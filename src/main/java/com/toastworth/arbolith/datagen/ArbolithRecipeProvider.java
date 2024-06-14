package com.toastworth.arbolith.datagen;

import com.toastworth.arbolith.Arbolith;
import com.toastworth.arbolith.wood.WoodSet;
import com.toastworth.arbolith.wood.WoodSets;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCookingSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraft.world.level.block.Blocks;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;

public class ArbolithRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ArbolithRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        WoodSets.WOOD_SETS.forEach(woodSet -> addWoodSetRecipes(woodSet, pFinishedRecipeConsumer));
    }

    private void addWoodSetRecipes(WoodSet woodSet, Consumer<FinishedRecipe> consumer) {
        woodFromLogs(consumer, woodSet.getWoodBlock().get(), woodSet.getLogBlock().get());
        woodFromLogs(consumer, woodSet.getStrippedWoodBlock().get(), woodSet.getStrippedLogBlock().get());
        planksFromLogs(consumer, woodSet.getPlanksBlock().get(), woodSet.getLogItemTag());
        woodenStairs(consumer, woodSet.getStairsBlock().get(), woodSet.getPlanksBlock().get());
        woodenSlab(consumer, woodSet.getSlabBlock().get(), woodSet.getPlanksBlock().get());
        woodenFence(consumer, woodSet.getFenceBlock().get(), woodSet.getPlanksBlock().get());
        woodenDoor(consumer, woodSet.getDoorBlock().get(), woodSet.getPlanksBlock().get());
        woodenFenceGate(consumer, woodSet.getFenceGateBlock().get(), woodSet.getPlanksBlock().get());
        woodenTrapdoor(consumer, woodSet.getTrapdoorBlock().get(), woodSet.getPlanksBlock().get());
        pressurePlate(consumer, woodSet.getPressurePlateBlock().get(), woodSet.getPlanksBlock().get());
        woodenButton(consumer, woodSet.getButtonBlock().get(), woodSet.getPlanksBlock().get());
        woodenSign(consumer, woodSet.getSignItem().get(), woodSet.getPlanksBlock().get());
    }

    protected static void woodFromOtherItem(Consumer<FinishedRecipe> consumer, ItemLike item, ItemLike item2) {
        ShapedRecipeBuilder.shaped(item, 3).define('#', item2).pattern("##").pattern("##").group("bark").unlockedBy("has_log", has(item2)).save(consumer, new ResourceLocation(Arbolith.MOD_ID, getConversionRecipeName(item,item2)));
    }

    protected static void planksFromOneLog(Consumer<FinishedRecipe> p_259712_, ItemLike p_259052_, ItemLike p_259045_, int i) {
        ShapelessRecipeBuilder.shapeless(p_259052_, i).requires(p_259045_).group("planks").unlockedBy("has_log", has(p_259045_)).save(p_259712_);
    }
    protected static void woodenButton(Consumer<FinishedRecipe> consumer, ItemLike item, ItemLike item2) {
        buttonBuilder(item, Ingredient.of(item2)).group("wooden_button").unlockedBy(getHasName(item2), has(item2)).save(consumer, new ResourceLocation(Arbolith.MOD_ID, getItemName(item)));
    }

    protected static void woodenSign(Consumer<FinishedRecipe> consumer, ItemLike item, ItemLike item2) {
        signBuilder(item, Ingredient.of(item2)).group("wooden_sign").unlockedBy(getHasName(item2), has(item2)).save(consumer, new ResourceLocation(Arbolith.MOD_ID, getItemName(item)));
    }

    private static void chestBoat(Consumer<FinishedRecipe> p_236372_, ItemLike p_236373_, ItemLike p_236374_) {
        ShapelessRecipeBuilder.shapeless(p_236373_).requires(Blocks.CHEST).requires(p_236374_).group("chest_boat").unlockedBy("has_boat", has(ItemTags.BOATS)).save(p_236372_);
    }

    protected static void hangingSign(Consumer<FinishedRecipe> consumer, ItemLike item, ItemLike item2) {
        ShapedRecipeBuilder.shaped(item, 6).group("hanging_sign").define('#', item2).define('X', Items.CHAIN).pattern("X X").pattern("###").pattern("###").unlockedBy(getHasName(item2), has(item2)).save(consumer, new ResourceLocation(Arbolith.MOD_ID, getItemName(item)));
    }

    protected static void woodenDoor(Consumer<FinishedRecipe> consumer, ItemLike item, ItemLike item2) {
        doorBuilder(item, Ingredient.of(item2)).group("wooden_door").unlockedBy(getHasName(item2), has(item2)).save(consumer, new ResourceLocation(Arbolith.MOD_ID, getItemName(item)));
    }

    protected static void woodenFenceGate(Consumer<FinishedRecipe> consumer, ItemLike item, ItemLike item2) {
        fenceGateBuilder(item, Ingredient.of(item2)).group("wooden_fence_gate").unlockedBy(getHasName(item2), has(item2)).save(consumer, new ResourceLocation(Arbolith.MOD_ID, getItemName(item)));
    }

    protected static void woodenFence(Consumer<FinishedRecipe> consumer, ItemLike item, ItemLike item2) {
        fenceBuilder(item, Ingredient.of(item2)).group("wooden_fence").unlockedBy(getHasName(item2), has(item2)).save(consumer, new ResourceLocation(Arbolith.MOD_ID, getItemName(item)));
    }

    protected static void woodenSlab(Consumer<FinishedRecipe> consumer, ItemLike item, ItemLike item2) {
        slabBuilder(item, Ingredient.of(item2)).group("wooden_slab").unlockedBy(getHasName(item2), has(item2)).save(consumer, new ResourceLocation(Arbolith.MOD_ID, getItemName(item)));
    }

    protected static void woodenStairs(Consumer<FinishedRecipe> consumer, ItemLike item, ItemLike item2) {
        stairBuilder(item, Ingredient.of(item2)).group("wooden_stairs").unlockedBy(getHasName(item2), has(item2)).save(consumer, new ResourceLocation(Arbolith.MOD_ID, getItemName(item)));
    }

    protected static void woodenTrapdoor(Consumer<FinishedRecipe> consumer, ItemLike item, ItemLike item2) {
        ShapedRecipeBuilder.shaped(item, 2).define('#', item2).pattern("###").pattern("###").group("wooden_trapdoor").unlockedBy(getHasName(item2), has(item2)).save(consumer, new ResourceLocation(Arbolith.MOD_ID, getItemName(item)));
    }

    protected static void pressurePlate(Consumer<FinishedRecipe> consumer, ItemLike item, ItemLike item2) {
        pressurePlateBuilder(item, Ingredient.of(item2)).group("wooden_pressure_plate").unlockedBy(getHasName(item2), has(item2)).save(consumer, new ResourceLocation(Arbolith.MOD_ID, getItemName(item)));
    }

    protected static void oreCooking(Consumer<FinishedRecipe> consumer, SimpleCookingSerializer<?> serializer, List<ItemLike> itemList, ItemLike item, float f, int i, String s, String s2) {
        for(ItemLike itemlike : itemList) {
            SimpleCookingRecipeBuilder.cooking(Ingredient.of(itemlike), item, f, i, serializer).group(s).unlockedBy(getHasName(itemlike), has(itemlike)).save(consumer, new ResourceLocation(Arbolith.MOD_ID, getItemName(item) + s2 + "_" + getItemName(itemlike)));
        }

    }

    protected static void oneToOneConversionRecipe(Consumer<FinishedRecipe> consumer, ItemLike item, ItemLike item2, @Nullable String group) {
        oneToOneConversionRecipe(consumer, item, item2, group, 1);
    }

    protected static void oneToOneConversionRecipe(Consumer<FinishedRecipe> consumer, ItemLike item, ItemLike item2, @Nullable String group, int ammount) {
        ShapelessRecipeBuilder.shapeless(item, ammount).requires(item2).group(group).unlockedBy(getHasName(item2), has(item2)).save(consumer, new ResourceLocation(Arbolith.MOD_ID, getConversionRecipeName(item, item2)));
    }
}
