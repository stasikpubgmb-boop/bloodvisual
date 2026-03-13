package com.blood.visual.module.impl.visual;

import com.blood.visual.module.Module;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.MinecraftClient;

public class Fullbright extends Module {

    private GameOptions options;

    public Fullbright() {
        super("Fullbright", Category.VISUAL);
    }

    @Override
    public void onEnable() {
        options = MinecraftClient.getInstance().options;
        options.gamma = 10.0f;
    }

    @Override
    public void onDisable() {
        options.gamma = 1.0f;
    }
}
