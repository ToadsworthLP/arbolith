package com.toastworth.arbolith.tree;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.AbstractMegaTreeGrower;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TreeType {
    private String name;
    private String displayName;
    private RegistryObject<Block> leavesBlock;
    private RegistryObject<Block> saplingBlock;
    private RegistryObject<Block> pottedSaplingBlock;
    private boolean hasMegaConfiguration;

    private List<TreeGrowerListEntry> treeConfigurations = new ArrayList<>(1);
    private WeightedRandomList<WeightedEntry.Wrapper<RegistryObject<ConfiguredFeature<?, ?>>>> treeConfiguredFeatures;
    private WeightedRandomList<WeightedEntry.Wrapper<RegistryObject<ConfiguredFeature<?, ?>>>> treeWithBeesConfiguredFeatures;
    private WeightedRandomList<WeightedEntry.Wrapper<RegistryObject<ConfiguredFeature<?, ?>>>> treeMegaConfiguredFeatures;

    public TreeType(String name, String displayName) {
        this.name = name;
        this.displayName = displayName;
    }

    public void addToDeferredRegister(DeferredRegister<Block> blockDeferredRegister, DeferredRegister<ConfiguredFeature<?, ?>> configuredFeatureRegister) {
        leavesBlock = blockDeferredRegister.register(name + "_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(TreeType::ocelotOrParrot).isSuffocating(TreeType::never).isViewBlocking(TreeType::never)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }
            }
        );

        saplingBlock = blockDeferredRegister.register(name + "_sapling", () -> new SaplingBlock(getAbstractTreeGrower(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));

        pottedSaplingBlock = blockDeferredRegister.register("potted_" + name + "_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, saplingBlock, BlockBehaviour.Properties.copy(Blocks.POTTED_OAK_SAPLING)));

        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(saplingBlock.getId(), pottedSaplingBlock);

        SimpleWeightedRandomList.Builder<RegistryObject<ConfiguredFeature<?, ?>>> treeListBuilder = new SimpleWeightedRandomList.Builder<>();
        SimpleWeightedRandomList.Builder<RegistryObject<ConfiguredFeature<?, ?>>> treeWithBeesListBuilder = new SimpleWeightedRandomList.Builder<>();
        SimpleWeightedRandomList.Builder<RegistryObject<ConfiguredFeature<?, ?>>> megaTreeListBuilder = new SimpleWeightedRandomList.Builder<>();
        treeConfigurations.forEach(entry -> {
            if(entry.isMega) {
                megaTreeListBuilder.add(configuredFeatureRegister.register(name + "_tree_" + entry.name, () -> new ConfiguredFeature<>(Feature.TREE, entry.value.get(leavesBlock, false))), entry.weight);
            } else {
                treeListBuilder.add(configuredFeatureRegister.register(name + "_tree_" + entry.name, () -> new ConfiguredFeature<>(Feature.TREE, entry.value.get(leavesBlock, false))), entry.weight);
                treeWithBeesListBuilder.add(configuredFeatureRegister.register(name + "_tree_" + entry.name + "_bees", () -> new ConfiguredFeature<>(Feature.TREE, entry.value.get(leavesBlock, true))), entry.weight);
            }
        });

        treeConfiguredFeatures = treeListBuilder.build();
        treeWithBeesConfiguredFeatures = treeWithBeesListBuilder.build();
        treeMegaConfiguredFeatures = megaTreeListBuilder.build();
        treeConfigurations = null;
    }

    public TreeType withConfiguration(String name, TreeConfigurationProvider configurationProvider, int weight) {
        treeConfigurations.add(new TreeGrowerListEntry(name, configurationProvider, weight, false));
        return this;
    }

    public TreeType withMegaConfiguration(String name, TreeConfigurationProvider configurationProvider, int weight) {
        treeConfigurations.add(new TreeGrowerListEntry(name, configurationProvider, weight, true));
        hasMegaConfiguration = true;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public RegistryObject<Block> getLeavesBlock() {
        return leavesBlock;
    }

    public RegistryObject<Block> getSaplingBlock() {
        return saplingBlock;
    }

    public RegistryObject<Block> getPottedSaplingBlock() {
        return pottedSaplingBlock;
    }

    private static boolean never(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return false;
    }

    private static boolean ocelotOrParrot(BlockState p_50822_, BlockGetter p_50823_, BlockPos p_50824_, EntityType<?> p_50825_) {
        return p_50825_ == EntityType.OCELOT || p_50825_ == EntityType.PARROT;
    }

    private static class TreeGrowerListEntry {
        public String name;
        public TreeConfigurationProvider value;
        public int weight;
        public boolean isMega;

        public TreeGrowerListEntry(String name, TreeConfigurationProvider value, int weight, boolean isMega) {
            this.name = name;
            this.value = value;
            this.weight = weight;
            this.isMega = isMega;
        }
    }

    @NotNull
    private AbstractTreeGrower getAbstractTreeGrower() {
        AbstractTreeGrower treeGrower;
        if(hasMegaConfiguration) {
            treeGrower = new AbstractMegaTreeGrower() {
                @Override
                protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomSource, boolean b) {
                    Optional<WeightedEntry.Wrapper<RegistryObject<ConfiguredFeature<?, ?>>>> result = b ? treeWithBeesConfiguredFeatures.getRandom(randomSource) : treeConfiguredFeatures.getRandom(randomSource);
                    return result.orElseThrow().getData().getHolder().get();
                }

                @Nullable
                @Override
                protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredMegaFeature(RandomSource randomSource) {
                    Optional<WeightedEntry.Wrapper<RegistryObject<ConfiguredFeature<?, ?>>>> result = treeMegaConfiguredFeatures.getRandom(randomSource);
                    return result.orElseThrow().getData().getHolder().get();
                }
            };
        } else {
            treeGrower = new AbstractTreeGrower() {
                @Nullable
                @Override
                protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomSource, boolean b) {
                    Optional<WeightedEntry.Wrapper<RegistryObject<ConfiguredFeature<?, ?>>>> result = b ? treeWithBeesConfiguredFeatures.getRandom(randomSource) : treeConfiguredFeatures.getRandom(randomSource);
                    return result.orElseThrow().getData().getHolder().get();
                }
            };
        }
        return treeGrower;
    }

    @FunctionalInterface
    public interface TreeConfigurationProvider {
        TreeConfiguration get(RegistryObject<Block> leavesBlock, boolean hasBees);
    }
}
