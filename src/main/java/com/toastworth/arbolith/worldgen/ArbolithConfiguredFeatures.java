package com.toastworth.arbolith.worldgen;

import com.toastworth.arbolith.Arbolith;
import com.toastworth.arbolith.tree.TreeTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class ArbolithConfiguredFeatures {
    public static void bootstrap(BootstapContext<ConfiguredFeature<?,?>> context) {
        TreeTypes.TREE_TYPES.forEach(treeType -> {
            treeType.getTreeConfigurations().forEach(treeConfiguration -> {
                register(context, treeConfiguration.key, Feature.TREE, treeConfiguration.value.get(treeType.getLeavesBlock(), false));

                if(treeConfiguration.keyBees != null) {
                    register(context, treeConfiguration.keyBees, Feature.TREE, treeConfiguration.value.get(treeType.getLeavesBlock(), true));
                }
            });
        });
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Arbolith.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
