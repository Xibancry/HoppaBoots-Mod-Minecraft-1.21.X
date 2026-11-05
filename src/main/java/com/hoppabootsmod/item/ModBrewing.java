package com.hoppabootsmod.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.brewing.IBrewingRecipe;

public class ModBrewing implements IBrewingRecipe {
    private final Item input;
    private final Item ingredient;
    private final Item output;

    public ModBrewing(ItemLike input, ItemLike ingredient, ItemLike output) {
        this.input = input.asItem();
        this.ingredient = ingredient.asItem();
        this.output = output.asItem();
    }

    @Override
    public boolean isInput(ItemStack stack) {
        return stack.is(input);
    }

    @Override
    public boolean isIngredient(ItemStack stack) {
        return stack.is(ingredient);
    }

    @Override
    public ItemStack getOutput(ItemStack inputStack, ItemStack ingredientStack) {
        if (!isInput(inputStack) || !isIngredient(ingredientStack)) return ItemStack.EMPTY;

        ItemStack result = new ItemStack(output);
        // Keep enchantments, durability, etc. from the hop boots
        result.applyComponents(inputStack.getComponentsPatch());
        return result;
    }
}