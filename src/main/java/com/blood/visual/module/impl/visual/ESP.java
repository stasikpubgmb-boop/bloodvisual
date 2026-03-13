package com.blood.visual.module.impl.visual;
import com.blood.visual.module.Category;
import com.blood.visual.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
public class ESP extends Module {
    public ESP() { super("ESP", Category.VISUAL); }
    @Override public void onTick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.world == null || mc.player == null) return;
    }
}
