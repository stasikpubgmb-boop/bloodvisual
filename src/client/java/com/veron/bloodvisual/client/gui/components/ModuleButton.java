package com.veron.bloodvisual.client.gui.components;

import com.veron.bloodvisual.client.gui.animation.Animation;
import com.veron.bloodvisual.client.gui.components.settings.BooleanSettingComponent;
import com.veron.bloodvisual.client.gui.components.settings.SettingComponent;
import com.veron.bloodvisual.client.gui.components.settings.SliderSettingComponent;
import com.veron.bloodvisual.client.gui.render.CustomFont;
import com.veron.bloodvisual.client.module.Module;
import com.veron.bloodvisual.client.settings.BoolSetting;
import com.veron.bloodvisual.client.settings.NumberSetting;
import com.veron.bloodvisual.client.settings.Setting;
import net.minecraft.client.gui.DrawContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Кнопка модуля в списке
 */
public class ModuleButton {
    private final Module module;
    private float x, y, width, height;
    private boolean expanded = false;
    private boolean hovered = false;

    private final Animation hoverAnimation = new Animation(0.15f);
    private final Animation expandAnimation = new Animation(0.2f);
    
    private final List<SettingComponent> settingComponents = new ArrayList<>();
    private float settingsHeight = 0;

    // Цвета
    private static final int COLOR_BG = 0xF0111111;           // #111111
    private static final int COLOR_BG_HOVER = 0xF01E1E1E;     // #1E1E1E при наведении
    private static final int COLOR_BG_ENABLED = 0xF01A2A1A;   // Темно-зеленый если включен
    private static final int COLOR_SEPARATOR = 0xFF333333;    // Разделитель
    private static final int COLOR_TEXT = 0xFFE0E0E0;         // Светло-серый текст
    private static final int COLOR_TEXT_ENABLED = 0xFFFFFFFF; // Белый если включен
    private static final int COLOR_STATUS_ON = 0xFFFFFFFF;    // Белый для ✓
    private static final int COLOR_STATUS_OFF = 0xFF888888;   // Серый для ○

    public ModuleButton(Module module, float x, float y, float width, float height) {
        this.module = module;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        // Создаем компоненты настроек
        initSettings();
    }

    private void initSettings() {
        float settingY = 0;
        for (Setting<?> setting : module.getSettings()) {
            SettingComponent component = null;
            
            if (setting instanceof BoolSetting) {
                component = new BooleanSettingComponent((BoolSetting) setting, 0, settingY, width - 20);
            } else if (setting instanceof NumberSetting) {
                component = new SliderSettingComponent((NumberSetting) setting, 0, settingY, width - 20);
            }
            
            if (component != null) {
                settingComponents.add(component);
                settingY += component.getHeight();
            }
        }
        settingsHeight = settingY;
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // Обновление анимаций
        hoverAnimation.update(delta);
        expandAnimation.update(delta);

        // Фон модуля
        int bgColor = module.isEnabled() ? COLOR_BG_ENABLED : 
                      (hovered ? COLOR_BG_HOVER : COLOR_BG);
        context.fill((int)x, (int)y, (int)(x + width), (int)(y + height), bgColor);

        // Верхний разделитель
        context.fill((int)x, (int)y, (int)(x + width), (int)y + 1, COLOR_SEPARATOR);

        // Текст модуля
        CustomFont font = CustomFont.getInstance();
        int textColor = module.isEnabled() ? COLOR_TEXT_ENABLED : COLOR_TEXT;
        float textX = x + 10;
        float textY = y + (height - font.getHeight()) / 2;
        font.draw(context, module.getName(), textX, textY, textColor);

        // Статус справа (✓ если включен, ○ если нет)
        String statusIcon = module.isEnabled() ? "✓" : "○";
        int statusColor = module.isEnabled() ? COLOR_STATUS_ON : COLOR_STATUS_OFF;
        float statusX = x + width - 20;
        font.draw(context, statusIcon, statusX, textY, statusColor);

        // Рендер настроек если раскрыт
        if (expanded && expandAnimation.getValue() > 0) {
            float settingsAlpha = expandAnimation.getValue();
            renderSettings(context, mouseX, mouseY, delta, settingsAlpha);
        }
    }

    private void renderSettings(DrawContext context, int mouseX, int mouseY, float delta, float alpha) {
        float settingY = y + height;
        float settingX = x + 10; // Отступ слева

        for (SettingComponent component : settingComponents) {
            component.setPosition(settingX, settingY);
            component.render(context, mouseX, mouseY, delta, alpha);
            settingY += component.getHeight();
        }
    }

    public boolean onClick(int mouseX, int mouseY, int button) {
        if (isMouseOver(mouseX, mouseY)) {
            if (button == 0) { // ЛКМ - toggle модуля
                module.toggle();
                return true;
            } else if (button == 1) { // ПКМ - раскрыть настройки
                if (!module.getSettings().isEmpty()) {
                    expanded = !expanded;
                    expandAnimation.setTarget(expanded ? 1.0f : 0.0f);
                }
                return true;
            }
        }

        // Клик по настройкам
        if (expanded) {
            for (SettingComponent component : settingComponents) {
                if (component.onClick(mouseX, mouseY, button)) {
                    return true;
                }
            }
        }

        return false;
    }

    public void onMouseDragged(int mouseX, int mouseY, int button, double deltaX, double deltaY) {
        if (expanded) {
            for (SettingComponent component : settingComponents) {
                component.onMouseDragged(mouseX, mouseY, button, deltaX, deltaY);
            }
        }
    }

    public boolean isMouseOver(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }

    public void setHovered(boolean hovered) {
        this.hovered = hovered;
        hoverAnimation.setTarget(hovered ? 1.0f : 0.0f);
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getHeight() {
        float h = height;
        if (expanded) {
            h += settingsHeight * expandAnimation.getValue();
        }
        return h;
    }

    public Module getModule() {
        return module;
    }
}
