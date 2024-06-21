package com.toastworth.arbolith;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ArbolithTags {
    public static final TagKey<Item> ARBOLITH_PLANKS_ITEM = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(Arbolith.MOD_ID, "planks"));
    public static final TagKey<Block> ARBOLITH_PLANKS_BLOCK = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(Arbolith.MOD_ID, "planks"));
}
