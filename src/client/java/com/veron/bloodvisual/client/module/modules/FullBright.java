package com.veron.bloodvisual.client.module.modules;

import com.veron.bloodvisual.client.module.Category;
import com.veron.bloodvisual.client.module.Module;
import com.veron.bloodvisual.client.settings.NumberSetting;

/**
 * Модуль яркости
 */
public class FullBright extends Module {
    private final NumberSetting brightness;

    public FullBright() {
        super("Full Bright", "Увеличивает яркость", Category.VISUALS);
        
        brightness = new NumberSetting("Brightness", 15.0, 1.0, 15.0, 1.0);
        addSetting(brightness);
    }

    @Override
    protected void onEnable() {
        // Установить яркость
    }

    @Override
    protected void onDisable() {
        // Вернуть стандартную яркость
    }
}
