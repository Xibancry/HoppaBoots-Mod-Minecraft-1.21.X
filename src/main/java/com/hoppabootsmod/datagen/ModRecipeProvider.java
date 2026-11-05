package com.hoppabootsmod.datagen;

import com.hoppabootsmod.HoppaBootsMod;
import com.hoppabootsmod.block.ModBlocks;
import com.hoppabootsmod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    // Provide datagen the new recipes
    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        List<ItemLike> ALUMINUM_SMELTABLES = List.of(ModItems.RAW_ALUMINUM.get(),
                ModBlocks.ALUMINUM_ORE.get(), ModBlocks.ALUMINUM_DEEPSLATE_ORE.get());

        // Recipe for ALUMINUM BLOCK
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ALUMINUM_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.REFINED_ALUMINUM.get())
                .unlockedBy(getHasName(ModItems.REFINED_ALUMINUM.get()), has(ModItems.REFINED_ALUMINUM.get())).save(pRecipeOutput);

        // Recipe for RAW ALUMINUM BLOCK
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RAW_ALUMINUM_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.RAW_ALUMINUM.get())
                .unlockedBy(getHasName(ModItems.RAW_ALUMINUM.get()), has(ModItems.RAW_ALUMINUM.get())).save(pRecipeOutput);

        // Recipe for REFINED ALUMINUM
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.REFINED_ALUMINUM.get(), 9)
                .requires(ModBlocks.ALUMINUM_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.ALUMINUM_BLOCK.get()), has(ModBlocks.ALUMINUM_BLOCK.get())).save(pRecipeOutput);

        // Recipe for RAW ALUMINUM
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RAW_ALUMINUM.get(), 9)
                .requires(ModBlocks.RAW_ALUMINUM_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.ALUMINUM_BLOCK.get()), has(ModBlocks.ALUMINUM_BLOCK.get()))
                .save(pRecipeOutput, HoppaBootsMod.MOD_ID + ":raw_aluminum_from_raw_aluminum_block");

        // Recipe for WIND CARTRIDGE
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WIND_CARTRIDGE.get())
                .pattern("BAB")
                .pattern("ACA")
                .pattern("BAB")
                .define('A', ModItems.REFINED_ALUMINUM.get())
                .define('B', Items.IRON_INGOT)
                .define('C', Items.WIND_CHARGE)
                .unlockedBy(getHasName(ModItems.REFINED_ALUMINUM.get()), has(ModItems.REFINED_ALUMINUM.get())).save(pRecipeOutput);

        // Recipe for HOP BOOTS
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.HOP_BOOTS.get())
                .pattern("B B")
                .pattern("ACA")
                .pattern("B B")
                .define('A', ModItems.REFINED_ALUMINUM.get())
                .define('B', ModItems.WIND_CARTRIDGE.get())
                .define('C', Items.IRON_BOOTS)
                .unlockedBy(getHasName(ModItems.WIND_CARTRIDGE.get()), has(ModItems.WIND_CARTRIDGE.get())).save(pRecipeOutput);

        // oreSmelting(pRecipeOutput, ALUMINUM_SMELTABLES, RecipeCategory.MISC, ModItems.REFINED_ALUMINUM.get(), 0.25f, 200, "refined_aluminum");
        oreBlasting(pRecipeOutput, ALUMINUM_SMELTABLES, RecipeCategory.MISC, ModItems.REFINED_ALUMINUM.get(), 0.25f, 100, "refined_aluminum");
        oreBlasting(pRecipeOutput, ALUMINUM_SMELTABLES, RecipeCategory.MISC, ModBlocks.ALUMINUM_BLOCK.get(), 0.25f, 800, "aluminum_block");
    }

//    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
//                                      float pExperience, int pCookingTIme, String pGroup) {
//        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
//                pExperience, pCookingTIme, pGroup, "_from_smelting");
//    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, HoppaBootsMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}