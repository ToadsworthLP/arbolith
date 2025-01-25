package com.toastworth.arbolith.datagen;

import com.toastworth.arbolith.Arbolith;
import com.toastworth.arbolith.tree.TreeType;
import com.toastworth.arbolith.tree.TreeTypes;
import com.toastworth.arbolith.wood.WoodSet;
import com.toastworth.arbolith.wood.WoodSets;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ArbolithBlockLootTables extends BlockLootSubProvider {
    private static final float[] LEAVES_DROP_SAPLING_CHANCES = new float[] { 0.05F, 0.0625F, 0.083333336F, 0.1F };

    public ArbolithBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        WoodSets.WOOD_SETS.forEach(this::addWoodSetLootTables);
        TreeTypes.TREE_TYPES.forEach(this::addTreeLootTables);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return Arbolith.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }

    private void addWoodSetLootTables(WoodSet woodSet) {
        dropSelf(woodSet.getLogBlock().get());
        dropSelf(woodSet.getWoodBlock().get());
        dropSelf(woodSet.getStrippedLogBlock().get());
        dropSelf(woodSet.getStrippedWoodBlock().get());
        dropSelf(woodSet.getPlanksBlock().get());
        add(woodSet.getSlabBlock().get(), createSlabItemTable(woodSet.getSlabBlock().get()));
        dropSelf(woodSet.getStairsBlock().get());
        dropSelf(woodSet.getFenceBlock().get());
        dropSelf(woodSet.getFenceGateBlock().get());
        dropSelf(woodSet.getButtonBlock().get());
        dropSelf(woodSet.getPressurePlateBlock().get());
        add(woodSet.getDoorBlock().get(), createDoorTable(woodSet.getDoorBlock().get()));
        dropSelf(woodSet.getTrapdoorBlock().get());
        dropOther(woodSet.getSignBlock().get(), woodSet.getSignItem().get());
        dropOther(woodSet.getWallSignBlock().get(), woodSet.getSignItem().get());
        dropOther(woodSet.getHangingSignBlock().get(), woodSet.getHangingSignItem().get());
        dropOther(woodSet.getWallHangingSignBlock().get(), woodSet.getHangingSignItem().get());
    }

    private void addTreeLootTables(TreeType treeType) {
        add(treeType.getLeavesBlock().get(), createLeavesDrops(treeType.getLeavesBlock().get(), treeType.getSaplingBlock().get(), LEAVES_DROP_SAPLING_CHANCES));
        dropSelf(treeType.getSaplingBlock().get());
        add(treeType.getPottedSaplingBlock().get(), createPotFlowerItemTable(treeType.getPottedSaplingBlock().get()));
    }
}
