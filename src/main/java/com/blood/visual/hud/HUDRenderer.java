package com.blood/visual/hud;

import com.blood.visual.module.Module;
import com.blood.visual.module.ModuleManager;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.render.DrawContext;
import net.minecraft.client.render.RenderTickCounter;

public class HUDRenderer implements HudRenderCallback {
    private final ModuleManager moduleManager;

    public HUDRenderer(ModuleManager moduleManager) {
        this.moduleManager = moduleManager;
    }

    @Override
    public void onHudRender(DrawContext ctx, RenderTickCounter counter) {
        int x = ctx.getWindow().getScaledWidth() - 10;
        int y = 10;

        for (Module module : moduleManager.getModules()) {
            if (module.isEnabled()) {
                int color = Color.HSBtoRGB((counter.getTick() % 100) / 100.0f, 1.0f, 1.0f);
                ctx.fill(x - 5, y - 5, 10, 10, 0x88000000);
                ctx.drawString(module.getName(), x, y);
                y += 10;
            }
        }
    }
}
