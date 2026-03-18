package com.veron.bloodvisual.client.module;

/**
 * Категории модулей для ClickGUI
 */
public enum Category {
    COMBAT("Combat", "⚔"),
    PLAYER("Player", "◈"),
    MOVEMENT("Movement", "➤"),
    VISUALS("Visuals", "◉"),
    MISC("Misc", "✦");

    private final String name;
    private final String icon;

    Category(String name, String icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }
}
