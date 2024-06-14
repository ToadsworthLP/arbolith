package com.toastworth.arbolith.datagen;

import com.toastworth.arbolith.Arbolith;
import com.toastworth.arbolith.wood.WoodSet;
import com.toastworth.arbolith.wood.WoodSets;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ArbolithItemModelProvider extends ItemModelProvider {
    public ArbolithItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Arbolith.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        WoodSets.WOOD_SETS.forEach(this::addWoodSetItems);
    }

    private void addWoodSetItems(WoodSet woodSet) {
        basicItem(woodSet.getSignItem().get());
    }

//    private ItemModelBuilder simpleItem(Item item) {
//        return withExistingParent(item.getPath(),
//                new ResourceLocation("item/generated")).texture("layer0",
//                new ResourceLocation(Arbolith.MOD_ID,"item/" + item.getRegistryName().getPath()));
//    }
//
//    private ItemModelBuilder handheldItem(Item item) {
//        return withExistingParent(item.getRegistryName().getPath(),
//                new ResourceLocation("item/handheld")).texture("layer0",
//                new ResourceLocation(Arbolith.MOD_ID,"item/" + item.getRegistryName().getPath()));
//    }
}
