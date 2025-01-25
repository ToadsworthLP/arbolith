package com.toastworth.arbolith.tree;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraftforge.registries.RegistryObject;

@FunctionalInterface
public interface TreeConfigurationProvider {
    TreeConfiguration get(RegistryObject<Block> leavesBlock, boolean hasBees);
}