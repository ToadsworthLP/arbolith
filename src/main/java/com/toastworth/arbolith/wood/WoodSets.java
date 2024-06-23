package com.toastworth.arbolith.wood;

import com.toastworth.arbolith.RenderTypes;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;

public class WoodSets {
    public static final List<WoodSet> WOOD_SETS = new ArrayList<>();

    public static final WoodSet CHERRY_SET = new WoodSet("cherry", "Cherry", MaterialColor.WOOD, MaterialColor.PODZOL, RenderTypes.CUTOUT);
    public static final WoodSet MAPLE_SET = new WoodSet("maple", "Maple", MaterialColor.QUARTZ, MaterialColor.PODZOL, RenderTypes.CUTOUT);
    public static final WoodSet LARCH_SET = new WoodSet("larch", "Larch", MaterialColor.WOOD, MaterialColor.PODZOL, RenderTypes.SOLID);
    public static final WoodSet SILVERWOOD_SET = new WoodSet("silverwood", "Silverwood", MaterialColor.QUARTZ, MaterialColor.QUARTZ, RenderTypes.SOLID);

    static {
        WOOD_SETS.add(CHERRY_SET);
        WOOD_SETS.add(MAPLE_SET);
        WOOD_SETS.add(LARCH_SET);
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
}
