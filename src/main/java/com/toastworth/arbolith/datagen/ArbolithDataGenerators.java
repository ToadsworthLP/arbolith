package com.toastworth.arbolith.datagen;

import com.toastworth.arbolith.Arbolith;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = Arbolith.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ArbolithDataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeServer(), new ArbolithRecipeProvider(packOutput));
        generator.addProvider(event.includeServer(), ArbolithLootTableProvider.create(packOutput));
        generator.addProvider(event.includeClient(), new ArbolithBlockStateProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new ArbolithItemModelProvider(packOutput, existingFileHelper));
        ArbolithBlockTagProvider blockTagProvider = generator.addProvider(event.includeServer(), new ArbolithBlockTagProvider(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new ArbolithItemTagProvider(packOutput, lookupProvider, blockTagProvider.contentsGetter(), existingFileHelper));
        generator.addProvider(true, new ArbolithLanguageProvider(packOutput, Arbolith.MOD_ID, "en_us"));
        generator.addProvider(event.includeServer(), new ArbolithWorldGenProvider(packOutput, lookupProvider));
    }
}