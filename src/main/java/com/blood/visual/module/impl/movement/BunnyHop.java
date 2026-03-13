package com.blood.visual.module.impl.movement;

import com.blood.visual.module.Module;
import net.minecraft.entity.player.PlayerEntity;

public class BunnyHop extends Module {

    public BunnyHop() {
        super("BunnyHop", Category.MOVEMENT);
    }

    @Override
    public void onTick() {
        if (MinecraftClient.getInstance().player.isOnGround()) {
            MinecraftClient.getInstance().player.jump();
        }
    }
}
