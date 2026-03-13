package com.blood.visual.module.impl.visual;
import com.blood.visual.module.Category;
import com.blood.visual.module.Module;
import net.minecraft.client.MinecraftClient;
public class Fullbright extends Module {
    private double prevGamma = 1.0;
    public Fullbright() { super("Fullbright", Category.VISUAL); }
    @Override public void onEnable() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.options == null) return;
        prevGamma = mc.options.getGamma().getValue();
        mc.options.getGamma().setValue(10.0);
    }
    @Override public void onDisable() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.options == null) return;
        mc.options.getGamma().setValue(prevGamma);
    }
}
