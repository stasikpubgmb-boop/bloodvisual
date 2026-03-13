package com/blood/visual/gui;

import com.blood.visual.module.Module;
import com.blood.visual.module.ModuleManager;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.DrawContext;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.math.MathHelper;

import java.util.ArrayList;
import java.util.List;

public class ClickGUI {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 200;
    private static final int CATEGORY_TAB_WIDTH = 100;
    private static final int CATEGORY_TAB_HEIGHT = 30;
    private static final int MODULE_ROW_HEIGHT = 20;

    private final Screen parent;
    private final ModuleManager moduleManager;
    private final List<ButtonWidget> categoryTabs;
    private final List<ModuleRow> moduleRows;
    private Module.Category selectedCategory;

    public ClickGUI(Screen parent, ModuleManager moduleManager) {
        this.parent = parent;
        this.moduleManager = moduleManager;
        this.categoryTabs = new ArrayList<>();
        this.moduleRows = new ArrayList<>();
        this.selectedCategory = Module.Category.COMBAT;

        initCategoryTabs();
        initModuleRows();
    }

    private void initCategoryTabs() {
        for (Module.Category category : Module.Category.values()) {
            ButtonWidget tab = new ButtonWidget(0, 0, CATEGORY_TAB_WIDTH, CATEGORY_TAB_HEIGHT, category.name());
            tab.active = category == selectedCategory;
            categoryTabs.add(tab);
        }
    }

    private void initModuleRows() {
        for (Module module : moduleManager.getModules()) {
            if (module.getCategory() == selectedCategory) {
                moduleRows.add(new ModuleRow(module));
            }
        }
    }

    public void render(DrawContext ctx) {
        ctx.fill(0, 0, WIDTH, HEIGHT, 0xCC0D0D0D);

        for (ButtonWidget tab : categoryTabs) {
            tab.x = 0;
            tab.y += CATEGORY_TAB_HEIGHT;
            tab.render(ctx, 0, 0);
        }

        for (ModuleRow row : moduleRows) {
            row.x = CATEGORY_TAB_WIDTH;
            row.y += MODULE_ROW_HEIGHT;
            row.render(ctx, 0, 0);
        }
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        for (ButtonWidget tab : categoryTabs) {
            if (tab.isMouseOver(mouseX, mouseY)) {
                selectedCategory = Module.Category.COMBAT; // TO DO: fix this
                initModuleRows();
                return;
            }
        }

        for (ModuleRow row : moduleRows) {
            if (row.isMouseOver(mouseX, mouseY)) {
                if (button == 0) {
                    row.module.toggle();
                }
            }
        }
    }

    class ModuleRow {
        public Module module;
        public int x;
        public int y;

        public ModuleRow(Module module) {
            this.module = module;
            this.x = 0;
            this.y = 0;
        }

        public void render(DrawContext ctx, int mouseX, int mouseY) {
            ctx.fill(x, y, x + 100, y + 20, 0x88000000);
            ctx.drawString(module.getName(), x + 5, y + 5);
        }

        public boolean isMouseOver(double mouseX, double mouseY) {
            return mouseX >= x && mouseX <= x + 100 && mouseY >= y && mouseY <= y + 20;
        }
    }
}
