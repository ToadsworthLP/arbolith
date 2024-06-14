package com.toastworth.arbolith.datagen;

import com.toastworth.arbolith.Arbolith;
import com.toastworth.arbolith.wood.WoodSet;
import com.toastworth.arbolith.wood.WoodSets;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ArbolithLanguageProvider extends LanguageProvider {
    public ArbolithLanguageProvider(DataGenerator gen, String modid, String locale) {
        super(gen, modid, locale);
    }

    @Override
    protected void addTranslations() {
        this.add("itemGroup." + Arbolith.MOD_ID, "Arbolith");
        this.add(Arbolith.PINK_PETALS.get(), "Pink Petals");

        WoodSets.WOOD_SETS.forEach(this::addWoodSetEnglishTranslations);
    }

    private void addWoodSetEnglishTranslations(WoodSet woodSet) {
        this.add(woodSet.getLogBlock().get(), woodSet.getDisplayName() + " Log");
        this.add(woodSet.getWoodBlock().get(), woodSet.getDisplayName() + " Wood");
        this.add(woodSet.getStrippedLogBlock().get(), "Stripped " + woodSet.getDisplayName() + " Log");
        this.add(woodSet.getStrippedWoodBlock().get(), "Stripped " + woodSet.getDisplayName() + " Wood");
        this.add(woodSet.getPlanksBlock().get(), woodSet.getDisplayName() + " Planks");
        this.add(woodSet.getSlabBlock().get(), woodSet.getDisplayName() + " Slab");
        this.add(woodSet.getStairsBlock().get(), woodSet.getDisplayName() + " Stairs");
        this.add(woodSet.getFenceBlock().get(), woodSet.getDisplayName() + " Fence");
        this.add(woodSet.getFenceGateBlock().get(), woodSet.getDisplayName() + " Fence Gate");
        this.add(woodSet.getButtonBlock().get(), woodSet.getDisplayName() + " Button");
        this.add(woodSet.getPressurePlateBlock().get(), woodSet.getDisplayName() + " Pressure Plate");
        this.add(woodSet.getDoorBlock().get(), woodSet.getDisplayName() + " Door");
        this.add(woodSet.getTrapdoorBlock().get(), woodSet.getDisplayName() + " Trapdoor");
        this.add(woodSet.getSignBlock().get(), woodSet.getDisplayName() + " Sign");

    }
}
