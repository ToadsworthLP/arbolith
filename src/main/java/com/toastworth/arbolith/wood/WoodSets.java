package com.toastworth.arbolith.wood;

import com.toastworth.arbolith.RenderTypes;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;

public class WoodSets {
    public static final List<WoodSet> WOOD_SETS = new ArrayList<>();

    public static final WoodSet MAPLE_SET = new WoodSet("maple", "Maple", MapColor.QUARTZ, MapColor.PODZOL, RenderTypes.CUTOUT);
    public static final WoodSet LARCH_SET = new WoodSet("larch", "Larch", MapColor.WOOD, MapColor.PODZOL, RenderTypes.SOLID);
    public static final WoodSet JACARANDA_SET = new WoodSet("jacaranda", "Jacaranda", MapColor.NETHER, MapColor.PODZOL, RenderTypes.CUTOUT);
    public static final WoodSet SILVERWOOD_SET = new WoodSet("silverwood", "Silverwood", MapColor.QUARTZ, MapColor.QUARTZ, RenderTypes.SOLID);

    static {
        WOOD_SETS.add(MAPLE_SET);
        WOOD_SETS.add(LARCH_SET);
        WOOD_SETS.add(JACARANDA_SET);
        WOOD_SETS.add(SILVERWOOD_SET);
    }

    public static void addToDeferredRegister(DeferredRegister<Block> blockDeferredRegister, DeferredRegister<Item> itemDeferredRegister) {
        WOOD_SETS.forEach((woodSet -> {
            woodSet.addToDeferredRegister(blockDeferredRegister, itemDeferredRegister);
        }));
    }

    public static Block[] getSignBlocks() {
        Block[] output = new Block[WOOD_SETS.size() * 2];

        for (int i = 0; i < WOOD_SETS.size(); i++) {
            int target = i * 2;
            WoodSet set = WOOD_SETS.get(i);
            output[target] = set.getSignBlock().get();
            output[target + 1] = set.getWallSignBlock().get();
        }

        return output;
    }

    public static Block[] getHangingSignBlocks() {
        Block[] output = new Block[WOOD_SETS.size() * 2];

        for (int i = 0; i < WOOD_SETS.size(); i++) {
            int target = i * 2;
            WoodSet set = WOOD_SETS.get(i);
            output[target] = set.getHangingSignBlock().get();
            output[target + 1] = set.getWallHangingSignBlock().get();
        }

        return output;
    }
}
