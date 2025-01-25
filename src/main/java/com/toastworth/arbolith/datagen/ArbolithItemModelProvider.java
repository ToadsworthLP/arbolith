package com.toastworth.arbolith.datagen;

import com.toastworth.arbolith.Arbolith;
import com.toastworth.arbolith.wood.WoodSet;
import com.toastworth.arbolith.wood.WoodSets;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ArbolithItemModelProvider extends ItemModelProvider {
    public ArbolithItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Arbolith.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        WoodSets.WOOD_SETS.forEach(this::addWoodSetItems);
    }

    private void addWoodSetItems(WoodSet woodSet) {
        basicItem(woodSet.getSignItem().get());
        basicItem(woodSet.getHangingSignItem().get());
    }
}
