package com.toastworth.arbolith.wood;

import com.toastworth.arbolith.Arbolith;
import com.toastworth.arbolith.RenderTypes;
import com.toastworth.arbolith.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class WoodSet {
    private String name;
    private String displayName;
    private MapColor woodColor;
    private MapColor barkColor;
    private BlockSetType setType;
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
    private RegistryObject<Block> hangingSignBlock;
    private RegistryObject<Block> wallHangingSignBlock;
    private RegistryObject<Item> signItem;
    private RegistryObject<Item> hangingSignItem;
    private TagKey<Block> logBlockTag;
    private TagKey<Item> logItemTag;

    public WoodSet(String name, String displayName, MapColor woodColor, MapColor barkColor) {
        this.name = name;
        this.displayName = displayName;
        this.woodColor = woodColor;
        this.barkColor = barkColor;
        this.setType = BlockSetType.register(new BlockSetType(new ResourceLocation(Arbolith.MOD_ID, name).toString()));
        this.woodType = WoodType.register(new WoodType(new ResourceLocation(Arbolith.MOD_ID, name).toString(), setType));
    }

    public WoodSet(String name, String displayName, MapColor woodColor, MapColor barkColor, String doorRenderType) {
        this.name = name;
        this.displayName = displayName;
        this.woodColor = woodColor;
        this.barkColor = barkColor;
        this.setType = BlockSetType.register(new BlockSetType(new ResourceLocation(Arbolith.MOD_ID, name).toString()));
        this.woodType = WoodType.register(new WoodType(new ResourceLocation(Arbolith.MOD_ID, name).toString(), setType));
        this.doorRenderType = doorRenderType;
    }

    public void addToDeferredRegister(DeferredRegister<Block> blockDeferredRegister, DeferredRegister<Item> itemDeferredRegister) {
        logBlockTag = TagKey.create(Registries.BLOCK, new ResourceLocation(Arbolith.MOD_ID, name + "_logs"));
        logItemTag = TagKey.create(Registries.ITEM, new ResourceLocation(Arbolith.MOD_ID, name + "_logs"));

        logBlock = blockDeferredRegister.register(name + "_log", () -> new LogBlock(woodColor, barkColor));
        woodBlock = blockDeferredRegister.register(name + "_wood", () -> new WoodBlock(woodColor));
        strippedLogBlock = blockDeferredRegister.register("stripped_" + name + "_log", () -> new LogBlock(woodColor, woodColor));
        strippedWoodBlock = blockDeferredRegister.register("stripped_" + name + "_wood", () -> new WoodBlock(woodColor));
        planksBlock = blockDeferredRegister.register(name + "_planks", () -> new Block(BlockBehaviour.Properties.of().mapColor(woodColor).ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
        slabBlock = blockDeferredRegister.register(name + "_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(woodColor).ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
        stairsBlock = blockDeferredRegister.register(name + "_stairs", () -> new StairBlock(planksBlock.get().defaultBlockState(), BlockBehaviour.Properties.copy(planksBlock.get())));
        fenceBlock = blockDeferredRegister.register(name + "_fence", () -> new FenceBlock(BlockBehaviour.Properties.of().mapColor(woodColor).ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
        fenceGateBlock = blockDeferredRegister.register(name + "_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.of().mapColor(woodColor).ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD), woodType));
        buttonBlock = blockDeferredRegister.register(name + "_button", () -> new ButtonBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).noCollission().strength(0.5F).sound(SoundType.WOOD), woodType.setType(), 30, true));
        pressurePlateBlock = blockDeferredRegister.register(name + "_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of().mapColor(woodColor).ignitedByLava().instrument(NoteBlockInstrument.BASS).noCollission().strength(0.5F).sound(SoundType.WOOD), woodType.setType()));
        doorBlock = blockDeferredRegister.register(name + "_door", () -> new DoorBlock(BlockBehaviour.Properties.of().mapColor(woodColor).ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(3.0F).sound(SoundType.WOOD).noOcclusion(), woodType.setType()));
        trapdoorBlock = blockDeferredRegister.register(name + "_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.of().mapColor(woodColor).ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(WoodSet::never), woodType.setType()));
        signBlock = blockDeferredRegister.register(name + "_sign", () -> new ArbolithStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), woodType));
        wallSignBlock = blockDeferredRegister.register(name + "_wall_sign", () -> new ArbolithWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), woodType));
        hangingSignBlock = blockDeferredRegister.register(name + "_hanging_sign", () -> new ArbolithHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), woodType));
        wallHangingSignBlock = blockDeferredRegister.register(name + "_wall_hanging_sign", () -> new ArbolithWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), woodType));

        signItem = itemDeferredRegister.register(name + "_sign", () -> new SignItem(new Item.Properties().stacksTo(16), signBlock.get(), wallSignBlock.get()));
        hangingSignItem = itemDeferredRegister.register(name + "_hanging_sign", () -> new HangingSignItem(hangingSignBlock.get(), wallHangingSignBlock.get(), new Item.Properties().stacksTo(16)));

        StrippableBlocks.add(logBlock, strippedLogBlock);
        StrippableBlocks.add(woodBlock, strippedWoodBlock);
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public MapColor getWoodColor() {
        return woodColor;
    }

    public MapColor getBarkColor() {
        return barkColor;
    }

    public BlockSetType getSetType() {
        return setType;
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

    public RegistryObject<Block> getHangingSignBlock() {
        return hangingSignBlock;
    }

    public RegistryObject<Block> getWallHangingSignBlock() {
        return wallHangingSignBlock;
    }

    public RegistryObject<Item> getSignItem() {
        return signItem;
    }

    public RegistryObject<Item> getHangingSignItem() {
        return hangingSignItem;
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
