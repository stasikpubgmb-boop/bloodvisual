package com.blood.visual.module.impl.movement;

import com.blood.visual.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;

public class Fly extends Module {

    public Fly() {
        super("Fly", Category.MOVEMENT);
    }

    @Override
    public void onEnable() {
        PlayerEntity player = MinecraftClient.getInstance().player;
        player.abilities.flying = true; // Access abilities through player
        player.abilities.allowFlying = true; // Access abilities through player
    }

    @Override
    public void onDisable() {
        PlayerEntity player = MinecraftClient.getInstance().player;
        player.abilities.flying = false; // Access abilities through player
        player.abilities.allowFlying = false; // Access abilities through player
    }

    @Override
    public void onTick() {
        PlayerEntity player = MinecraftClient.getInstance().player;
        player.abilities.flying = true; // Access abilities through player
    }
}
