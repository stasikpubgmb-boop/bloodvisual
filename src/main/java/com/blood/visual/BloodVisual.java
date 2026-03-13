package com.blood.visual;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

public class BloodVisual implements ClientModInitializer {

    public static ModuleManager moduleManager;
    public static KeyBinding clickGuiKeybind;
    public static List<Module> modules = new ArrayList<>();
    public static HUD hud;

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
        hud = new HUD();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            for (Module module : modules) {
                module.onTick();
            }
        });
    }
}
