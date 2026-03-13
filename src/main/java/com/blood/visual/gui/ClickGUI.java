package com.blood.visual.gui;

import com.blood.visual.module.Category;
import com.blood.visual.module.Module;
import com.blood.visual.module.ModuleManager;
import com.blood.visual.setting.BooleanSetting;
import com.blood.visual.setting.Setting;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClickGUI extends Screen {
    // ...

    @Override
    public void render(DrawContext ctx, int mx, int my, float delta) {
        // ...

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
        ctx.fill(sx, sy, sx + sw, sy + (settings.size() * 16 + 20), 0x88000000); // Fix ctx
        // ...
    }
}
