package com.blood.visual.module.impl.movement;

import com.blood.visual.module.Module;

public class Sprint extends Module {

    public Sprint() {
        super("Sprint", Category.MOVEMENT);
    }

    @Override
    public void onTick() {
        MinecraftClient.getInstance().player.setSprinting(true);
    }
}
