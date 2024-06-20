package com.toastworth.arbolith.wood;

import com.toastworth.arbolith.Arbolith;
import com.toastworth.arbolith.ArbolithCreativeTab;
import com.toastworth.arbolith.RenderTypes;
import com.toastworth.arbolith.block.ArbolithStandingSignBlock;
import com.toastworth.arbolith.block.ArbolithWallSignBlock;
import com.toastworth.arbolith.block.StrippableBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class WoodSet {
    private String name;
    private String displayName;
    private MaterialColor woodColor;
    private MaterialColor barkColor;
    private WoodType woodType;
    private String doorRenderType = RenderTypes.SOLID;
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
    private RegistryObject<Block> signBlock;
    private RegistryObject<Block> wallSignBlock;
    private RegistryObject<Item> signItem;
    private TagKey<Block> logBlockTag;
    private TagKey<Item> logItemTag;

    public WoodSet(String name, String displayName, MaterialColor woodColor, MaterialColor barkColor) {
        this.name = name;
        this.displayName = displayName;
        this.woodColor = woodColor;
        this.barkColor = barkColor;
        this.woodType = WoodType.create(name);
    }

    public WoodSet(String name, String displayName, MaterialColor woodColor, MaterialColor barkColor, String doorRenderType) {
        this.name = name;
        this.displayName = displayName;
        this.woodColor = woodColor;
        this.barkColor = barkColor;
        this.woodType = WoodType.create(name);
        this.doorRenderType = doorRenderType;
    }

    public void addToDeferredRegister(DeferredRegister<Block> blockDeferredRegister, DeferredRegister<Item> itemDeferredRegister) {
        logBlockTag = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(Arbolith.MOD_ID, name + "_logs"));
        logItemTag = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(Arbolith.MOD_ID, name + "_logs"));

        logBlock = blockDeferredRegister.register(name + "_log", () -> new LogBlock(woodColor, barkColor));
        woodBlock = blockDeferredRegister.register(name + "_wood", () -> new WoodBlock(woodColor));
        strippedLogBlock = blockDeferredRegister.register("stripped_" + name + "_log", () -> new LogBlock(woodColor, woodColor));
        strippedWoodBlock = blockDeferredRegister.register("stripped_" + name + "_wood", () -> new WoodBlock(woodColor));
        planksBlock = blockDeferredRegister.register(name + "_planks", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD, woodColor).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
        slabBlock = blockDeferredRegister.register(name + "_slab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, woodColor).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
        stairsBlock = blockDeferredRegister.register(name + "_stairs", () -> new StairBlock(planksBlock.get().defaultBlockState(), BlockBehaviour.Properties.copy(planksBlock.get())));
        fenceBlock = blockDeferredRegister.register(name + "_fence", () -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, planksBlock.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
        fenceGateBlock = blockDeferredRegister.register(name + "_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, planksBlock.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
        buttonBlock = blockDeferredRegister.register(name + "_button", () -> new WoodButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)));
        pressurePlateBlock = blockDeferredRegister.register(name + "_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, planksBlock.get().defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD)));
        doorBlock = blockDeferredRegister.register(name + "_door", () -> new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, planksBlock.get().defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()));
        trapdoorBlock = blockDeferredRegister.register(name + "_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, woodColor).strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(WoodSet::never)));
        signBlock = blockDeferredRegister.register(name + "_sign", () -> new ArbolithStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), woodType));
        wallSignBlock = blockDeferredRegister.register(name + "_wall_sign", () -> new ArbolithWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), woodType));

        signItem = itemDeferredRegister.register(name + "_sign", () -> new SignItem(new Item.Properties().tab(ArbolithCreativeTab.INSTANCE).stacksTo(16), signBlock.get(), wallSignBlock.get()));

        StrippableBlocks.add(logBlock, strippedLogBlock);
        StrippableBlocks.add(woodBlock, strippedWoodBlock);
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public MaterialColor getWoodColor() {
        return woodColor;
    }

    public MaterialColor getBarkColor() {
        return barkColor;
    }

    public WoodType getWoodType() {
        return woodType;
    }

    public String getDoorRenderType() {
        return doorRenderType;
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

    public RegistryObject<Block> getSignBlock() {
        return signBlock;
    }

    public RegistryObject<Block> getWallSignBlock() {
        return wallSignBlock;
    }

    public RegistryObject<Item> getSignItem() {
        return signItem;
    }

    public TagKey<Block> getLogBlockTag() {
        return logBlockTag;
    }

    public TagKey<Item> getLogItemTag() {
        return logItemTag;
    }

    private static Boolean never(BlockState p_50779_, BlockGetter p_50780_, BlockPos p_50781_, EntityType<?> p_50782_) {
        return false;
    }
}
