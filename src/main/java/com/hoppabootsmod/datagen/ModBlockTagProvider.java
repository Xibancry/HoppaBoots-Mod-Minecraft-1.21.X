package com.hoppabootsmod.datagen;

import com.hoppabootsmod.HoppaBootsMod;
import com.hoppabootsmod.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, HoppaBootsMod.MOD_ID, existingFileHelper);
    }

    // Provide datagen the tags for the new blocks
    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.ALUMINUM_BLOCK.get())
                .add(ModBlocks.RAW_ALUMINUM_BLOCK.get())
                .add(ModBlocks.ALUMINUM_ORE.get())
                .add(ModBlocks.ALUMINUM_DEEPSLATE_ORE.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.ALUMINUM_ORE.get())
                .add(ModBlocks.ALUMINUM_DEEPSLATE_ORE.get());
    }
}