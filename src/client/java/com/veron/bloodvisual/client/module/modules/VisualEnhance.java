package com.veron.bloodvisual.client.module.modules;

import com.veron.bloodvisual.client.module.Category;
import com.veron.bloodvisual.client.module.Module;
import com.veron.bloodvisual.client.settings.BoolSetting;
import com.veron.bloodvisual.client.settings.NumberSetting;

/**
 * Пример модуля - визуальные улучшения
 */
public class VisualEnhance extends Module {
    private final BoolSetting smoothCamera;
    private final NumberSetting fovModifier;
    private final BoolSetting particleMultiplier;

    public VisualEnhance() {
        super("Visual Enhance", "Улучшает визуальные эффекты", Category.VISUALS);
        
        smoothCamera = new BoolSetting("Smooth Camera", true);
        fovModifier = new NumberSetting("FOV Modifier", 1.0, 0.5, 2.0, 0.1);
        particleMultiplier = new BoolSetting("Particle Multiplier", false);
        
        addSetting(smoothCamera);
        addSetting(fovModifier);
        addSetting(particleMultiplier);
    }

    @Override
    protected void onEnable() {
        // Логика включения
    }

    @Override
    protected void onDisable() {
        // Логика выключения
    }
}
