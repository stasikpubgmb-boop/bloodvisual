package com.blood.visual.module.impl.visual;

import com.blood.visual.module.Module;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

public class ESP extends Module {

    public ESP() {
        super("ESP", Category.VISUAL);
    }

    @Override
    public void onEnable() {
        MinecraftClient.getInstance().getEntityRenderDispatcher().events.register(new net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents.Last({
            matrices, vertexConsumerProvider -> {
                for (PlayerEntity player : MinecraftClient.getInstance().world.getPlayers()) {
                    if (player != MinecraftClient.getInstance().player) {
                        renderPlayerESP(matrices, vertexConsumerProvider, player);
                    }
                }
            }
        }));
    }

    private void renderPlayerESP(PoseStack matrices, net.minecraft.client.render.VertexConsumerProvider vertexConsumerProvider, PlayerEntity player) {
        double minX = player.getBoundingBox().minX;
        double maxX = player.getBoundingBox().maxX;
        double minY = player.getBoundingBox().minY;
        double maxY = player.getBoundingBox().maxY;
        double minZ = player.getBoundingBox().minZ;
        double maxZ = player.getBoundingBox().maxZ;

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 0.5f);
        bufferBuilder.begin(net.minecraft.client.render.VertexFormat.DrawMode.LINES, VertexFormats.POSITION);
        bufferBuilder.vertex(minX, minY, minZ).next();
        bufferBuilder.vertex(maxX, minY, minZ).next();
        bufferBuilder.vertex(maxX, minY, minZ).next();
        bufferBuilder.vertex(maxX, minY, maxZ).next();
        bufferBuilder.vertex(maxX, minY, maxZ).next();
        bufferBuilder.vertex(minX, minY, maxZ).next();
        bufferBuilder.vertex(minX, minY, maxZ).next();
        bufferBuilder.vertex(minX, minY, minZ).next();

        bufferBuilder.vertex(minX, maxY, minZ).next();
        bufferBuilder.vertex(maxX, maxY, minZ).next();
        bufferBuilder.vertex(maxX, maxY, minZ).next();
        bufferBuilder.vertex(maxX, maxY, maxZ).next();
        bufferBuilder.vertex(maxX, maxY, maxZ).next();
        bufferBuilder.vertex(minX, maxY, maxZ).next();
        bufferBuilder.vertex(minX, maxY, maxZ).next();
        bufferBuilder.vertex(minX, maxY, minZ).next();

        bufferBuilder.vertex(minX, minY, minZ).next();
        bufferBuilder.vertex(minX, maxY, minZ).next();
        bufferBuilder.vertex(maxX, minY, minZ).next();
        bufferBuilder.vertex(maxX, maxY, minZ).next();
        bufferBuilder.vertex(minX, minY, maxZ).next();
        bufferBuilder.vertex(minX, maxY, maxZ).next();
        bufferBuilder.vertex(maxX, minY, maxZ).next();
        bufferBuilder.vertex(maxX, maxY, maxZ).next();

        tessellator.draw();
    }
}
