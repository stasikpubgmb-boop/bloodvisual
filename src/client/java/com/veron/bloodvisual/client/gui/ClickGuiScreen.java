package com.veron.bloodvisual.client.gui;

import com.veron.bloodvisual.client.gui.animation.Animation;
import com.veron.bloodvisual.client.gui.components.CategoryTab;
import com.veron.bloodvisual.client.gui.components.ModuleButton;
import com.veron.bloodvisual.client.gui.render.BlurRenderer;
import com.veron.bloodvisual.client.gui.render.CustomFont;
import com.veron.bloodvisual.client.module.Category;
import com.veron.bloodvisual.client.module.Module;
import com.veron.bloodvisual.client.module.ModuleManager;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

/**
 * Главный экран ClickGUI в стиле Nursultan/Celestial/Expensive
 */
public class ClickGuiScreen extends Screen {
    // Размеры и позиции
    private static final int GUI_WIDTH = 500;
    private static final int GUI_HEIGHT = 400;
    private static final int HEADER_HEIGHT = 34;
    private static final int TAB_WIDTH = 90;
    private static final int TAB_SPACING = 4;
    
    // Цвета
    private static final int COLOR_BG = 0xF00D0D0D;           // Основной фон #0D0D0D alpha ~94%
    private static final int COLOR_HEADER_BG = 0xF0111111;    // Фон хедера
    private static final int COLOR_LOGO_BG = 0xFF1A1A1A;      // Фон логотипа
    private static final int COLOR_LOGO_TEXT = 0xFFFFFFFF;    // Белый текст логотипа

    private int guiX, guiY;
    private final Animation fadeAnimation = new Animation(0.15f);
    
    private Category selectedCategory = Category.VISUALS;
    private final List<CategoryTab> categoryTabs = new ArrayList<>();
    private final List<ModuleButton> moduleButtons = new ArrayList<>();
    
    private float scrollOffset = 0;
    private float targetScrollOffset = 0;
    private int contentHeight = 0;

    public ClickGuiScreen() {
        super(Text.literal("BloodVisual ClickGUI"));
    }

    @Override
    protected void init() {
        super.init();
        
        // Центрируем GUI
        guiX = (width - GUI_WIDTH) / 2;
        guiY = (height - GUI_HEIGHT) / 2;

        // Инициализация категорий
        initCategoryTabs();
        
        // Инициализация модулей для выбранной категории
        updateModuleList();
        
        // Запуск fade-in анимации
        fadeAnimation.setValue(0);
        fadeAnimation.setTarget(1.0f);
    }

    private void initCategoryTabs() {
        categoryTabs.clear();
        
        // Логотип слева
        float tabX = guiX + 10;
        float tabY = guiY + 5;

        // Вкладки категорий
        tabX += 40; // Отступ после логотипа
        
        for (Category category : Category.values()) {
            CategoryTab tab = new CategoryTab(category, tabX, tabY, TAB_WIDTH, HEADER_HEIGHT - 10);
            tab.setActive(category == selectedCategory);
            categoryTabs.add(tab);
            tabX += TAB_WIDTH + TAB_SPACING;
        }
    }

    private void updateModuleList() {
        moduleButtons.clear();
        
        List<Module> modules = ModuleManager.getInstance().getModulesByCategory(selectedCategory);
        
        float buttonY = 0;
        float buttonX = guiX + 10;
        float buttonWidth = GUI_WIDTH - 20;
        float buttonHeight = 32;

        for (Module module : modules) {
            ModuleButton button = new ModuleButton(module, buttonX, guiY + HEADER_HEIGHT + buttonY, buttonWidth, buttonHeight);
            moduleButtons.add(button);
            buttonY += buttonHeight;
        }
        
        contentHeight = (int) buttonY;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // Обновление анимации
        fadeAnimation.update(delta);
        float alpha = fadeAnimation.getValue();

        // 1. Размытый фон за пределами GUI
        BlurRenderer.getInstance().renderBlurredBackground(context, width, height);

        // 2. Основной фон GUI (сплошной темный)
        int bgColorWithAlpha = applyAlpha(COLOR_BG, alpha);
        context.fill(guiX, guiY, guiX + GUI_WIDTH, guiY + GUI_HEIGHT, bgColorWithAlpha);

        // 3. Хедер с категориями
        renderHeader(context, mouseX, mouseY, delta, alpha);

        // 4. Модули
        renderModules(context, mouseX, mouseY, delta, alpha);

        super.render(context, mouseX, mouseY, delta);
    }

