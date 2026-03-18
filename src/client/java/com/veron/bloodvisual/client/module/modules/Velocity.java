package com.veron.bloodvisual.client.module.modules;

import com.veron.bloodvisual.client.module.Category;
import com.veron.bloodvisual.client.module.Module;
import com.veron.bloodvisual.client.settings.NumberSetting;

/**
 * Модуль изменения отбрасывания
 */
public class Velocity extends Module {
    private final NumberSetting horizontal;
    private final NumberSetting vertical;

    public Velocity() {
        super("Velocity", "Изменяет отбрасывание", Category.COMBAT);
        
        horizontal = new NumberSetting("Horizontal", 0.0, 0.0, 100.0, 5.0);
        vertical = new NumberSetting("Vertical", 0.0, 0.0, 100.0, 5.0);
        
        addSetting(horizontal);
        addSetting(vertical);
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
