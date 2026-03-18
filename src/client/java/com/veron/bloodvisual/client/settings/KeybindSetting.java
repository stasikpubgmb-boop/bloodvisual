package com.veron.bloodvisual.client.settings;

import org.lwjgl.glfw.GLFW;

/**
 * Настройка кейбинда
 */
public class KeybindSetting extends Setting<Integer> {
    private boolean listening = false;

    public KeybindSetting(String name, int defaultKey) {
        super(name, defaultKey);
    }

    public KeybindSetting(String name) {
        this(name, GLFW.GLFW_KEY_UNKNOWN);
    }

    public boolean isListening() {
        return listening;
    }

    public void setListening(boolean listening) {
        this.listening = listening;
    }

    public String getKeyName() {
        if (getValue() == GLFW.GLFW_KEY_UNKNOWN) {
            return "None";
        }
        String name = GLFW.glfwGetKeyName(getValue(), 0);
        return name != null ? name.toUpperCase() : "KEY" + getValue();
    }
}
