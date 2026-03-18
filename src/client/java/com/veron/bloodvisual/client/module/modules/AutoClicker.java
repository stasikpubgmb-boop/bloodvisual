package com.veron.bloodvisual.client.module.modules;

import com.veron.bloodvisual.client.module.Category;
import com.veron.bloodvisual.client.module.Module;
import com.veron.bloodvisual.client.settings.BoolSetting;
import com.veron.bloodvisual.client.settings.NumberSetting;

/**
 * Модуль автоматического клика
 */
public class AutoClicker extends Module {
    private final NumberSetting cps;
    private final BoolSetting leftClick;
    private final BoolSetting rightClick;

    public AutoClicker() {
        super("Auto Clicker", "Автоматический клик", Category.COMBAT);
        
        cps = new NumberSetting("CPS", 10.0, 1.0, 20.0, 1.0);
        leftClick = new BoolSetting("Left Click", true);
        rightClick = new BoolSetting("Right Click", false);
        
        addSetting(cps);
        addSetting(leftClick);
        addSetting(rightClick);
    }

    @Override
    protected void onEnable() {
        // Запустить автокликер
    }

    @Override
    protected void onDisable() {
        // Остановить автокликер
    }
}
