package com.blood.visual.module.impl.visual;

import com.blood.visual.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.DrawContext;
import net.minecraft.client.util.math.MathHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

import java.awt.Color;

public class TargetESP extends Module {

    private PlayerEntity currentTarget;

    public TargetESP() {
        super("TargetESP", Module.Category.VISUAL);
    }

    @Override
    public void onTick() {
        currentTarget = null;
        double distance = Double.MAX_VALUE;
        for (PlayerEntity player : MinecraftClient.getInstance().world.getEntitiesByType(PlayerEntity.class)) {
            if (player != MinecraftClient.getInstance().player && player.getPos().distanceTo(MinecraftClient.getInstance().player.getPos()) <= 20) {
                double playerDistance = player.getPos().distanceTo(MinecraftClient.getInstance().player.getPos());
                if (playerDistance < distance) {
                    distance = playerDistance;
                    currentTarget = player;
                }
            }
        }
    }

    public void render(DrawContext ctx) {
        if (currentTarget == null || currentTarget == MinecraftClient.getInstance().player) {
            return;
        }

        int width = 200;
        int height = 60;

        int x = ctx.getWindow().getScaledWidth() / 2 - width / 2;
        int y = ctx.getWindow().getScaledHeight() - height - 10;

        float hue = System.currentTimeMillis() / 1000.0f;
        int borderColor = Color.HSBtoRGB(hue, 1, 1);

        float health = currentTarget.getHealth() / currentTarget.getMaxHealth();
        int healthColor;
        if (health > 0.6f) {
            healthColor = Color.GREEN.getRGB();
        } else if (health > 0.3f) {
            healthColor = Color.YELLOW.getRGB();
        } else {
            healthColor = Color.RED.getRGB();
        }

        ctx.fill(x, y, x + width, y + height, new Color(0, 0, 0, 128).getRGB());

        ctx.fill(x, y, x + 2, y + height, borderColor);
        ctx.fill(x + width - 2, y, x + width, y + height, borderColor);
        ctx.fill(x, y, x + width, y + 2, borderColor);
        ctx.fill(x, y + height - 2, x + width, y + height, borderColor);

        // Draw health bar
        int healthBarWidth = width - 10;
        ctx.fill(x + 5, y + height / 2 - 5, x + 5 + (int) (healthBarWidth * health), y + height / 2 + 5, healthColor);
    }
}
