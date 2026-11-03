package com.hoppabootsmod.item;

import com.hoppabootsmod.HoppaBootsMod;
import com.hoppabootsmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, HoppaBootsMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> HOPPA_BOOTS_TAB = CREATIVE_MODE_TABS.register("hoppa_boots_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.REFINED_ALUMINUM.get()))
                    .title(Component.translatable("creativetab.hoppabootsmod.hoppa_boots_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.RAW_ALUMINUM.get());
                        output.accept(ModItems.REFINED_ALUMINUM.get());
                        output.accept(ModItems.WIND_CARTRIDGE.get());
                        output.accept(ModBlocks.RAW_ALUMINUM_BLOCK.get());
                        output.accept(ModBlocks.ALUMINUM_ORE.get());
                        output.accept(ModBlocks.ALUMINUM_DEEPSLATE_ORE.get());
                        output.accept(ModBlocks.ALUMINUM_BLOCK.get());
                        output.accept(ModItems.ALUMINUM_BOOTS.get());
                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
