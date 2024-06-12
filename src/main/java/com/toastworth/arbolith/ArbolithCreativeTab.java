package com.toastworth.arbolith;

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
        return new ItemStack(Items.OAK_SAPLING);
    }
}
