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
            List<Module> mods = ModuleManager.getByCategory(cat);
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
            ctx.fill(width/2 - 80, height/2 - 10, width/2 + 80, height/2 + 10, 0xDD000000);
            ctx.drawCenteredTextWithShadow(textRenderer, Text.literal("Бинд: " + keybindMod.getName()), width/2, height/2 - 4, 0xFFFFFF00);
        }
        super.render(ctx, mx, my, delta);
    }

    private void renderSettings(DrawContext ctx) {
        List<Setting<?>> settings = settingsMod.getSettings();
        if (settings.isEmpty()) return;
        int sx = width/2 - 75, sy = height/2 - (settings.size() * 16 + 20) / 2;
        int sw = 150;
        ctx.fill(sx, sy, sx + sw, sy + settings.size() * 16 + 24, 0xEE111111);
        ctx.fill(sx, sy, sx + sw, sy + 14, 0xEE333399);
        ctx.drawTextWithShadow(textRenderer, settingsMod.getName(), sx + 3, sy + 3, 0xFFFFFFFF);
        int ry = sy + 16;
        for (Setting<?> s : settings) {
            ctx.fill(sx + 2, ry, sx + sw - 2, ry + 14, 0xDD222222);
            if (s instanceof BooleanSetting bs) {
                ctx.drawTextWithShadow(textRenderer, bs.getName(), sx + 5, ry + 3, 0xFFCCCCCC);
                ctx.fill(sx + sw - 14, ry + 2, sx + sw - 2, ry + 12, bs.getValue() ? 0xFF00FF88 : 0xFF555555);
            } else if (s instanceof SliderSetting ss) {
                ctx.drawTextWithShadow(textRenderer, ss.getName() + ": " + String.format("%.1f", ss.getValue()), sx + 5, ry + 3, 0xFFCCCCCC);
                float pct = (ss.getValue() - ss.getMin()) / (ss.getMax() - ss.getMin());
                ctx.fill(sx + 2, ry + 11, sx + sw - 2, ry + 13, 0xFF444444);
                ctx.fill(sx + 2, ry + 11, sx + 2 + (int)((sw - 4) * pct), ry + 13, 0xFF4488FF);
            }
            ry += 16;
        }
    }

    @Override
    public boolean mouseClicked(double mx, double my, int btn) {
        if (settingsMod != null) { settingsMod = null; return true; }
        for (Category cat : Category.values()) {
            int[] p = pos.get(cat);
            int px = p[0], py = p[1];
            List<Module> mods = ModuleManager.getByCategory(cat);
            if (mx >= px && mx <= px + PW && my >= py && my <= py + HH) {
                dragging = cat; dox = (int)(mx - px); doy = (int)(my - py); return true;
            }
            int ry = py + HH;
            for (Module m : mods) {
                if (mx >= px && mx <= px + PW && my >= ry && my <= ry + MH) {
                    if (btn == 0) m.toggle();
                    else if (btn == 1) settingsMod = m;
                    else if (btn == 2) keybindMod = m;
                    return true;
                }
                ry += MH;
            }
        }
        return super.mouseClicked(mx, my, btn);
    }

    @Override
    public boolean mouseDragged(double mx, double my, int btn, double dx, double dy) {
        if (dragging != null) { int[] p = pos.get(dragging); p[0] = (int)(mx - dox); p[1] = (int)(my - doy); return true; }
        return super.mouseDragged(mx, my, btn, dx, dy);
    }

    @Override
    public boolean mouseReleased(double mx, double my, int btn) { dragging = null; return super.mouseReleased(mx, my, btn); }

    @Override
    public boolean keyPressed(int kc, int sc, int mod) {
        if (keybindMod != null) { keybindMod.setKey(kc); keybindMod = null; return true; }
        return super.keyPressed(kc, sc, mod);
    }

    @Override public boolean shouldPause() { return false; }

    private int getCatColor(Category c) {
        return switch (c) {
            case COMBAT -> 0xDD991111;
            case MOVEMENT -> 0xDD116699;
            case VISUAL -> 0xDD119944;
            case MISC -> 0xDD886611;
        };
    }
}
