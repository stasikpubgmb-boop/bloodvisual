package com.veron.bloodvisual.client.gui.render;

import net.minecraft.client.gui.DrawContext;

/**
 * Рендерер размытия фона для ClickGUI
 * Упрощенная версия с градиентом для производительности
 */
public class BlurRenderer {
    private static BlurRenderer instance;

    private BlurRenderer() {
    }

    public static BlurRenderer getInstance() {
        if (instance == null) {
            instance = new BlurRenderer();
        }
        return instance;
    }

    /**
     * Рендерит размытый фон
     * Использует затемнение и градиент вместо полноценного blur для производительности
     * TODO: Реализовать настоящий Gaussian blur через шейдеры в будущем
     */
    public void renderBlurredBackground(DrawContext context, int width, int height) {
        // Рисуем полупрозрачный темный оверлей (60% прозрачности)
        context.fill(0, 0, width, height, 0x99000000);
        
        // Дополнительный градиент для глубины (используем встроенный метод DrawContext)
        context.fillGradient(0, 0, width, height, 0x44000000, 0x88000000);
    }

    public void cleanup() {
        // Очистка ресурсов если понадобится в будущем
    }
}