    private void renderHeader(DrawContext context, int mouseX, int mouseY, float delta, float alpha) {
        // Фон хедера
        int headerBg = applyAlpha(COLOR_HEADER_BG, alpha);
        context.fill(guiX, guiY, guiX + GUI_WIDTH, guiY + HEADER_HEIGHT, headerBg);

        // Логотип [BV]
        int logoX = guiX + 10;
        int logoY = guiY + 7;
        int logoSize = 20;
        
        int logoBg = applyAlpha(COLOR_LOGO_BG, alpha);
        context.fill(logoX, logoY, logoX + logoSize, logoY + logoSize, logoBg);
        
        CustomFont font = CustomFont.getInstance();
        int logoTextColor = applyAlpha(COLOR_LOGO_TEXT, alpha);
        font.drawCentered(context, "BV", logoX + logoSize / 2f, logoY + (logoSize - font.getHeight()) / 2f, logoTextColor);

        // Вкладки категорий
        for (CategoryTab tab : categoryTabs) {
            tab.setHovered(tab.isMouseOver(mouseX, mouseY));
            tab.render(context, mouseX, mouseY, delta);
        }
    }

    private void renderModules(DrawContext context, int mouseX, int mouseY, float delta, float alpha) {
        // Область для модулей с scissor для скролла
        int moduleAreaY = guiY + HEADER_HEIGHT;
        int moduleAreaHeight = GUI_HEIGHT - HEADER_HEIGHT;

        // Плавный скролл
        scrollOffset += (targetScrollOffset - scrollOffset) * 0.3f;

        // TODO: Добавить scissor для обрезки контента
        
        float offsetY = -scrollOffset;
        for (ModuleButton button : moduleButtons) {
            float buttonX = guiX + 10;
            button.setPosition(buttonX, moduleAreaY + offsetY);
            button.setHovered(button.isMouseOver(mouseX, mouseY));
            button.render(context, mouseX, mouseY, delta);
            offsetY += button.getHeight();
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int mx = (int) mouseX;
        int my = (int) mouseY;

        // Клик по вкладкам категорий
        for (CategoryTab tab : categoryTabs) {
            if (tab.isMouseOver(mx, my)) {
                if (selectedCategory != tab.getCategory()) {
                    selectedCategory = tab.getCategory();
                    updateCategorySelection();
                    updateModuleList();
                    scrollOffset = 0;
                    targetScrollOffset = 0;
                }
                return true;
            }
        }

        // Клик по модулям
        for (ModuleButton moduleButton : moduleButtons) {
            if (moduleButton.onClick(mx, my, button)) {
                return true;
            }
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        int mx = (int) mouseX;
        int my = (int) mouseY;

        for (ModuleButton moduleButton : moduleButtons) {
            moduleButton.onMouseDragged(mx, my, button, deltaX, deltaY);
        }

        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        // Скролл модулей
        int maxScroll = Math.max(0, contentHeight - (GUI_HEIGHT - HEADER_HEIGHT - 20));
        targetScrollOffset = Math.max(0, Math.min(maxScroll, targetScrollOffset - (float)verticalAmount * 20));
        return true;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        // Закрытие на ESC или RIGHT_SHIFT
        if (keyCode == GLFW.GLFW_KEY_ESCAPE || keyCode == GLFW.GLFW_KEY_RIGHT_SHIFT) {
            close();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    private void updateCategorySelection() {
        for (CategoryTab tab : categoryTabs) {
            tab.setActive(tab.getCategory() == selectedCategory);
        }
    }

    @Override
    public boolean shouldPause() {
        return false; // Не ставим игру на паузу
    }

    private int applyAlpha(int color, float alpha) {
        int a = (int)((color >> 24 & 0xFF) * alpha);
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = color & 0xFF;
        return (a << 24) | (r << 16) | (g << 8) | b;
    }
}
