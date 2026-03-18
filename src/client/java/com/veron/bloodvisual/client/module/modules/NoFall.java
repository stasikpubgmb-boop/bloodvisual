package com.veron.bloodvisual.client.module.modules;

import com.veron.bloodvisual.client.module.Category;
import com.veron.bloodvisual.client.module.Module;
import com.veron.bloodvisual.client.settings.BoolSetting;

/**
 * Модуль защиты от урона при падении
 */
public class NoFall extends Module {
    private final BoolSetting packet;

    public NoFall() {
        super("No Fall", "Убирает урон от падения", Category.PLAYER);
        
        packet = new BoolSetting("Packet Mode", true);
        addSetting(packet);
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
