package com.blood.visual.hud;

import com.blood.visual.module.Module;
import com.blood.visual.module.ModuleManager;
import com.blood.visual.BloodVisual;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;

public class HUDRenderer implements HudRenderCallback {
    private float hue = 0f;

    @Override
    public void onHudRender(DrawContext ctx, RenderTickCounter counter) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null || mc.currentScreen != null) return;
        ModuleManager moduleManager = BloodVisual.moduleManager;
        int sw = mc.getWindow().getScaledWidth();
        int y = 2;
        hue += 0.005f; if (hue > 1f) hue = 0f;
        for (Module module : moduleManager.getModulesByIsEnabled()) {
            String name = module.getName();
            int w = mc.textRenderer.getWidth(name);
            int color = (int) (Math.sin(hue + module.getModuleID() * 0.05f) * 128 + 128) << 16 | (int) (Math.sin(hue + module.getModuleID() * 0.05f + Math.PI / 2) * 128 + 128) << 8 | (int) (Math.sin(hue + module.getModuleID() * 0.05f + Math.PI) * 128 + 128);
            ctx.fill(sw-w-3, y-1, sw, y+9, 0x55000000);
            ctx.drawTextWithShadow(mc.textRenderer, name, sw-w-1, y, color);
            y += 10;
        }
    }
}
