package com.veron.bloodvisual.client.module.modules;

import com.veron.bloodvisual.client.module.Category;
import com.veron.bloodvisual.client.module.Module;
import com.veron.bloodvisual.client.settings.BoolSetting;
import com.veron.bloodvisual.client.settings.NumberSetting;

/**
 * Пример модуля - кастомные частицы
 */
public class CustomParticles extends Module {
    private final NumberSetting amount;
    private final NumberSetting lifetime;
    private final BoolSetting physicsEnabled;

    public CustomParticles() {
        super("Custom Particles", "Кастомные частицы крови", Category.VISUALS);
        
        amount = new NumberSetting("Amount", 10.0, 1.0, 50.0, 1.0);
        lifetime = new NumberSetting("Lifetime", 2.0, 0.5, 10.0, 0.5);
        physicsEnabled = new BoolSetting("Physics", true);
        
        addSetting(amount);
        addSetting(lifetime);
        addSetting(physicsEnabled);
    }
}
