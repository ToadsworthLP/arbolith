package com.toastworth.arbolith;

import com.toastworth.arbolith.tree.TreeTypes;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ArbolithCreativeTab extends CreativeModeTab {
    public static final ArbolithCreativeTab INSTANCE = new ArbolithCreativeTab("arbolith");

    public ArbolithCreativeTab(String label) {
        super(label);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(TreeTypes.CHERRY_TREE_TYPE.getSaplingBlock().get().asItem());
    }
}
