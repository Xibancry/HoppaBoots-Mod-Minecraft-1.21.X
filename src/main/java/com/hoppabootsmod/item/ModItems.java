package com.hoppabootsmod.item;

import com.hoppabootsmod.HoppaBootsMod;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    // Declara la lista de nuevos ITEMS a registrar por mod
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, HoppaBootsMod.MOD_ID);

    // Crea el nuevo item Aluminum y lo suma a la lista de ITEMS
    public static final RegistryObject<Item> RAW_ALUMINUM = ITEMS.register("raw_aluminum",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> REFINED_ALUMINUM = ITEMS.register("refined_aluminum",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WIND_CARTRIDGE = ITEMS.register("wind_cartridge",
            () -> new Item(new Item.Properties()));

    // Declaracion de armor items
    public static final RegistryObject<Item> HOP_BOOTS = ITEMS.register("hop_boots",
            () -> new ArmorItem(ModArmorMaterials.ALUMINUM_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(15))));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
