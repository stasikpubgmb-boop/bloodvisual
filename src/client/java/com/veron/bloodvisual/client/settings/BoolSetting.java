package com.veron.bloodvisual.client.settings;

/**
 * Boolean настройка (вкл/выкл)
 */
public class BoolSetting extends Setting<Boolean> {
    public BoolSetting(String name, boolean defaultValue) {
        super(name, defaultValue);
    }

    public void toggle() {
        setValue(!getValue());
    }
}
