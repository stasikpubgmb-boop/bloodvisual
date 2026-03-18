package com.veron.bloodvisual.client;

import net.fabricmc.api.ClientModInitializer;
import com.veron.bloodvisual.BloodVisual;

public class BloodVisualClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BloodVisual.LOGGER.info("BloodVisual client initialized!");
	}
}
