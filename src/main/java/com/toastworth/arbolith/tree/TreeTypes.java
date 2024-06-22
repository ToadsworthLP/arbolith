package com.toastworth.arbolith.tree;

import com.google.common.collect.ImmutableList;
import com.toastworth.arbolith.wood.WoodSets;
import net.minecraft.util.valueproviders.ClampedNormalInt;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaPineFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraftforge.registries.DeferredRegister;

import java.sql.Blob;
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

    public static final TreeType ASPEN_TREE_TYPE = new TreeType("aspen", "Aspen") // TODO find Terralith biome to put this in - maybe ditch yellow and make it silverwood?
            .withConfiguration("normal", ((leavesBlock, hasBees) -> new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(WoodSets.ASPEN_SET.getLogBlock().get()),
                    new StraightTrunkPlacer(7, 1, 1),
                    BlockStateProvider.simple(leavesBlock.get()),
                    new FancyFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0), 6),
                    new TwoLayersFeatureSize(1, 0, 1))
                    .decorators(hasBees ? ImmutableList.of(new BeehiveDecorator(0.05f)) : ImmutableList.of()).build()),
            9);

    public static final TreeType LARCH_TREE_TYPE = new TreeType("larch", "Larch") // TODO set up proper tree shape + add as "lark" to Terralith
            .withConfiguration("normal", ((leavesBlock, hasBees) -> new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(WoodSets.LARCH_SET.getLogBlock().get()),
                    new StraightTrunkPlacer(7, 1, 1),
                    BlockStateProvider.simple(leavesBlock.get()),
                    new FancyFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0), 6),
                    new TwoLayersFeatureSize(1, 0, 1))
                    .decorators(hasBees ? ImmutableList.of(new BeehiveDecorator(0.05f)) : ImmutableList.of()).build()),
            9);

    static {
        TREE_TYPES.add(CHERRY_TREE_TYPE);
        TREE_TYPES.add(RED_MAPLE_TREE_TYPE);
        TREE_TYPES.add(ORANGE_MAPLE_TREE_TYPE);
        TREE_TYPES.add(ASPEN_TREE_TYPE);
        TREE_TYPES.add(LARCH_TREE_TYPE);
    }

    public static void addToDeferredRegister(DeferredRegister<Block> blockDeferredRegister, DeferredRegister<ConfiguredFeature<?, ?>> configuredFeatureRegister) {
        TREE_TYPES.forEach((woodSet -> {
            woodSet.addToDeferredRegister(blockDeferredRegister, configuredFeatureRegister);
        }));
    }
}
