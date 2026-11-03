package com.hoppabootsmod.datagen;

import com.hoppabootsmod.HoppaBootsMod;
import com.hoppabootsmod.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, HoppaBootsMod.MOD_ID, exFileHelper);
    }

    // Register for blockstate of new blocks (aka textures and models)
    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.ALUMINUM_BLOCK);
        blockWithItem(ModBlocks.RAW_ALUMINUM_BLOCK);

        blockWithItem(ModBlocks.ALUMINUM_ORE);
        blockWithItem(ModBlocks.ALUMINUM_DEEPSLATE_ORE);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}