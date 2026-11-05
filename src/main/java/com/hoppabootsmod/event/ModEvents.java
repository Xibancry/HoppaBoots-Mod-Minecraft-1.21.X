package com.hoppabootsmod.event;

import com.hoppabootsmod.HoppaBootsMod;
import com.hoppabootsmod.item.ModItems;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.SimpleChannel;

@Mod.EventBusSubscriber(modid = HoppaBootsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {
    // Setup for all server-sided checks
    public static final SimpleChannel CHANNEL = ChannelBuilder
            .named(ResourceLocation.fromNamespaceAndPath(HoppaBootsMod.MOD_ID, "main"))
            .networkProtocolVersion(1)
            .simpleChannel();

    // Hopefully this will only be used to reset fall damage since the naming convention for these is awkward
    public static class ResetFallPacket {
        public static void encode(ResetFallPacket msg, FriendlyByteBuf buf) {
        }

        public static ResetFallPacket decode(FriendlyByteBuf buf) {
            return new ResetFallPacket();
        }

        public static void handle(ResetFallPacket msg, net.minecraftforge.event.network.CustomPayloadEvent.Context ctx) {
            ServerPlayer player = ctx.getSender();
            if (player == null) return;

            // Server checks for boots being equipped as an anticheat measure
            if (player.getItemBySlot(EquipmentSlot.FEET).is(ModItems.HOP_BOOTS.get())) {
                player.fallDistance = 0;
            }
        }
    }

    public static void register() {
        CHANNEL.messageBuilder(ResetFallPacket.class, NetworkDirection.PLAY_TO_SERVER)
                .encoder(ResetFallPacket::encode)
                .decoder(ResetFallPacket::decode)
                .consumerMainThread(ResetFallPacket::handle)
                .add();
    }
}