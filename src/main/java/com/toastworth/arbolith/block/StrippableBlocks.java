package com.toastworth.arbolith.block;

import com.google.common.collect.Maps;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

public class StrippableBlocks {
    private static final Map<RegistryObject<Block>, RegistryObject<Block>> customStrippables = new HashMap<>();

    public static void setup() {
        Map<Block, Block> updatedMap = Maps.newHashMap(AxeItem.STRIPPABLES);
        customStrippables.forEach((key, value) -> {
            updatedMap.put(key.get(), value.get());
        });

        AxeItem.STRIPPABLES = updatedMap;
    }

    public static void add(RegistryObject<Block> log, RegistryObject<Block> strippedLog) {
        customStrippables.put(log, strippedLog);
    }
}
