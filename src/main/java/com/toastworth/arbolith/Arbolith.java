package com.toastworth.arbolith;

import com.mojang.logging.LogUtils;
import com.toastworth.arbolith.block.StrippableBlocks;
import com.toastworth.arbolith.block.entity.ArbolithHangingSignBlockEntity;
import com.toastworth.arbolith.block.entity.ArbolithSignBlockEntity;
import com.toastworth.arbolith.tree.TreeTypes;
import com.toastworth.arbolith.wood.WoodSets;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

import java.util.function.Supplier;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Arbolith.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class Arbolith
{
    public static final String MOD_ID = "arbolith";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MOD_ID);

    public static final RegistryObject<BlockEntityType<ArbolithSignBlockEntity>> SIGN_BLOCK_ENTITY = BLOCK_ENTITIES.register("sign_block_entity",
            () -> BlockEntityType.Builder.of(ArbolithSignBlockEntity::new, WoodSets.getSignBlocks()).build(null));

    public static final RegistryObject<BlockEntityType<ArbolithHangingSignBlockEntity>> HANGING_SIGN_BLOCK_ENTITY = BLOCK_ENTITIES.register("hanging_sign_block_entity",
            () -> BlockEntityType.Builder.of(ArbolithHangingSignBlockEntity::new, WoodSets.getHangingSignBlocks()).build(null));

    public Arbolith() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);

        WoodSets.addToDeferredRegister(BLOCKS, ITEMS);
        TreeTypes.addToDeferredRegister(BLOCKS);

        ArbolithCreativeTab.register(modEventBus);

        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        BLOCK_ENTITIES.register(modEventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            WoodSets.WOOD_SETS.forEach(woodSet -> WoodType.register(woodSet.getWoodType()));
            StrippableBlocks.setup();
        });
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            WoodSets.WOOD_SETS.forEach(woodSet -> Sheets.addWoodType(woodSet.getWoodType()));
            BlockEntityRenderers.register(SIGN_BLOCK_ENTITY.get(), SignRenderer::new);
            BlockEntityRenderers.register(HANGING_SIGN_BLOCK_ENTITY.get(), HangingSignRenderer::new);
        });
    }

    @SubscribeEvent
    public static void onRegisterItems(final RegisterEvent event) {
        if (event.getRegistryKey().equals(ForgeRegistries.Keys.ITEMS)){
            BLOCKS.getEntries().forEach( (blockRegistryObject) -> {
                Block block = blockRegistryObject.get();
                if(block instanceof SignBlock) return;
                if(block instanceof FlowerPotBlock) return;

                Item.Properties properties = new Item.Properties();
                Supplier<Item> blockItemFactory = () -> new BlockItem(block, properties);
                event.register(ForgeRegistries.Keys.ITEMS, blockRegistryObject.getId(), blockItemFactory);
            });
        }
    }
}
