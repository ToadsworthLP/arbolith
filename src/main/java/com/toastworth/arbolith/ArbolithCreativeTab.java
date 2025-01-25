package com.toastworth.arbolith;

import com.toastworth.arbolith.tree.TreeTypes;
import com.toastworth.arbolith.wood.WoodSets;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ArbolithCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Arbolith.MOD_ID);
    public static final RegistryObject<CreativeModeTab> ARBOLITH_TAB = CREATIVE_TABS.register(
            "arbolith",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(TreeTypes.RED_MAPLE_TREE_TYPE.getSaplingBlock().get().asItem()))
            .title(Component.translatable("creativetab.arbolith"))
            .displayItems((parameters, output) -> {
                TreeTypes.TREE_TYPES.forEach(treeType -> {
                    output.accept(treeType.getSaplingBlock().get().asItem());
                    output.accept(treeType.getLeavesBlock().get());
                });

                WoodSets.WOOD_SETS.forEach(woodSet -> {
                   output.accept(woodSet.getLogBlock().get());
                   output.accept(woodSet.getWoodBlock().get());
                   output.accept(woodSet.getStrippedLogBlock().get());
                   output.accept(woodSet.getStrippedWoodBlock().get());
                   output.accept(woodSet.getPlanksBlock().get());
                   output.accept(woodSet.getSlabBlock().get());
                   output.accept(woodSet.getStairsBlock().get());
                   output.accept(woodSet.getFenceBlock().get());
                   output.accept(woodSet.getFenceGateBlock().get());
                   output.accept(woodSet.getButtonBlock().get());
                   output.accept(woodSet.getPressurePlateBlock().get());
                   output.accept(woodSet.getDoorBlock().get());
                   output.accept(woodSet.getTrapdoorBlock().get());
                   output.accept(woodSet.getSignItem().get());
                   output.accept(woodSet.getHangingSignItem().get());
                });
            })
            .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_TABS.register(eventBus);
    }
}
