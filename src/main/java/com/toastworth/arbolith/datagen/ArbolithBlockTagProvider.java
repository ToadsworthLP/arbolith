package com.toastworth.arbolith.datagen;

import com.toastworth.arbolith.Arbolith;
import com.toastworth.arbolith.wood.WoodSet;
import com.toastworth.arbolith.wood.WoodSets;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class ArbolithBlockTagProvider extends BlockTagsProvider {
    public ArbolithBlockTagProvider(DataGenerator pGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, Arbolith.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        WoodSets.WOOD_SETS.forEach(this::addWoodSetTags);
    }

    private void addWoodSetTags(WoodSet woodSet) {
        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(woodSet.getLogBlock().get())
                .add(woodSet.getWoodBlock().get())
                .add(woodSet.getStrippedLogBlock().get())
                .add(woodSet.getStrippedWoodBlock().get());

        this.tag(BlockTags.OVERWORLD_NATURAL_LOGS).add(woodSet.getLogBlock().get());
        this.tag(BlockTags.PLANKS).add(woodSet.getPlanksBlock().get());
        this.tag(BlockTags.WOODEN_SLABS).add(woodSet.getSlabBlock().get());
        this.tag(BlockTags.WOODEN_STAIRS).add(woodSet.getStairsBlock().get());
        this.tag(BlockTags.WOODEN_FENCES).add(woodSet.getFenceBlock().get());
        this.tag(BlockTags.FENCE_GATES).add(woodSet.getFenceGateBlock().get());
        this.tag(BlockTags.WOODEN_BUTTONS).add(woodSet.getButtonBlock().get());
        this.tag(BlockTags.WOODEN_PRESSURE_PLATES).add(woodSet.getPressurePlateBlock().get());
        this.tag(BlockTags.WOODEN_DOORS).add(woodSet.getDoorBlock().get());
        this.tag(BlockTags.WOODEN_TRAPDOORS).add(woodSet.getTrapdoorBlock().get());
        this.tag(BlockTags.STANDING_SIGNS).add(woodSet.getSignBlock().get());
        this.tag(BlockTags.WALL_SIGNS).add(woodSet.getWallSignBlock().get());

        this.tag(Tags.Blocks.FENCE_GATES_WOODEN).add(woodSet.getFenceGateBlock().get());
        this.tag(Tags.Blocks.FENCES_WOODEN).add(woodSet.getFenceBlock().get());
        this.tag(TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation("forge", "trapdoors/wooden")))
                .add(woodSet.getTrapdoorBlock().get());
        this.tag(TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation("forge", "doors/wooden")))
                .add(woodSet.getDoorBlock().get());
        this.tag(TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation("forge", "stairs/wooden")))
                .add(woodSet.getStairsBlock().get());
        this.tag(TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation("forge", "slabs/wooden")))
                .add(woodSet.getSlabBlock().get());
        this.tag(TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation("forge", "planks")))
                .add(woodSet.getPlanksBlock().get());
        this.tag(TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation("forge", "logs")))
                .add(woodSet.getLogBlock().get());
        this.tag(TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation("forge", "wood")))
                .add(woodSet.getWoodBlock().get());
        this.tag(TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation("forge", "stripped_logs")))
                .add(woodSet.getStrippedLogBlock().get());
        this.tag(TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation("forge", "stripped_wood")))
                .add(woodSet.getStrippedWoodBlock().get());

        this.tag(woodSet.getLogBlockTag())
                .add(woodSet.getLogBlock().get())
                .add(woodSet.getWoodBlock().get())
                .add(woodSet.getStrippedLogBlock().get())
                .add(woodSet.getStrippedWoodBlock().get());
    }
}
