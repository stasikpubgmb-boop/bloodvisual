package com.blood.visual.hud;

import com.blood.visual.module.Module;
import com.blood.visual.module.ModuleManager;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import java.awt.Color;
import java.util.Comparator;
import java.util.List;

public class HUDRenderer implements HudRenderCallback {
    private float hue = 0f;

    @Override
    public void onHudRender(DrawContext ctx, RenderTickCounter counter) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null || mc.currentScreen != null) return;
        List<Module> enabled = ModuleManager.getModules().stream()
            .filter(Module::isEnabled)
            .sorted(Comparator.comparingInt(m -> -mc.textRenderer.getWidth(m.getName())))
            .toList();
        int sw = mc.getWindow().getScaledWidth();
        int y = 2;
        hue += 0.005f; if (hue > 1f) hue = 0f;
        for (int i = 0; i < enabled.size(); i++) {
            String name = enabled.get(i).getName();
            int w = mc.textRenderer.getWidth(name);
            int color = Color.HSBtoRGB((hue + i * 0.05f) % 1f, 0.8f, 1f);
            ctx.fill(sw-w-3, y-1, sw, y+9, 0x55000000);
            ctx.drawTextWithShadow(mc.textRenderer, name, sw-w-1, y, color);
            y += 10;
        }
    }
}
