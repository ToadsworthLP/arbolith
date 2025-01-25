package com.toastworth.arbolith.datagen;

import com.toastworth.arbolith.Arbolith;
import com.toastworth.arbolith.ArbolithTags;
import com.toastworth.arbolith.tree.TreeType;
import com.toastworth.arbolith.tree.TreeTypes;
import com.toastworth.arbolith.wood.WoodSet;
import com.toastworth.arbolith.wood.WoodSets;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ArbolithItemTagProvider extends ItemTagsProvider {
    public ArbolithItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, p_275729_, p_275322_, Arbolith.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        WoodSets.WOOD_SETS.forEach(this::addWoodSetTags);
        TreeTypes.TREE_TYPES.forEach(this::addTreeTags);
    }

    private void addWoodSetTags(WoodSet woodSet) {
        this.tag(ItemTags.LOGS_THAT_BURN)
                .add(woodSet.getLogBlock().get().asItem())
                .add(woodSet.getWoodBlock().get().asItem())
                .add(woodSet.getStrippedLogBlock().get().asItem())
                .add(woodSet.getStrippedWoodBlock().get().asItem());

        this.tag(ItemTags.PLANKS).add(woodSet.getPlanksBlock().get().asItem());
        this.tag(ItemTags.SIGNS).add(woodSet.getSignItem().get());
        this.tag(ItemTags.HANGING_SIGNS).add(woodSet.getHangingSignItem().get());
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
        this.tag(TagKey.create(Registries.ITEM, new ResourceLocation("forge", "trapdoors/wooden")))
                .add(woodSet.getTrapdoorBlock().get().asItem());
        this.tag(TagKey.create(Registries.ITEM, new ResourceLocation("forge", "doors/wooden")))
                .add(woodSet.getDoorBlock().get().asItem());
        this.tag(TagKey.create(Registries.ITEM, new ResourceLocation("forge", "stairs/wooden")))
                .add(woodSet.getStairsBlock().get().asItem());
        this.tag(TagKey.create(Registries.ITEM, new ResourceLocation("forge", "slabs/wooden")))
                .add(woodSet.getSlabBlock().get().asItem());
        this.tag(TagKey.create(Registries.ITEM, new ResourceLocation("forge", "planks")))
                .add(woodSet.getPlanksBlock().get().asItem());
        this.tag(TagKey.create(Registries.ITEM, new ResourceLocation("forge", "logs")))
                .add(woodSet.getLogBlock().get().asItem());
        this.tag(TagKey.create(Registries.ITEM, new ResourceLocation("forge", "wood")))
                .add(woodSet.getWoodBlock().get().asItem());
        this.tag(TagKey.create(Registries.ITEM, new ResourceLocation("forge", "stripped_logs")))
                .add(woodSet.getStrippedLogBlock().get().asItem());
        this.tag(TagKey.create(Registries.ITEM, new ResourceLocation("forge", "stripped_wood")))
                .add(woodSet.getStrippedWoodBlock().get().asItem());

        this.tag(woodSet.getLogItemTag())
                .add(woodSet.getLogBlock().get().asItem())
                .add(woodSet.getWoodBlock().get().asItem())
                .add(woodSet.getStrippedLogBlock().get().asItem())
                .add(woodSet.getStrippedWoodBlock().get().asItem());

        this.tag(ArbolithTags.ARBOLITH_PLANKS_ITEM).add(woodSet.getPlanksBlock().get().asItem());
    }

    private void addTreeTags(TreeType treeType) {
        this.tag(ItemTags.LEAVES).add(treeType.getLeavesBlock().get().asItem());
        this.tag(ItemTags.SAPLINGS).add(treeType.getSaplingBlock().get().asItem());
    }
}
