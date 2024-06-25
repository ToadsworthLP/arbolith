package com.toastworth.arbolith.tree;

import com.google.common.collect.ImmutableList;
import com.toastworth.arbolith.wood.WoodSets;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaJungleFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaPineFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.CocoaDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.MegaJungleTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraftforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;

public class TreeTypes {
    public static final List<TreeType> TREE_TYPES = new ArrayList<>();

    public static final TreeType CHERRY_TREE_TYPE = new TreeType("cherry", "Cherry")
            .withConfiguration("normal", ((leavesBlock, hasBees) -> new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(WoodSets.CHERRY_SET.getLogBlock().get()),
                    new FancyTrunkPlacer(5, 2, 2),
                    BlockStateProvider.simple(leavesBlock.get()),
                    new FancyFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), 2),
                    new TwoLayersFeatureSize(0, 0, 0))
                    .decorators(hasBees ? ImmutableList.of(new BeehiveDecorator(0.05f)) : ImmutableList.of())
                    .ignoreVines()
                    .build()),
                    9)
            .withConfiguration("large", ((leavesBlock, hasBees) -> new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(WoodSets.CHERRY_SET.getLogBlock().get()),
                    new FancyTrunkPlacer(9, 3, 3),
                    BlockStateProvider.simple(leavesBlock.get()),
                    new FancyFoliagePlacer(ConstantInt.of(4), ConstantInt.of(2), 3),
                    new TwoLayersFeatureSize(0, 0, 0))
                    .decorators(hasBees ? ImmutableList.of(new BeehiveDecorator(0.05f)) : ImmutableList.of())
                    .ignoreVines()
                    .build()),
                    1);

    public static final TreeType RED_MAPLE_TREE_TYPE = new TreeType("red_maple", "Red Maple")
            .withConfiguration("normal", ((leavesBlock, hasBees) -> new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(WoodSets.MAPLE_SET.getLogBlock().get()),
                    new StraightTrunkPlacer(5, 1, 1),
                    BlockStateProvider.simple(leavesBlock.get()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                    new TwoLayersFeatureSize(0, 0, 0))
                    .decorators(hasBees ? ImmutableList.of(new BeehiveDecorator(0.05f)) : ImmutableList.of())
                    .ignoreVines()
                    .build()),
                    9)
            .withConfiguration("large", ((leavesBlock, hasBees) -> new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(WoodSets.MAPLE_SET.getLogBlock().get()),
                    new StraightTrunkPlacer(8, 2, 1),
                    BlockStateProvider.simple(leavesBlock.get()),
                    new MegaPineFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), UniformInt.of(7, 8)),
                    new TwoLayersFeatureSize(0, 0, 0))
                    .decorators(hasBees ? ImmutableList.of(new BeehiveDecorator(0.05f)) : ImmutableList.of())
                    .ignoreVines()
                    .build()),
                    1);

    public static final TreeType ORANGE_MAPLE_TREE_TYPE = new TreeType("orange_maple", "Orange Maple")
            .withConfiguration("normal", ((leavesBlock, hasBees) -> new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(WoodSets.MAPLE_SET.getLogBlock().get()),
                    new StraightTrunkPlacer(5, 1, 1),
                    BlockStateProvider.simple(leavesBlock.get()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                    new TwoLayersFeatureSize(0, 0, 0))
                    .decorators(hasBees ? ImmutableList.of(new BeehiveDecorator(0.05f)) : ImmutableList.of())
                    .ignoreVines()
                    .build()),
                    9)
            .withConfiguration("large", ((leavesBlock, hasBees) -> new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(WoodSets.MAPLE_SET.getLogBlock().get()),
                    new StraightTrunkPlacer(8, 2, 1),
                    BlockStateProvider.simple(leavesBlock.get()),
                    new MegaPineFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), UniformInt.of(7, 8)),
                    new TwoLayersFeatureSize(0, 0, 0))
                    .decorators(hasBees ? ImmutableList.of(new BeehiveDecorator(0.05f)) : ImmutableList.of())
                    .ignoreVines()
                    .build()),
                    1);

    public static final TreeType LARCH_TREE_TYPE = new TreeType("larch", "Larch")
            .withConfiguration("normal", ((leavesBlock, hasBees) -> new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(WoodSets.LARCH_SET.getLogBlock().get()),
                    new StraightTrunkPlacer(5, 2, 2),
                    BlockStateProvider.simple(leavesBlock.get()),
                    new MegaPineFoliagePlacer(ConstantInt.of(1), UniformInt.of(0, 2), UniformInt.of(5, 6)),
                    new TwoLayersFeatureSize(0, 0, 0))
                    .ignoreVines()
                    .decorators(hasBees ? ImmutableList.of(new BeehiveDecorator(0.05f)) : ImmutableList.of())
                    .build()),
                    9)
            .withConfiguration("large", ((leavesBlock, hasBees) -> new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(WoodSets.LARCH_SET.getLogBlock().get()),
                    new StraightTrunkPlacer(10, 2, 1),
                    BlockStateProvider.simple(leavesBlock.get()),
                    new MegaPineFoliagePlacer(ConstantInt.of(1), UniformInt.of(1, 2), UniformInt.of(9, 12)),
                    new TwoLayersFeatureSize(0, 0, 0))
                    .decorators(hasBees ? ImmutableList.of(new BeehiveDecorator(0.05f)) : ImmutableList.of())
                    .ignoreVines()
                    .build()),
                    1);

    public static final TreeType JACARANDA_TREE_TYPE = new TreeType("jacaranda", "Jacaranda")
            .withConfiguration("normal", ((leavesBlock, hasBees) -> new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(WoodSets.JACARANDA_SET.getLogBlock().get()),
                    new ForkingTrunkPlacer(5,2,1),
                    BlockStateProvider.simple(leavesBlock.get()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 2),
                    new TwoLayersFeatureSize(0, 0, 0))
                    .ignoreVines()
                    .build()),
                    1)
            .withMegaConfiguration("mega", ((leavesBlock, hasBees) -> new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(WoodSets.JACARANDA_SET.getLogBlock().get()),
                    new MegaJungleTrunkPlacer(10, 2, 19),
                    BlockStateProvider.simple(leavesBlock.get()),
                    new MegaJungleFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), 2),
                    new TwoLayersFeatureSize(1, 1, 2))
                    .build()),
                    1);

    public static final TreeType SILVERWOOD_TREE_TYPE = new TreeType("silverwood", "Silverwood")
            .withConfiguration("normal", ((leavesBlock, hasBees) -> new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(WoodSets.SILVERWOOD_SET.getLogBlock().get()),
                    new FancyTrunkPlacer(6, 2, 1),
                    BlockStateProvider.simple(leavesBlock.get()),
                    new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4),
                    new TwoLayersFeatureSize(0, 0, 0))
                    .decorators(hasBees ? ImmutableList.of(new BeehiveDecorator(0.05f)) : ImmutableList.of())
                    .ignoreVines()
                    .build()),
                    9)
    .withConfiguration("large", ((leavesBlock, hasBees) -> new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(WoodSets.SILVERWOOD_SET.getLogBlock().get()),
                    new FancyTrunkPlacer(9, 3, 2),
                    BlockStateProvider.simple(leavesBlock.get()),
                    new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(3), 4),
                    new TwoLayersFeatureSize(0, 0, 0))
                    .decorators(hasBees ? ImmutableList.of(new BeehiveDecorator(0.05f)) : ImmutableList.of())
                    .ignoreVines()
                    .build()),
            1);

    static {
        TREE_TYPES.add(CHERRY_TREE_TYPE);
        TREE_TYPES.add(RED_MAPLE_TREE_TYPE);
        TREE_TYPES.add(ORANGE_MAPLE_TREE_TYPE);
        TREE_TYPES.add(LARCH_TREE_TYPE);
        TREE_TYPES.add(JACARANDA_TREE_TYPE);
        TREE_TYPES.add(SILVERWOOD_TREE_TYPE);
    }

    public static void addToDeferredRegister(DeferredRegister<Block> blockDeferredRegister, DeferredRegister<ConfiguredFeature<?, ?>> configuredFeatureRegister) {
        TREE_TYPES.forEach((woodSet -> {
            woodSet.addToDeferredRegister(blockDeferredRegister, configuredFeatureRegister);
        }));
    }
}
