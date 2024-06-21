package com.toastworth.arbolith.tree;

import com.toastworth.arbolith.wood.WoodSets;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraftforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

public class TreeTypes {
    public static final List<TreeType> TREE_TYPES = new ArrayList<>();

    public static final TreeType CHERRY_TREE_TYPE = new TreeType("cherry", "Cherry")
            .withConfiguration("large", (leavesBlock -> new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(WoodSets.CHERRY_SET.getLogBlock().get()),
                    new FancyTrunkPlacer(12, 6, 10),
                    BlockStateProvider.simple(leavesBlock.get()),
                    new FancyFoliagePlacer(ConstantInt.of(4), ConstantInt.of(2), 3),
                    new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(16)))
                    .decorators(List.of(new BeehiveDecorator(0.05f))).build()),
                    1)
            .withConfiguration("medium", (leavesBlock -> new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(WoodSets.CHERRY_SET.getLogBlock().get()),
                        new FancyTrunkPlacer(6, 3, 5),
                        BlockStateProvider.simple(leavesBlock.get()),
                        new FancyFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), 2),
                        new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(16)))
                        .decorators(List.of(new BeehiveDecorator(0.05f))).build()),
                9);

    static {
        TREE_TYPES.add(CHERRY_TREE_TYPE);
    }

    public static void addToDeferredRegister(DeferredRegister<Block> blockDeferredRegister, DeferredRegister<ConfiguredFeature<?, ?>> configuredFeatureRegister) {
        TREE_TYPES.forEach((woodSet -> {
            woodSet.addToDeferredRegister(blockDeferredRegister, configuredFeatureRegister);
        }));
    }
}
