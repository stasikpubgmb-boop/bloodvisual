package com.blood.visual.module;

import com.blood.visual.setting.Setting;
import org.lwjgl.glfw.GLFW;
import java.util.ArrayList;
import java.util.List;

public abstract class Module {
    private String name;
    private Category category;
    private boolean enabled;
    private int key = -1;
    private List<Setting<?>> settings = new ArrayList<>();

    public Module(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public void toggle() { enabled = !enabled; if (enabled) onEnable(); else onDisable(); }
    public boolean isEnabled() { return enabled; }
    public String getName() { return name; }
    public Category getCategory() { return category; }
    public int getKey() { return key; }
    public void setKey(int key) { this.key = key; }
    public List<Setting<?>> getSettings() { return settings; }
    public void addSetting(Setting<?> s) { settings.add(s); }

    public String getKeyName() {
        if (key == -1) return "NONE";
        String k = GLFW.glfwGetKeyName(key, 0);
        return k != null ? k.toUpperCase() : "KEY_" + key;
    }

    public void onEnable() {}
    public void onDisable() {}
    public void onTick() {}
}
