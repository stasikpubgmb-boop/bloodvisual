package com.veron.bloodvisual.client;

import com.veron.bloodvisual.BloodVisual;
import com.veron.bloodvisual.client.gui.ClickGuiScreen;
import com.veron.bloodvisual.client.module.ModuleManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

/**
 * Клиентская инициализация мода BloodVisual
 */
public class BloodVisualClient implements ClientModInitializer {
	private static KeyBinding openGuiKey;

	@Override
	public void onInitializeClient() {
		BloodVisual.LOGGER.info("BloodVisual client initialized!");

		// Инициализация ModuleManager
		ModuleManager.getInstance();

		// Регистрация кейбинда для открытия GUI (Right Shift)
		openGuiKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.bloodvisual.open_gui",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_RIGHT_SHIFT,
				"category.bloodvisual"
		));

		// Обработка нажатия кейбинда
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (openGuiKey.wasPressed()) {
				MinecraftClient.getInstance().setScreen(new ClickGuiScreen());
			}
		});
	}
}
