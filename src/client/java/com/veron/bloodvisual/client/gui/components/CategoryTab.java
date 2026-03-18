package com.veron.bloodvisual.client.gui.components;

import com.veron.bloodvisual.client.gui.animation.Animation;
import com.veron.bloodvisual.client.gui.render.CustomFont;
import com.veron.bloodvisual.client.module.Category;
import net.minecraft.client.gui.DrawContext;

/**
 * Вкладка категории в верхней панели
 */
public class CategoryTab {
    private final Category category;
    private float x, y, width, height;
    private boolean active = false;
    private boolean hovered = false;
    
    private final Animation hoverAnimation = new Animation(0.15f);
    private final Animation activeAnimation = new Animation(0.2f);

    // Цвета в стиле Nursultan/Celestial
    private static final int COLOR_BG_INACTIVE = 0xE6222222;  // #222222 с alpha ~90%
    private static final int COLOR_BG_ACTIVE = 0x1FFFFFFF;    // #FFFFFF с alpha ~12%
    private static final int COLOR_BG_HOVER = 0xE61E1E1E;     // #1E1E1E
    private static final int COLOR_TEXT = 0xFFE0E0E0;         // Светло-серый
    private static final int COLOR_TEXT_ACTIVE = 0xFFFFFFFF;  // Белый

    public CategoryTab(Category category, float x, float y, float width, float height) {
        this.category = category;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // Обновление анимаций
        hoverAnimation.update(delta);
        activeAnimation.update(delta);

        // Фон вкладки
        int bgColor = active ? COLOR_BG_ACTIVE : COLOR_BG_INACTIVE;
        if (hovered && !active) {
            bgColor = COLOR_BG_HOVER;
        }

        // Рисуем закругленный прямоугольник
        drawRoundedRect(context, x, y, width, height, 5, bgColor);

        // Текст категории
        CustomFont font = CustomFont.getInstance();
        int textColor = active ? COLOR_TEXT_ACTIVE : COLOR_TEXT;
        
        String text = category.getName();
        float textX = x + 12;
        float textY = y + (height - font.getHeight()) / 2;
        font.draw(context, text, textX, textY, textColor);

        // Иконка справа (✗ если активна, ○ если нет)
        String icon = active ? "✗" : "○";
        float iconX = x + width - 18;
        font.draw(context, icon, iconX, textY, textColor);
    }

    /**
     * Рисует закругленный прямоугольник
     * Упрощенная версия - рисуем обычный прямоугольник
     * TODO: Реализовать настоящие закругленные углы через scissor/stencil
     */
    private void drawRoundedRect(DrawContext context, float x, float y, float width, float height, float radius, int color) {
        context.fill((int)x, (int)y, (int)(x + width), (int)(y + height), color);
    }

    public void onClick() {
        // Обработка клика - установка активности происходит извне
    }

    public boolean isMouseOver(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }

    public void setHovered(boolean hovered) {
        this.hovered = hovered;
        hoverAnimation.setTarget(hovered ? 1.0f : 0.0f);
    }

    public void setActive(boolean active) {
        this.active = active;
        activeAnimation.setTarget(active ? 1.0f : 0.0f);
    }

    public boolean isActive() {
        return active;
    }

    public Category getCategory() {
        return category;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getWidth() {
        return width;
    }
}
