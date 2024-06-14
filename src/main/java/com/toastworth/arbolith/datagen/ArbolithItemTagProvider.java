package com.toastworth.arbolith.datagen;

import com.toastworth.arbolith.wood.WoodSet;
import com.toastworth.arbolith.wood.WoodSets;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class ArbolithItemTagProvider extends ItemTagsProvider {
    public ArbolithItemTagProvider(DataGenerator p_126530_, BlockTagsProvider p_126531_, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_126530_, p_126531_, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {
        WoodSets.WOOD_SETS.forEach(this::addWoodSetTags);
    }


    private void addWoodSetTags(WoodSet woodSet) {
        this.tag(ItemTags.LOGS_THAT_BURN)
                .add(woodSet.getLogBlock().get().asItem())
                .add(woodSet.getWoodBlock().get().asItem())
                .add(woodSet.getStrippedLogBlock().get().asItem())
                .add(woodSet.getStrippedWoodBlock().get().asItem());

        this.tag(ItemTags.PLANKS).add(woodSet.getPlanksBlock().get().asItem());
        this.tag(ItemTags.SIGNS).add(woodSet.getSignItem().get());
        this.tag(ItemTags.WOODEN_BUTTONS).add(woodSet.getButtonBlock().get().asItem());
        this.tag(ItemTags.WOODEN_DOORS).add(woodSet.getDoorBlock().get().asItem());
        this.tag(ItemTags.WOODEN_FENCES).add(woodSet.getFenceBlock().get().asItem());
        this.tag(ItemTags.WOODEN_PRESSURE_PLATES).add(woodSet.getPressurePlateBlock().get().asItem());
        this.tag(ItemTags.WOODEN_SLABS).add(woodSet.getSlabBlock().get().asItem());
        this.tag(ItemTags.WOODEN_STAIRS).add(woodSet.getStairsBlock().get().asItem());
        this.tag(ItemTags.WOODEN_DOORS).add(woodSet.getDoorBlock().get().asItem());
        this.tag(ItemTags.WOODEN_TRAPDOORS).add(woodSet.getTrapdoorBlock().get().asItem());

        this.tag(Tags.Items.FENCE_GATES_WOODEN).add(woodSet.getFenceGateBlock().get().asItem());
        this.tag(Tags.Items.FENCES_WOODEN).add(woodSet.getFenceGateBlock().get().asItem());
        this.tag(TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("forge", "trapdoors/wooden")))
                .add(woodSet.getTrapdoorBlock().get().asItem());
        this.tag(TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("forge", "doors/wooden")))
                .add(woodSet.getDoorBlock().get().asItem());
        this.tag(TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("forge", "stairs/wooden")))
                .add(woodSet.getStairsBlock().get().asItem());
        this.tag(TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("forge", "slabs/wooden")))
                .add(woodSet.getSlabBlock().get().asItem());
        this.tag(TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("forge", "planks")))
                .add(woodSet.getPlanksBlock().get().asItem());
        this.tag(TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("forge", "logs")))
                .add(woodSet.getLogBlock().get().asItem());
        this.tag(TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("forge", "wood")))
                .add(woodSet.getWoodBlock().get().asItem());
        this.tag(TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("forge", "stripped_logs")))
                .add(woodSet.getStrippedLogBlock().get().asItem());
        this.tag(TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("forge", "stripped_wood")))
                .add(woodSet.getStrippedWoodBlock().get().asItem());

        this.tag(woodSet.getLogItemTag())
                .add(woodSet.getLogBlock().get().asItem())
                .add(woodSet.getWoodBlock().get().asItem())
                .add(woodSet.getStrippedLogBlock().get().asItem())
                .add(woodSet.getStrippedWoodBlock().get().asItem());
    }
}
