package com.toastworth.arbolith.datagen;

import com.toastworth.arbolith.Arbolith;
import com.toastworth.arbolith.tree.TreeType;
import com.toastworth.arbolith.tree.TreeTypes;
import com.toastworth.arbolith.wood.WoodSet;
import com.toastworth.arbolith.wood.WoodSets;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class ArbolithLanguageProvider extends LanguageProvider {
    public ArbolithLanguageProvider(PackOutput output, String modid, String locale) {
        super(output, modid, locale);
    }

    @Override
    protected void addTranslations() {
        this.add("creativetab." + Arbolith.MOD_ID, "Arbolith");

        WoodSets.WOOD_SETS.forEach(this::addWoodSetEnglishTranslations);
        TreeTypes.TREE_TYPES.forEach(this::addTreeEnglishTranslations);
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
        this.add(woodSet.getHangingSignBlock().get(), woodSet.getDisplayName() + " Hanging Sign");
    }

    private void addTreeEnglishTranslations(TreeType treeType) {
        this.add(treeType.getLeavesBlock().get(), treeType.getDisplayName() + " Leaves");
        this.add(treeType.getSaplingBlock().get(), treeType.getDisplayName() + " Sapling");
        this.add(treeType.getPottedSaplingBlock().get(), "Potted " + treeType.getDisplayName() + " Sapling");
    }
}