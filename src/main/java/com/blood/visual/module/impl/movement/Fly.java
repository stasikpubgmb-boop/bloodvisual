package com.blood.visual.module.impl.movement;

import com.blood.visual.module.Module;
import com.blood.visual.module.Category;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;

public class Fly extends Module {

    public Fly() {
        super("Fly", Category.MOVEMENT);
    }

    @Override
    public void onEnable() {
        PlayerEntity player = MinecraftClient.getInstance().player;
        player.getAbilities().flying = true; // Access abilities through getAbilities()
        player.getAbilities().allowFlying = true; // Access abilities through getAbilities()
    }

    @Override
    public void onDisable() {
        PlayerEntity player = MinecraftClient.getInstance().player;
        player.getAbilities().flying = false; // Access abilities through getAbilities()
        player.getAbilities().allowFlying = false; // Access abilities through getAbilities()
    }

    @Override
    public void onTick() {
        PlayerEntity player = MinecraftClient.getInstance().player;
        player.getAbilities().flying = true; // Access abilities through getAbilities()
    }
}
