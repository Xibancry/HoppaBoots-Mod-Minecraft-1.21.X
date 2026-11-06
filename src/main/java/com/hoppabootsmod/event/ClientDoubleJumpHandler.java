package com.hoppabootsmod.event;

import com.hoppabootsmod.HoppaBootsMod;
import com.hoppabootsmod.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

@Mod.EventBusSubscriber(modid = HoppaBootsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientDoubleJumpHandler {
    private static boolean jumpWasReleased = false;
    private static boolean usedDoubleJump = false;

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (!(event.player instanceof LocalPlayer player)) return;
        if (player != Minecraft.getInstance().player) return;

        // Check if player is wearing hop boots
        boolean wearingBoots = player.getItemBySlot(EquipmentSlot.FEET)
                .is(ModItems.HOP_BOOTS.get());

        // Stop checks if boots are not equipped
        if (!wearingBoots) {
            return;
        }

        // True if jump key is pressed
        boolean jumpPressed = Minecraft.getInstance().options.keyJump.isDown();

        // Reset whenever the player lands in any non-air terrain or (somehow lol) flies
        if (player.onGround() || player.isInWater() || player.isInLava()
                || player.onClimbable() || player.getAbilities().flying
                || player.isPassenger()) {
            usedDoubleJump = false;
            jumpWasReleased = false;
            return;
        }

        boolean canDoubleJump = !usedDoubleJump // List of moments the player should not be able to double jump
                && !player.onGround()
                && !player.isFallFlying()
                && !player.getAbilities().flying
                && !player.isInWater()
                && !player.onClimbable()
                && !player.isPassenger();

        // Supposedly will only logically apply when player is airborne
        if (!jumpPressed) {
            jumpWasReleased = true;
            return;
        }

        // Trigger only if it is the second time the player hits the jump key
        if (canDoubleJump && jumpPressed && jumpWasReleased) {
            Vec3 motion = player.getDeltaMovement();
            player.setDeltaMovement(motion.x, 0.525, motion.z);
            player.hasImpulse = true;
            player.fallDistance = 0; // This only works for client-side, but we leave it here for data consistency
            ModEvents.CHANNEL.send(new ModEvents.ResetFallPacket(),  // server-side copy
                    PacketDistributor.SERVER.noArg());
            usedDoubleJump = true;
            jumpWasReleased = false;
        }
    }
}