package com.blood.visual.module.impl.visual;

import com.blood.visual.module.Module;
import net.minecraft.client.option.GameOptions;

public class Fullbright extends Module {

    private GameOptions options;

    public Fullbright() {
        super("Fullbright", Category.VISUAL);
    }

    @Override
    public void onEnable() {
        options = MinecraftClient.getInstance().options;
        options.getGamma().setValue(10.0);
    }

    @Override
    public void onDisable() {
        options.getGamma().setValue(1.0);
    }
}
