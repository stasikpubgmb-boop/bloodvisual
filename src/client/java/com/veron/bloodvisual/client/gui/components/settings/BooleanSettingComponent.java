package com.veron.bloodvisual.client.gui.components.settings;

import com.veron.bloodvisual.client.gui.render.CustomFont;
import com.veron.bloodvisual.client.settings.BoolSetting;
import net.minecraft.client.gui.DrawContext;

/**
 * Компонент boolean настройки
 */
public class BooleanSettingComponent extends SettingComponent {
    private final BoolSetting setting;
    private static final float CHECKBOX_SIZE = 12;
    private static final int COLOR_TEXT = 0xFFB0B0B0;
    private static final int COLOR_CHECKBOX = 0xFFFFFFFF;
    private static final int COLOR_CHECKBOX_BG = 0xFF2A2A2A;

    public BooleanSettingComponent(BoolSetting setting, float x, float y, float width) {
        super(x, y, width);
        this.setting = setting;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta, float alpha) {
        CustomFont font = CustomFont.getInstance();

        // Текст настройки
        float textY = y + (getHeight() - font.getHeight()) / 2;
        int textColor = applyAlpha(COLOR_TEXT, alpha);
        font.draw(context, setting.getName(), x, textY, textColor);

        // Чекбокс справа
        float checkboxX = x + width - CHECKBOX_SIZE - 5;
        float checkboxY = y + (getHeight() - CHECKBOX_SIZE) / 2;

        // Фон чекбокса
        int bgColor = applyAlpha(COLOR_CHECKBOX_BG, alpha);
        context.fill((int)checkboxX, (int)checkboxY, 
                    (int)(checkboxX + CHECKBOX_SIZE), (int)(checkboxY + CHECKBOX_SIZE), 
                    bgColor);

        // Заливка если включен
        if (setting.getValue()) {
            int fillColor = applyAlpha(COLOR_CHECKBOX, alpha);
            context.fill((int)(checkboxX + 2), (int)(checkboxY + 2), 
                        (int)(checkboxX + CHECKBOX_SIZE - 2), (int)(checkboxY + CHECKBOX_SIZE - 2), 
                        fillColor);
        }
    }

    @Override
    public boolean onClick(int mouseX, int mouseY, int button) {
        if (isMouseOver(mouseX, mouseY) && button == 0) {
            setting.toggle();
            return true;
        }
        return false;
    }

    @Override
    public float getHeight() {
        return 20;
    }

    private int applyAlpha(int color, float alpha) {
        int a = (int)((color >> 24 & 0xFF) * alpha);
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = color & 0xFF;
        return (a << 24) | (r << 16) | (g << 8) | b;
    }
}
