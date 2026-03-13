package com.blood.visual.module;

import java.util.ArrayList;
import java.util.List;

public abstract class Module {
    private String name;
    private Category category;
    private boolean enabled;
    private List<Setting> settings;
    private int key = -1;

    public Module(String name, Category category) {
        this.name = name;
        this.category = category;
        this.settings = new ArrayList<>();
    }

    public void toggle() {
        this.enabled = !this.enabled;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public String getName() {
        return this.name;
    }

    public Category getCategory() {
        return this.category;
    }

    public int getKey() {
        return this.key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getKeyName() {
        if (this.key == -1) {
            return "None";
        } else {
            return "" + (char) this.key;
        }
    }

    public List<Setting> getSettings() {
        return this.settings;
    }

    public void addSetting(Setting setting) {
        this.settings.add(setting);
    }

    public void onEnable() {}

    public void onDisable() {}

    public void onTick() {}
}

enum Category {
    COMBAT,
    MOVEMENT,
    VISUAL
}

interface Setting {}
