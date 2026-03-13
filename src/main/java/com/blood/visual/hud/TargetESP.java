package com.blood.visual.hud;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;

public class TargetESP implements HudRenderCallback {
    private float hue = 0f;

    @Override
    public void onHudRender(DrawContext ctx, RenderTickCounter counter) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null || mc.world == null) return;

        PlayerEntity target = null;
        double closest = 20.0;
        for (PlayerEntity p : mc.world.getPlayers()) {
            if (p == mc.player) continue;
            double dist = mc.player.distanceTo(p);
            if (dist < closest) { closest = dist; target = p; }
        }
        if (target == null) return;

        int sw = mc.getWindow().getScaledWidth();
        int sh = mc.getWindow().getScaledHeight();
        int bx = sw / 2 - 80, by = sh - 80, bw = 160, bh = 55;

        hue += 0.01f; if (hue > 1f) hue = 0f;
        int borderColor = java.awt.Color.HSBtoRGB(hue, 0.8f, 1f);

        ctx.fill(bx, by, bx + bw, by + bh, 0xCC0a0a0a);
        ctx.fill(bx, by, bx + bw, by + 1, borderColor);
        ctx.fill(bx, by + bh - 1, bx + bw, by + bh, borderColor);
        ctx.fill(bx, by, bx + 1, by + bh, borderColor);
        ctx.fill(bx + bw - 1, by, bx + bw, by + bh, borderColor);

        ctx.drawTextWithShadow(mc.textRenderer, target.getName().getString(), bx + 5, by + 5, 0xFFFFFFFF);

        float hp = target.getHealth();
        float maxHp = target.getMaxHealth();
        float hpPct = hp / maxHp;
        int hpColor = hpPct > 0.6f ? 0xFF00FF44 : hpPct > 0.3f ? 0xFFFFAA00 : 0xFFFF3333;
        ctx.fill(bx + 5, by + 18, bx + bw - 5, by + 26, 0xFF333333);
        ctx.fill(bx + 5, by + 18, bx + 5 + (int)((bw - 10) * hpPct), by + 26, hpColor);
        ctx.drawTextWithShadow(mc.textRenderer, "HP: " + (int)hp + "/" + (int)maxHp, bx + 5, by + 29, 0xFFCCCCCC);
        ctx.drawTextWithShadow(mc.textRenderer, "Dist: " + (int)closest + "m", bx + 5, by + 40, 0xFF888888);
    }
}
