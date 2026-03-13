package com.blood.visual;

import com.blood.visual.gui.ClickGUI;
import com.blood.visual.hud.HUDRenderer;
import com.blood.visual.hud.TargetESP;
import com.blood.visual.module.ModuleManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

public class BloodVisual implements ClientModInitializer {

    public static ModuleManager moduleManager;
    public static KeyBinding clickGuiKeybind;
    public static List<com.blood.visual.module.Module> modules = new ArrayList<>();
    public static com.blood.visual.hud.HUD hud;

    @Override
    public void onInitializeClient() {
        moduleManager = new ModuleManager();

        clickGuiKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.bloodvisual.clickgui", // The translation key for the keybinding's name
                InputUtil.Type.KEYBOARD, // The type of the keybinding, KEYBOARD or MOUSE
                GLFW.GLFW_KEY_RIGHT_SHIFT, // The key code of the keybinding
                "category.bloodvisual.clickgui" // The translation key for the keybinding's category
        ));

        // Initialize modules
        // ...

        // Register HUD
        hud = new com.blood.visual.hud.HUD();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            for (com.blood.visual.module.Module module : modules) {
                module.onTick();
            }
        });

        // Register ClickGUI
        ClickGUI clickGUI = new ClickGUI(null, moduleManager);

        // Register HUD Renderers
        HUDRenderer hudRenderer = new HUDRenderer();
        TargetESP targetESP = new TargetESP(MinecraftClient.getInstance());
    }
}
