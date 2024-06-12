package com.toastworth.arbolith.datagen;

import com.toastworth.arbolith.Arbolith;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public class ArbolithBlockLootTables extends BlockLoot {
    private static final float[] LEAVES_DROP_SAPLING_CHANCES = new float[] { 0.05F, 0.0625F, 0.083333336F, 0.1F };

    @Override
    protected void addTables() {
        dropSelf(Arbolith.CHERRY_LOG.get());
        dropWhenSilkTouch(Arbolith.CHERRY_LEAVES.get());
        dropWhenSilkTouch(Arbolith.PINK_PETALS.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return Arbolith.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
