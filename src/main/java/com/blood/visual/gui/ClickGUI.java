package com.blood.visual.gui;

import com.blood.visual.module.Category;
import com.blood.visual.module.Module;
import com.blood.visual.module.ModuleManager;
import com.blood.visual.setting.BooleanSetting;
import com.blood.visual.setting.Setting;
import com.blood.visual.setting.SliderSetting;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClickGUI extends Screen {
    private static final int PW = 110, HH = 14, MH = 12, GAP = 5;
    private final Map<Category, int[]> pos = new HashMap<>();
    private Category dragging = null;
    private int dox, doy;
    private Module settingsMod = null;
    private Module keybindMod = null;

    public ClickGUI() {
        super(Text.literal("ClickGUI"));
        int x = 5;
        for (Category c : Category.values()) {
            pos.put(c, new int[]{x, 5});
            x += PW + GAP;
        }
    }

    @Override
    public void render(DrawContext ctx, int mx, int my, float delta) {
        ctx.fill(0, 0, this.width, this.height, 0x88000000);
        for (Category cat : Category.values()) {
            int[] p = pos.get(cat);
            int px = p[0], py = p[1];
            List<Module> mods = ModuleManager.getModulesByCategory(cat);
            int ph = HH + mods.size() * MH;
            ctx.fill(px, py, px + PW, py + ph, 0xDD1a1a1a);
            ctx.fill(px, py, px + PW, py + HH, getCatColor(cat));
            ctx.drawTextWithShadow(textRenderer, cat.name(), px + 3, py + 3, 0xFFFFFFFF);
            int ry = py + HH;
            for (Module m : mods) {
                boolean hov = mx >= px && mx <= px + PW && my >= ry && my <= ry + MH;
                ctx.fill(px, ry, px + PW, ry + MH, hov ? 0xDD2a2a2a : 0xDD1a1a1a);
                ctx.fill(px, ry, px + 3, ry + MH, m.isEnabled() ? 0xFF00FF88 : 0xFF555555);
                ctx.drawTextWithShadow(textRenderer, m.getName(), px + 5, ry + 2, m.isEnabled() ? 0xFFFFFFFF : 0xFFAAAAAA);
                if (m.getKey() != -1) {
                    String k = "[" + m.getKeyName() + "]";
                    ctx.drawTextWithShadow(textRenderer, k, px + PW - textRenderer.getWidth(k) - 2, ry + 2, 0xFF888888);
                }
                ry += MH;
            }
        }
        if (settingsMod != null) renderSettings(ctx);
        if (keybindMod != null) {
            ctx.fill(width/2 - 80, height/2 - 10, width/2 + 80, height/2 + 10, 0-DD000000);
            ctx.drawCenteredTextWithShadow(textRenderer, Text.literal("Бинд: " + keybindMod.getName()), width/2, height/2 - 4, 0xFFFFFF00);
        }
        super.render(ctx, mx, my, delta);
    }

    private void renderSettings(DrawContext ctx) {
        List<Setting<?>> settings = settingsMod.getSettings();
        if (settings.isEmpty()) return;
        int sx = width/2 - 75, sy = height/2 - (settings.size() * 16 + 20) / 2;
        int sw = 150;
        ctx.fill(sx, sy, sx + sw, sy + settings.size() * 16 + 20, 0x55000000);
    }
}
