package com.toastworth.arbolith.tree;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class TreeGrowerListEntry {
    public ResourceKey<ConfiguredFeature<?, ?>> key;
    public ResourceKey<ConfiguredFeature<?, ?>> keyBees;
    public TreeConfigurationProvider value;
    public int weight;
    public boolean isMega;

    public TreeGrowerListEntry(ResourceKey<ConfiguredFeature<?, ?>> key, ResourceKey<ConfiguredFeature<?, ?>> keyBees, TreeConfigurationProvider value, int weight, boolean isMega) {
        this.key = key;
        this.keyBees = keyBees;
        this.value = value;
        this.weight = weight;
        this.isMega = isMega;
    }
}