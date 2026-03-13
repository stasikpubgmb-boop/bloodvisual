package com.blood.visual.module.impl.movement;

import com.blood.visual.module.Module;
import com.blood.visual.module.ModuleSetting;
import com.blood.visual.module.SliderSetting;
import net.minecraft.client.option.GameOptions;

public class Speed extends Module {

    private SliderSetting speed;

    public Speed() {
        super("Speed", Category.MOVEMENT);
        speed = new SliderSetting("Speed", 1.1, 2.0, 1.3);
        addSetting(speed);
    }

    @Override
    public void onTick() {
        MinecraftClient.getInstance().player.setVelocity(MinecraftClient.getInstance().player.getVelocity().x * speed.getValue(), MinecraftClient.getInstance().player.getVelocity().y, MinecraftClient.getInstance().player.getVelocity().z * speed.getValue());
    }
}
