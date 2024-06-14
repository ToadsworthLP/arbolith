package com.toastworth.arbolith.datagen;

import com.toastworth.arbolith.Arbolith;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Arbolith.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ArbolithDataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(true, new ArbolithLootTableProvider(generator));
        generator.addProvider(true, new ArbolithBlockStateProvider(generator, existingFileHelper));
        generator.addProvider(true, new ArbolithItemModelProvider(generator, existingFileHelper));
        BlockTagsProvider blockTagsProvider = new ArbolithBlockTagProvider(generator, existingFileHelper);
        generator.addProvider(true, blockTagsProvider);
        generator.addProvider(true, new ArbolithItemTagProvider(generator, blockTagsProvider, Arbolith.MOD_ID, existingFileHelper));
        generator.addProvider(true, new ArbolithRecipeProvider(generator));
        generator.addProvider(true, new ArbolithLanguageProvider(generator, Arbolith.MOD_ID, "en_us"));
    }
}