package com.blood.visual.module.impl.visual;

import com.blood.visual.module.Module;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.MinecraftClient;

public class Fullbright extends Module {

    public Fullbright() {
        super("Fullbright", Category.VISUAL);
    }

    @Override
    public void onEnable() {
        GameOptions options = MinecraftClient.getInstance().options;
        options.setGamma(10.0);
    }

    @Override
    public void onDisable() {
        GameOptions options = MinecraftClient.getInstance().options;
        options.setGamma(1.0);
    }
}
