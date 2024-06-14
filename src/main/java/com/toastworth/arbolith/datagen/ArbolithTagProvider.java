package com.toastworth.arbolith.datagen;

import com.toastworth.arbolith.Arbolith;
import com.toastworth.arbolith.wood.WoodSet;
import com.toastworth.arbolith.wood.WoodSets;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class ArbolithTagProvider extends BlockTagsProvider {
    public ArbolithTagProvider(DataGenerator pGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, Arbolith.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        WoodSets.WOOD_SETS.forEach(this::addWoodSetTags);
    }

    private void addWoodSetTags(WoodSet woodSet) {
        this.tag(BlockTags.FENCES).add(woodSet.getFenceBlock().get());
        this.tag(BlockTags.WOODEN_FENCES).add(woodSet.getFenceBlock().get());

        this.tag(BlockTags.FENCE_GATES).add(woodSet.getFenceGateBlock().get());
    }
}
