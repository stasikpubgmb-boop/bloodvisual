package com.veron.bloodvisual.client.module.modules;

import com.veron.bloodvisual.client.module.Category;
import com.veron.bloodvisual.client.module.Module;
import com.veron.bloodvisual.client.settings.BoolSetting;

/**
 * Модуль автоматического спринта
 */
public class Sprint extends Module {
    private final BoolSetting omniSprint;

    public Sprint() {
        super("Sprint", "Автоматический спринт", Category.MOVEMENT);
        
        omniSprint = new BoolSetting("Omni Sprint", true);
        addSetting(omniSprint);
    }

    @Override
    protected void onEnable() {
        // Логика включения автоспринта
    }

    @Override
    protected void onDisable() {
        // Логика выключения
    }
}
