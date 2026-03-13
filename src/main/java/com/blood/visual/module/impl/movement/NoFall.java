package com.blood.visual.module.impl.movement;

import com.blood.visual.module.Module;
import net.minecraft.client.MinecraftClient;

public class NoFall extends Module {

    public NoFall() {
        super("NoFall", Category.MOVEMENT);
    }

    @Override
    public void onTick() {
        if (MinecraftClient.getInstance().player.fallDistance > 2) {
            MinecraftClient.getInstance().player.fallDistance = 0;
        }
    }
}
