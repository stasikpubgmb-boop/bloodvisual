package com.blood.visual.hud;

import com.blood.visual.module.Module;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

public class TargetESP implements HudRenderCallback {
    private final MinecraftClient client;

    public TargetESP(MinecraftClient client) {
        this.client = client;
    }

    @Override
    public void onHudRender(DrawContext ctx, RenderTickCounter counter) {
        PlayerEntity player = client.player;
        if (player == null) return;

        Entity target = getClosestEntityInCrosshairDirection(player, 10);
        if (target instanceof PlayerEntity) {
            PlayerEntity targetPlayer = (PlayerEntity) target;
            int health = (int) targetPlayer.getHealth();
            int maxHealth = (int) targetPlayer.getMaxHealth();
            int ping = client.getConnection().getPlayerInfo(targetPlayer.getUuid()).getResponseTime();

            int x = ctx.getWindow().getScaledWidth() / 2 - 50;
            int y = ctx.getWindow().getScaledHeight() - 50;

            ctx.fill(x, y, 100, 20, 0x88000000);
            ctx.drawString(targetPlayer.getEntityName(), x + 5, y + 5);

            // Health bar
            ctx.fill(x + 5, y + 15, 90, 5, getHealthColor(health, maxHealth));

            // Armor bar
            ctx.fill(x + 5, y + 20, 90, 5, 0x88888888);

            // Ping
            ctx.drawString(String.valueOf(ping), x + 95, y + 5);
        }
    }

    private Entity getClosestEntityInCrosshairDirection(PlayerEntity player, double maxDistance) {
        Vec3d direction = player.getRotationVec(1.0f);
        Vec3d closestEntity = null;
        double closestDistance = Double.MAX_VALUE;

        for (Entity entity : player.world.getEntities()) {
            if (entity instanceof PlayerEntity && entity != player) {
                Vec3d entityDirection = entity.getPos().subtract(player.getPos());
                double dotProduct = direction.dotProduct(entityDirection.normalize());
                double distance = entityDirection.length();

                if (distance <= maxDistance && dotProduct > 0.5) {
                    if (distance < closestDistance) {
                        closestDistance = distance;
                        closestEntity = entity;
                    }
                }
            }
        }

        return closestEntity;
    }

    private int getHealthColor(int health, int maxHealth) {
        if (health >= maxHealth * 0.7) {
            return 0x33CC33;
        } else if (health >= maxHealth * 0.4) {
            return 0xFFFF66;
        } else {
            return 0xFF3333;
        }
    }
}
