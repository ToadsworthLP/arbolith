package com.toastworth.arbolith.tree;

import com.toastworth.arbolith.worldgen.ArbolithConfiguredFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
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
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
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
    private WeightedRandomList<WeightedEntry.Wrapper<ResourceKey<ConfiguredFeature<?, ?>>>> treeConfiguredFeatures;
    private WeightedRandomList<WeightedEntry.Wrapper<ResourceKey<ConfiguredFeature<?, ?>>>> treeWithBeesConfiguredFeatures;
    private WeightedRandomList<WeightedEntry.Wrapper<ResourceKey<ConfiguredFeature<?, ?>>>> treeMegaConfiguredFeatures;

    public TreeType(String name, String displayName) {
        this.name = name;
        this.displayName = displayName;
    }

    public void addToDeferredRegister(DeferredRegister<Block> blockDeferredRegister) {
        leavesBlock = blockDeferredRegister.register(name + "_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(TreeType::ocelotOrParrot).isSuffocating(TreeType::never).isViewBlocking(TreeType::never)) {
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

        saplingBlock = blockDeferredRegister.register(name + "_sapling", () -> new SaplingBlock(getAbstractTreeGrower(), BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));

        pottedSaplingBlock = blockDeferredRegister.register("potted_" + name + "_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, saplingBlock, BlockBehaviour.Properties.copy(Blocks.POTTED_OAK_SAPLING)));

        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(saplingBlock.getId(), pottedSaplingBlock);

        SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>> treeListBuilder = new SimpleWeightedRandomList.Builder<>();
        SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>> treeWithBeesListBuilder = new SimpleWeightedRandomList.Builder<>();
        SimpleWeightedRandomList.Builder<ResourceKey<ConfiguredFeature<?, ?>>> megaTreeListBuilder = new SimpleWeightedRandomList.Builder<>();
        treeConfigurations.forEach(entry -> {
            if(entry.isMega) {
                megaTreeListBuilder.add(entry.key, entry.weight);
            } else {
                treeListBuilder.add(entry.key, entry.weight);
                treeWithBeesListBuilder.add(entry.keyBees, entry.weight);
            }
        });

        treeConfiguredFeatures = treeListBuilder.build();
        treeWithBeesConfiguredFeatures = treeWithBeesListBuilder.build();
        treeMegaConfiguredFeatures = megaTreeListBuilder.build();
    }

    public TreeType withConfiguration(String name, TreeConfigurationProvider configurationProvider, int weight) {
        ResourceKey<ConfiguredFeature<?, ?>> key = ArbolithConfiguredFeatures.registerKey(this.name + "_tree_" + name);
        ResourceKey<ConfiguredFeature<?, ?>> keyBees = ArbolithConfiguredFeatures.registerKey(this.name + "_tree_" + name + "_bees");

        treeConfigurations.add(new TreeGrowerListEntry(key, keyBees, configurationProvider, weight, false));
        return this;
    }

    public TreeType withMegaConfiguration(String name, TreeConfigurationProvider configurationProvider, int weight) {
        ResourceKey<ConfiguredFeature<?, ?>> key = ArbolithConfiguredFeatures.registerKey(this.name + "_tree_" + name);

        treeConfigurations.add(new TreeGrowerListEntry(key, null, configurationProvider, weight, true));
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

    public List<TreeGrowerListEntry> getTreeConfigurations() {
        return treeConfigurations;
    }

    private static boolean never(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return false;
    }

    private static boolean ocelotOrParrot(BlockState p_50822_, BlockGetter p_50823_, BlockPos p_50824_, EntityType<?> p_50825_) {
        return p_50825_ == EntityType.OCELOT || p_50825_ == EntityType.PARROT;
    }

    @NotNull
    private AbstractTreeGrower getAbstractTreeGrower() {
        AbstractTreeGrower treeGrower;
        if(hasMegaConfiguration) {
            treeGrower = new AbstractMegaTreeGrower() {
                @Override
                protected @Nullable ResourceKey<ConfiguredFeature<?, ?>> getConfiguredMegaFeature(RandomSource randomSource) {
                    Optional<WeightedEntry.Wrapper<ResourceKey<ConfiguredFeature<?, ?>>>> result = treeMegaConfiguredFeatures.getRandom(randomSource);
                    return result.isPresent() ? result.get().getData() : null;
                }

                @Override
                protected @Nullable ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomSource, boolean b) {
                    Optional<WeightedEntry.Wrapper<ResourceKey<ConfiguredFeature<?, ?>>>> result = b ? treeWithBeesConfiguredFeatures.getRandom(randomSource) : treeConfiguredFeatures.getRandom(randomSource);
                    return result.isPresent() ? result.get().getData() : null;
                }
            };
        } else {
            treeGrower = new AbstractTreeGrower() {
                @Override
                protected @Nullable ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomSource, boolean b) {
                    Optional<WeightedEntry.Wrapper<ResourceKey<ConfiguredFeature<?, ?>>>> result = b ? treeWithBeesConfiguredFeatures.getRandom(randomSource) : treeConfiguredFeatures.getRandom(randomSource);
                    return result.isPresent() ? result.get().getData() : null;
                }
            };
        }

        return treeGrower;
    }
}
