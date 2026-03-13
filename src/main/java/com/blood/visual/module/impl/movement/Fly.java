package com.blood.visual.module.impl.movement;

import com.blood.visual.module.Module;
import net.minecraft.client.MinecraftClient;

public class Fly extends Module {

    public Fly() {
        super("Fly", Category.MOVEMENT);
    }

    @Override
    public void onEnable() {
        MinecraftClient.getInstance().player.abilities.allowFlying = true;
        MinecraftClient.getInstance().player.abilities.flying = true;
    }

    @Override
    public void onDisable() {
        MinecraftClient.getInstance().player.abilities.allowFlying = false;
        MinecraftClient.getInstance().player.abilities.flying = false;
    }

    @Override
    public void onTick() {
        MinecraftClient.getInstance().player.abilities.flying = true;
    }
}
