package com.blood.visual.module.impl.movement;

import com.blood.visual.module.Module;
import com.blood.visual.module.Category;
import net.minecraft.client.MinecraftClient;

public class Sprint extends Module {

    public Sprint() {
        super("Sprint", Category.MOVEMENT);
    }

    @Override
    public void onTick() {
        MinecraftClient.getInstance().player.setSprinting(true);
    }
}
