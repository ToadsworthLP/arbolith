package com.toastworth.arbolith.tree;

import com.google.common.collect.ImmutableList;
import com.toastworth.arbolith.wood.WoodSets;
import net.minecraft.util.valueproviders.BiasedToBottomInt;
import net.minecraft.util.valueproviders.ClampedNormalInt;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaPineFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraftforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

public class TreeTypes {
    public static final List<TreeType> TREE_TYPES = new ArrayList<>();

    public static final TreeType CHERRY_TREE_TYPE = new TreeType("cherry", "Cherry")
            .withConfiguration("normal", ((leavesBlock, hasBees) -> new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(WoodSets.CHERRY_SET.getLogBlock().get()),
                    new FancyTrunkPlacer(5, 3, 5),
                    BlockStateProvider.simple(leavesBlock.get()),
                    new FancyFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), 2),
                    new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(16)))
                    .decorators(hasBees ? ImmutableList.of(new BeehiveDecorator(0.05f)) : ImmutableList.of()).build()),
                    9)
            .withConfiguration("large", ((leavesBlock, hasBees) -> new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(WoodSets.CHERRY_SET.getLogBlock().get()),
                    new FancyTrunkPlacer(10, 6, 10),
                    BlockStateProvider.simple(leavesBlock.get()),
                    new FancyFoliagePlacer(ConstantInt.of(4), ConstantInt.of(2), 3),
                    new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(16)))
                    .decorators(hasBees ? ImmutableList.of(new BeehiveDecorator(0.05f)) : ImmutableList.of()).build()),
                    1);

    public static final TreeType RED_MAPLE_TREE_TYPE = new TreeType("red_maple", "Red Maple")
            .withConfiguration("normal", ((leavesBlock, hasBees) -> new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(WoodSets.MAPLE_SET.getLogBlock().get()),
                    new StraightTrunkPlacer(5, 2, 2),
                    BlockStateProvider.simple(leavesBlock.get()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                    new TwoLayersFeatureSize(1, 0, 1))
                    .decorators(hasBees ? ImmutableList.of(new BeehiveDecorator(0.05f)) : ImmutableList.of()).build()),
                    9)
            .withConfiguration("large", ((leavesBlock, hasBees) -> new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(WoodSets.MAPLE_SET.getLogBlock().get()),
                    new StraightTrunkPlacer(15, 2, 2),
                    BlockStateProvider.simple(leavesBlock.get()),
                    new MegaPineFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0), ClampedNormalInt.of(12, 3, 11, 15)),
                    new TwoLayersFeatureSize(0, 1, 0, OptionalInt.of(0)))
                    .decorators(hasBees ? ImmutableList.of(new BeehiveDecorator(0.05f)) : ImmutableList.of()).build()),
                    1);

    public static final TreeType ORANGE_MAPLE_TREE_TYPE = new TreeType("orange_maple", "Orange Maple")
            .withConfiguration("normal", ((leavesBlock, hasBees) -> new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(WoodSets.MAPLE_SET.getLogBlock().get()),
                    new StraightTrunkPlacer(5, 2, 2),
                    BlockStateProvider.simple(leavesBlock.get()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                    new TwoLayersFeatureSize(1, 0, 1))
                    .decorators(hasBees ? ImmutableList.of(new BeehiveDecorator(0.05f)) : ImmutableList.of()).build()),
            9)
            .withConfiguration("large", ((leavesBlock, hasBees) -> new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(WoodSets.MAPLE_SET.getLogBlock().get()),
                    new StraightTrunkPlacer(15, 2, 2),
                    BlockStateProvider.simple(leavesBlock.get()),
                    new MegaPineFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0), ClampedNormalInt.of(12, 3, 11, 15)),
                    new TwoLayersFeatureSize(0, 1, 0, OptionalInt.of(0)))
                    .decorators(hasBees ? ImmutableList.of(new BeehiveDecorator(0.05f)) : ImmutableList.of()).build()),
            1);

    public static final TreeType SILVERWOOD_TREE_TYPE = new TreeType("silverwood", "Silverwood")
            .withConfiguration("normal", ((leavesBlock, hasBees) -> new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(WoodSets.SILVERWOOD_SET.getLogBlock().get()),
                    new ForkingTrunkPlacer(5, 2, 2),
                    BlockStateProvider.simple(leavesBlock.get()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(2), 3),
                    new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(16)))
                    .decorators(hasBees ? ImmutableList.of(new BeehiveDecorator(0.05f)) : ImmutableList.of()).build()),
                9)
            .withConfiguration("large", ((leavesBlock, hasBees) -> new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(WoodSets.SILVERWOOD_SET.getLogBlock().get()),
                    new FancyTrunkPlacer(10, 6, 10),
                    BlockStateProvider.simple(leavesBlock.get()),
                    new FancyFoliagePlacer(ConstantInt.of(4), ConstantInt.of(2), 3),
                    new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(16)))
                    .decorators(hasBees ? ImmutableList.of(new BeehiveDecorator(0.05f)) : ImmutableList.of()).build()),
                1);
    public static final TreeType LARCH_TREE_TYPE = new TreeType("larch", "Larch")
            .withConfiguration("normal", ((leavesBlock, hasBees) -> new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(WoodSets.LARCH_SET.getLogBlock().get()),
                    new StraightTrunkPlacer(5, 2, 1),
                    BlockStateProvider.simple(leavesBlock.get()),
                    new SpruceFoliagePlacer(UniformInt.of(2, 3), UniformInt.of(0, 2), UniformInt.of(1, 2)),
                    new TwoLayersFeatureSize(2, 0, 2))
                    .ignoreVines()
                    .decorators(hasBees ? ImmutableList.of(new BeehiveDecorator(0.05f)) : ImmutableList.of()).build()),
                    9)
            .withConfiguration("large", ((leavesBlock, hasBees) -> new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(WoodSets.LARCH_SET.getLogBlock().get()),
                    new StraightTrunkPlacer(7, 4, 10),
                    BlockStateProvider.simple(leavesBlock.get()),
                    new MegaPineFoliagePlacer(ConstantInt.of(1), UniformInt.of(1, 3), UniformInt.of(12, 18)),
                    new TwoLayersFeatureSize(1, 0, 1))
                    .decorators(hasBees ? ImmutableList.of(new BeehiveDecorator(0.05f)) : ImmutableList.of()).build()),
                    1);

    static {
        TREE_TYPES.add(CHERRY_TREE_TYPE);
        TREE_TYPES.add(RED_MAPLE_TREE_TYPE);
        TREE_TYPES.add(ORANGE_MAPLE_TREE_TYPE);
        TREE_TYPES.add(SILVERWOOD_TREE_TYPE);
        TREE_TYPES.add(LARCH_TREE_TYPE);
    }

    public static void addToDeferredRegister(DeferredRegister<Block> blockDeferredRegister, DeferredRegister<ConfiguredFeature<?, ?>> configuredFeatureRegister) {
        TREE_TYPES.forEach((woodSet -> {
            woodSet.addToDeferredRegister(blockDeferredRegister, configuredFeatureRegister);
        }));
    }
}
