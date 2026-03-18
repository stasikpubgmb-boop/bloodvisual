package com.veron.bloodvisual.client.gui.components.settings;

import com.veron.bloodvisual.client.gui.render.CustomFont;
import com.veron.bloodvisual.client.settings.NumberSetting;
import net.minecraft.client.gui.DrawContext;

/**
 * Компонент slider настройки
 */
public class SliderSettingComponent extends SettingComponent {
    private final NumberSetting setting;
    private boolean dragging = false;

    private static final int COLOR_TEXT = 0xFFB0B0B0;
    private static final int COLOR_SLIDER_BG = 0xFF2A2A2A;
    private static final int COLOR_SLIDER_FILL = 0xFFAAAAAA;
    private static final int COLOR_VALUE = 0xFFE0E0E0;

    public SliderSettingComponent(NumberSetting setting, float x, float y, float width) {
        super(x, y, width);
        this.setting = setting;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta, float alpha) {
        CustomFont font = CustomFont.getInstance();

        // Название настройки
        float textY = y + 2;
        int textColor = applyAlpha(COLOR_TEXT, alpha);
        font.draw(context, setting.getName(), x, textY, textColor);

        // Значение справа
        String valueText = String.format("%.1f", setting.getValue());
        float valueX = x + width - font.getWidth(valueText) - 5;
        int valueColor = applyAlpha(COLOR_VALUE, alpha);
        font.draw(context, valueText, valueX, textY, valueColor);

        // Слайдер
        float sliderY = y + font.getHeight() + 4;
        float sliderHeight = 3;
        float sliderWidth = width - 10;

        // Фон слайдера
        int bgColor = applyAlpha(COLOR_SLIDER_BG, alpha);
        context.fill((int)x, (int)sliderY, 
                    (int)(x + sliderWidth), (int)(sliderY + sliderHeight), 
                    bgColor);

        // Заполненная часть
        float fillWidth = sliderWidth * setting.getPercentage();
        int fillColor = applyAlpha(COLOR_SLIDER_FILL, alpha);
        context.fill((int)x, (int)sliderY, 
                    (int)(x + fillWidth), (int)(sliderY + sliderHeight), 
                    fillColor);
    }

    @Override
    public boolean onClick(int mouseX, int mouseY, int button) {
        if (isMouseOver(mouseX, mouseY) && button == 0) {
            dragging = true;
            updateValue(mouseX);
            return true;
        }
        return false;
    }

    @Override
    public void onMouseDragged(int mouseX, int mouseY, int button, double deltaX, double deltaY) {
        if (dragging) {
            updateValue(mouseX);
        }
    }

    private void updateValue(int mouseX) {
        float sliderWidth = width - 10;
        float relativeX = mouseX - x;
        float percentage = Math.max(0, Math.min(1, relativeX / sliderWidth));
        
        double value = setting.getMin() + (setting.getMax() - setting.getMin()) * percentage;
        setting.setValue(value);
    }

    public void setDragging(boolean dragging) {
        this.dragging = dragging;
    }

    @Override
    public float getHeight() {
        return CustomFont.getInstance().getHeight() + 10;
    }

    private int applyAlpha(int color, float alpha) {
        int a = (int)((color >> 24 & 0xFF) * alpha);
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = color & 0xFF;
        return (a << 24) | (r << 16) | (g << 8) | b;
    }
}
