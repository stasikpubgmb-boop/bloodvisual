package com.blood.visual.setting;

public class BooleanSetting extends Setting<Boolean> {
    private boolean value;

    public BooleanSetting(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return this.value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
