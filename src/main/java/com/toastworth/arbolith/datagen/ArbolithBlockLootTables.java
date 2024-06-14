package com.toastworth.arbolith.datagen;

import com.toastworth.arbolith.Arbolith;
import com.toastworth.arbolith.wood.WoodSet;
import com.toastworth.arbolith.wood.WoodSets;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public class ArbolithBlockLootTables extends BlockLoot {
    private static final float[] LEAVES_DROP_SAPLING_CHANCES = new float[] { 0.05F, 0.0625F, 0.083333336F, 0.1F };

    @Override
    protected void addTables() {
        dropWhenSilkTouch(Arbolith.PINK_PETALS.get());

        WoodSets.WOOD_SETS.forEach(this::addWoodSetLootTables);
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
        dropSelf(woodSet.getSlabBlock().get());
        dropSelf(woodSet.getStairsBlock().get());
        dropSelf(woodSet.getFenceBlock().get());
        dropSelf(woodSet.getFenceGateBlock().get());
        dropSelf(woodSet.getButtonBlock().get());
        dropSelf(woodSet.getPressurePlateBlock().get());
        dropSelf(woodSet.getDoorBlock().get());
        dropSelf(woodSet.getTrapdoorBlock().get());

        dropOther(woodSet.getSignBlock().get(), woodSet.getSignItem().get());
        dropOther(woodSet.getWallSignBlock().get(), woodSet.getSignItem().get());
    }
}
