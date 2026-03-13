package com.blood.visual.module.impl.visual;

import com.blood.visual.module.Module;
import net.minecraft.client.options.GameOptions;
import net.minecraft.client.MinecraftClient;

public class Fullbright extends Module {

    public Fullbright() {
        super("Fullbright", Category.VISUAL);
    }

    @Override
    public void onEnable() {
        GameOptions options = MinecraftClient.getInstance().options;
        options.gamma = 10.0D; // Use double instead of float
    }

    @Override
    public void onDisable() {
        GameOptions options = MinecraftClient.getInstance().options;
        options.gamma = 1.0D; // Use double instead of float
    }
}
