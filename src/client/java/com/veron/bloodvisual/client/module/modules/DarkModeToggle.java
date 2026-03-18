package com.veron.bloodvisual.client.module.modules;

import com.veron.bloodvisual.client.module.Category;
import com.veron.bloodvisual.client.module.Module;
import com.veron.bloodvisual.client.settings.BoolSetting;
import com.veron.bloodvisual.client.settings.NumberSetting;

/**
 * Пример модуля - темный режим
 */
public class DarkModeToggle extends Module {
    private final BoolSetting autoEnable;
    private final NumberSetting darkness;

    public DarkModeToggle() {
        super("Dark Mode", "Затемняет экран", Category.VISUALS);
        
        autoEnable = new BoolSetting("Auto Enable", false);
        darkness = new NumberSetting("Darkness", 0.5, 0.0, 1.0, 0.05);
        
        addSetting(autoEnable);
        addSetting(darkness);
    }
}
