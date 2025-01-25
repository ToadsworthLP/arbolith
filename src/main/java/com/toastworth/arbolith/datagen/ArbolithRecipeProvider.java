package com.toastworth.arbolith.datagen;

import com.toastworth.arbolith.Arbolith;
import com.toastworth.arbolith.wood.WoodSet;
import com.toastworth.arbolith.wood.WoodSets;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraft.world.level.block.Blocks;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class ArbolithRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ArbolithRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        WoodSets.WOOD_SETS.forEach(woodSet -> addWoodSetRecipes(woodSet, pFinishedRecipeConsumer));
    }

    private void addWoodSetRecipes(WoodSet woodSet, Consumer<FinishedRecipe> consumer) {
        woodFromLogs(consumer, woodSet.getWoodBlock().get(), woodSet.getLogBlock().get());
        woodFromLogs(consumer, woodSet.getStrippedWoodBlock().get(), woodSet.getStrippedLogBlock().get());
        planksFromLogs(consumer, woodSet.getPlanksBlock().get(), woodSet.getLogItemTag(), 4);
        woodenStairs(consumer, woodSet.getStairsBlock().get(), woodSet.getPlanksBlock().get());
        woodenSlab(consumer, woodSet.getSlabBlock().get(), woodSet.getPlanksBlock().get());
        woodenFence(consumer, woodSet.getFenceBlock().get(), woodSet.getPlanksBlock().get());
        woodenDoor(consumer, woodSet.getDoorBlock().get(), woodSet.getPlanksBlock().get());
        woodenFenceGate(consumer, woodSet.getFenceGateBlock().get(), woodSet.getPlanksBlock().get());
        woodenTrapdoor(consumer, woodSet.getTrapdoorBlock().get(), woodSet.getPlanksBlock().get());
        pressurePlate(consumer, woodSet.getPressurePlateBlock().get(), woodSet.getPlanksBlock().get());
        woodenButton(consumer, woodSet.getButtonBlock().get(), woodSet.getPlanksBlock().get());
        woodenSign(consumer, woodSet.getSignItem().get(), woodSet.getPlanksBlock().get());
        hangingSign(consumer, woodSet.getHangingSignItem().get(), woodSet.getPlanksBlock().get());
    }

    protected static void woodFromOtherItem(Consumer<FinishedRecipe> consumer, ItemLike item, ItemLike item2) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, item, 3).define('#', item2).pattern("##").pattern("##").group("bark").unlockedBy("has_log", has(item2)).save(consumer, new ResourceLocation(Arbolith.MOD_ID, getConversionRecipeName(item,item2)));
    }

    protected static void planksFromOneLog(Consumer<FinishedRecipe> p_259712_, ItemLike p_259052_, ItemLike p_259045_, int i) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, p_259052_, i).requires(p_259045_).group("planks").unlockedBy("has_log", has(p_259045_)).save(p_259712_);
    }
    protected static void woodenButton(Consumer<FinishedRecipe> consumer, ItemLike item, ItemLike item2) {
        buttonBuilder(item, Ingredient.of(item2)).group("wooden_button").unlockedBy(getHasName(item2), has(item2)).save(consumer, new ResourceLocation(Arbolith.MOD_ID, getItemName(item)));
    }

    protected static void woodenSign(Consumer<FinishedRecipe> consumer, ItemLike item, ItemLike item2) {
        signBuilder(item, Ingredient.of(item2)).group("wooden_sign").unlockedBy(getHasName(item2), has(item2)).save(consumer, new ResourceLocation(Arbolith.MOD_ID, getItemName(item)));
    }

    protected static void chestBoat(Consumer<FinishedRecipe> p_236372_, ItemLike p_236373_, ItemLike p_236374_) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, p_236373_).requires(Blocks.CHEST).requires(p_236374_).group("chest_boat").unlockedBy("has_boat", has(ItemTags.BOATS)).save(p_236372_);
    }

    protected static void hangingSign(Consumer<FinishedRecipe> consumer, ItemLike pSign, ItemLike pMaterial) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, pSign, 6).group("hanging_sign").define('#', pMaterial).define('X', Items.CHAIN).pattern("X X").pattern("###").pattern("###").unlockedBy("has_stripped_logs", has(pMaterial)).save(consumer);
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
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, item, Ingredient.of(item2)).group("wooden_slab").unlockedBy(getHasName(item2), has(item2)).save(consumer, new ResourceLocation(Arbolith.MOD_ID, getItemName(item)));
    }

    protected static void woodenStairs(Consumer<FinishedRecipe> consumer, ItemLike item, ItemLike item2) {
        stairBuilder(item, Ingredient.of(item2)).group("wooden_stairs").unlockedBy(getHasName(item2), has(item2)).save(consumer, new ResourceLocation(Arbolith.MOD_ID, getItemName(item)));
    }

    protected static void woodenTrapdoor(Consumer<FinishedRecipe> consumer, ItemLike item, ItemLike item2) {
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, item, 2).define('#', item2).pattern("###").pattern("###").group("wooden_trapdoor").unlockedBy(getHasName(item2), has(item2)).save(consumer, new ResourceLocation(Arbolith.MOD_ID, getItemName(item)));
    }

    protected static void pressurePlate(Consumer<FinishedRecipe> consumer, ItemLike item, ItemLike item2) {
        pressurePlateBuilder(RecipeCategory.REDSTONE, item, Ingredient.of(item2)).group("wooden_pressure_plate").unlockedBy(getHasName(item2), has(item2)).save(consumer, new ResourceLocation(Arbolith.MOD_ID, getItemName(item)));
    }

    protected static void oneToOneConversionRecipe(Consumer<FinishedRecipe> consumer, ItemLike item, ItemLike item2, @Nullable String group, int amount) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, item, amount).requires(item2).group(group).unlockedBy(getHasName(item2), has(item2)).save(consumer, new ResourceLocation(Arbolith.MOD_ID, getConversionRecipeName(item, item2)));
    }
}
