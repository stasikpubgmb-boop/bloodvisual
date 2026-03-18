package com.veron.bloodvisual.client.gui.components.settings;

import net.minecraft.client.gui.DrawContext;

/**
 * Абстрактный компонент настройки
 */
public abstract class SettingComponent {
    protected float x, y, width;
    protected boolean hovered = false;

    public SettingComponent(float x, float y, float width) {
        this.x = x;
        this.y = y;
        this.width = width;
    }

    public abstract void render(DrawContext context, int mouseX, int mouseY, float delta, float alpha);
    
    public abstract boolean onClick(int mouseX, int mouseY, int button);
    
    public void onMouseDragged(int mouseX, int mouseY, int button, double deltaX, double deltaY) {
        // Переопределяется в подклассах при необходимости
    }

    public abstract float getHeight();

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    protected boolean isMouseOver(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + width && 
               mouseY >= y && mouseY <= y + getHeight();
    }
}
