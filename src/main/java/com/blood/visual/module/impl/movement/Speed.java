package com.blood.visual.module.impl.movement;

import com.blood.visual.module.Module;
import com.blood.visual.module.Category;
import com.blood.visual.module.ModuleManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;

public class Speed extends Module {

    private double speed;

    public Speed() {
        super("Speed", Category.MOVEMENT);
        speed = 1.1;
    }

    @Override
    public void onTick() {
        MinecraftClient.getInstance().player.setVelocity(MinecraftClient.getInstance().player.getVelocity().x * speed, MinecraftClient.getInstance().player.getVelocity().y, MinecraftClient.getInstance().player.getVelocity().z * speed);
    }
}
