package com.blood.visual;

import com.blood.visual.gui.ClickGUI;
import com.blood.visual.hud.HUDRenderer;
import com.blood.visual.hud.TargetESP;
import com.blood.visual.module.ModuleManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

public class BloodVisual implements ClientModInitializer {
    public static final String MOD_ID = "bloodvisual";
    public static ModuleManager moduleManager;

    @Override
    public void onInitializeClient() {
        moduleManager = new ModuleManager();
        moduleManager.init();
        HudRenderCallback.EVENT.register(new HUDRenderer());
        HudRenderCallback.EVENT.register(new TargetESP());
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            moduleManager.onTick();
            if (client.player != null && client.options.inventoryKey.wasPressed()) {
                // reserved
            }
        });
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;
            if (GLFW.glfwGetKey(client.getWindow().getHandle(), GLFW.GLFW_KEY_RIGHT_SHIFT) == GLFW.GLFW_PRESS) {
                if (client.currentScreen == null) {
                    client.setScreen(new ClickGUI());
                }
            }
            moduleManager.onKey(0);
        });
    }
}
