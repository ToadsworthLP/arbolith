package com.toastworth.arbolith.wood;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class WoodSet {
    private String name;
    private MaterialColor woodColor;
    private MaterialColor barkColor;
    private RegistryObject<Block> logBlock;
    private RegistryObject<Block> woodBlock;
    private RegistryObject<Block> strippedLogBlock;
    private RegistryObject<Block> strippedWoodBlock;
    private RegistryObject<Block> planksBlock;
    private RegistryObject<Block> slabBlock;
    private RegistryObject<Block> stairsBlock;
    private RegistryObject<Block> fenceBlock;
    private RegistryObject<Block> fenceGateBlock;
    private RegistryObject<Block> buttonBlock;
    private RegistryObject<Block> pressurePlateBlock;
    private RegistryObject<Block> doorBlock;
    private RegistryObject<Block> trapdoorBlock;

    public WoodSet(String name, MaterialColor woodColor, MaterialColor barkColor) {
        this.name = name;
        this.woodColor = woodColor;
        this.barkColor = barkColor;
    }

    public void register(DeferredRegister<Block> blockDeferredRegister) {
        strippedLogBlock = blockDeferredRegister.register("stripped_" + name + "_log", () -> new LogBlock(woodColor, woodColor));
        strippedWoodBlock = blockDeferredRegister.register("stripped_" + name + "_wood", () -> new WoodBlock(woodColor));
        logBlock = blockDeferredRegister.register(name + "_log", () -> new LogBlock(woodColor, barkColor, strippedLogBlock.get()));
        woodBlock = blockDeferredRegister.register(name + "_wood", () -> new WoodBlock(woodColor, strippedWoodBlock.get()));
        planksBlock = blockDeferredRegister.register(name + "_planks", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD, woodColor).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
        slabBlock = blockDeferredRegister.register(name + "_slab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, woodColor).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
        stairsBlock = blockDeferredRegister.register(name + "_stairs", () -> new StairBlock(planksBlock.get().defaultBlockState(), BlockBehaviour.Properties.copy(planksBlock.get())));
        fenceBlock = blockDeferredRegister.register(name + "_fence", () -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, planksBlock.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
        fenceGateBlock = blockDeferredRegister.register(name + "_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, planksBlock.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
        buttonBlock = blockDeferredRegister.register(name + "_button", () -> new WoodButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)));
        pressurePlateBlock = blockDeferredRegister.register(name + "_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, planksBlock.get().defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD)));
        doorBlock = blockDeferredRegister.register(name + "_door", () -> new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, planksBlock.get().defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()));
        trapdoorBlock = blockDeferredRegister.register(name + "_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, woodColor).strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(WoodSet::never)));
    }

    public String getName() {
        return name;
    }

    public MaterialColor getWoodColor() {
        return woodColor;
    }

    public MaterialColor getBarkColor() {
        return barkColor;
    }

    public RegistryObject<Block> getLogBlock() {
        return logBlock;
    }

    public RegistryObject<Block> getWoodBlock() {
        return woodBlock;
    }

    public RegistryObject<Block> getStrippedLogBlock() {
        return strippedLogBlock;
    }

    public RegistryObject<Block> getStrippedWoodBlock() {
        return strippedWoodBlock;
    }

    public RegistryObject<Block> getPlanksBlock() {
        return planksBlock;
    }

    public RegistryObject<Block> getSlabBlock() {
        return slabBlock;
    }

    public RegistryObject<Block> getStairsBlock() {
        return stairsBlock;
    }

    public RegistryObject<Block> getFenceBlock() {
        return fenceBlock;
    }

    public RegistryObject<Block> getFenceGateBlock() {
        return fenceGateBlock;
    }

    public RegistryObject<Block> getButtonBlock() {
        return buttonBlock;
    }

    public RegistryObject<Block> getPressurePlateBlock() {
        return pressurePlateBlock;
    }

    public RegistryObject<Block> getDoorBlock() {
        return doorBlock;
    }

    public RegistryObject<Block> getTrapdoorBlock() {
        return trapdoorBlock;
    }

    private static Boolean never(BlockState p_50779_, BlockGetter p_50780_, BlockPos p_50781_, EntityType<?> p_50782_) {
        return false;
    }
}
