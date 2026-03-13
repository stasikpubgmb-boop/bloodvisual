package com.blood.visual.module.impl.visual;

import com.blood.visual.module.Module;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import java.awt.Color;

public class TargetESP extends Module {

    private PlayerEntity currentTarget;

    public TargetESP() {
        super("TargetESP", Category.VISUAL);
    }

    @Override
    public void onTick() {
        currentTarget = null;
        double distance = Double.MAX_VALUE;
        for (PlayerEntity player : MinecraftClient.getInstance().world.getPlayers()) {
            if (player != MinecraftClient.getInstance().player && player.getPos().distanceTo(MinecraftClient.getInstance().player.getPos()) <= 20) {
                double playerDistance = player.getPos().distanceTo(MinecraftClient.getInstance().player.getPos());
                if (playerDistance < distance) {
                    distance = playerDistance;
                    currentTarget = player;
                }
            }
        }
    }

    public void render(MatrixStack matrices) {
        if (currentTarget == null || currentTarget == MinecraftClient.getInstance().player) {
            return;
        }

        matrices.push();
        matrices.translate(0, 0, 0);

        RenderSystem.disableDepthTest();
        RenderSystem.disableTexture();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);

        int width = 200;
        int height = 60;

        int x = MinecraftClient.getInstance().width / 2 - width / 2;
        int y = MinecraftClient.getInstance().height - height - 10;

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

        // Draw background
        DrawableHelper.fill(matrices, x, y, x + width, y + height, new Color(0, 0, 0, 128).getRGB());

        // Draw border
        DrawableHelper.fill(matrices, x, y, x + 2, y + height, borderColor);
        DrawableHelper.fill(matrices, x + width - 2, y, x + width, y + height, borderColor);
        DrawableHelper.fill(matrices, x, y, x + width, y + 2, borderColor);
        DrawableHelper.fill(matrices, x, y + height - 2, x + width, y + height, borderColor);

        // Draw health bar
        int healthBarWidth = width - 4;
        int healthBarHeight = 10;
        int healthBarX = x + 2;
        int healthBarY = y + height - healthBarHeight - 2;
        DrawableHelper.fill(matrices, healthBarX, healthBarY, healthBarX + (int) (healthBarWidth * health), healthBarY + healthBarHeight, healthColor);

        // Draw text
        MinecraftClient.getInstance().textRenderer.draw(matrices, currentTarget.getDisplayName().getString(), x + 2, y + 2, Color.WHITE.getRGB());
        MinecraftClient.getInstance().textRenderer.draw(matrices, "Armor: " + currentTarget.getArmor(), x + 2, y + 14, Color.WHITE.getRGB());
        MinecraftClient.getInstance().textRenderer.draw(matrices, "Distance: " + (int) currentTarget.getPos().distanceTo(MinecraftClient.getInstance().player.getPos()), x + 2, y + 26, Color.WHITE.getRGB());

        RenderSystem.disableBlend();
        RenderSystem.enableTexture();
        RenderSystem.enableDepthTest();

        matrices.pop();
    }
}
