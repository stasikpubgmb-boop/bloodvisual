package com.veron.bloodvisual.client.gui.render;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

/**
 * Обертка для кастомного шрифта
 * TODO: Добавить поддержку TTF через Caxton или custom font provider
 */
public class CustomFont {
    private static CustomFont instance;
    private final TextRenderer renderer;

    private CustomFont() {
        // Пока используем стандартный рендерер
        // В будущем: загрузить TTF из assets/bloodvisual/fonts/inter.ttf
        this.renderer = MinecraftClient.getInstance().textRenderer;
    }

    public static CustomFont getInstance() {
        if (instance == null) {
            instance = new CustomFont();
        }
        return instance;
    }

    /**
     * Рисует текст без тени
     */
    public int draw(DrawContext context, String text, float x, float y, int color) {
        return context.drawText(renderer, text, (int)x, (int)y, color, false);
    }

    /**
     * Рисует текст с тенью
     */
    public int drawWithShadow(DrawContext context, String text, float x, float y, int color) {
        return context.drawText(renderer, text, (int)x, (int)y, color, true);
    }

    /**
     * Получить ширину текста
     */
    public int getWidth(String text) {
        return renderer.getWidth(text);
    }

    /**
     * Получить высоту шрифта
     */
    public int getHeight() {
        return renderer.fontHeight;
    }

    /**
     * Центрированный текст
     */
    public int drawCentered(DrawContext context, String text, float x, float y, int color) {
        float centeredX = x - (getWidth(text) / 2.0f);
        return draw(context, text, centeredX, y, color);
    }
}
