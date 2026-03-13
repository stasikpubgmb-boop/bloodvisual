package com.blood.visual.module;

import com.blood.visual.module.impl.movement.BunnyHop;
import com.blood.visual.module.impl.movement.Fly;
import com.blood.visual.module.impl.movement.NoFall;
import com.blood.visual.module.impl.movement.Sprint;
import com.blood.visual.module.impl.movement.Speed;
import com.blood.visual.module.impl.visual.ESP;
import com.blood.visual.module.impl.visual.Fullbright;
import com.blood.visual.module.impl.visual.NoFog;
import com.blood.visual.module.impl.visual.TargetESP;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private List<Module> modules;

    public ModuleManager() {
        modules = new ArrayList<>();

        modules.add(new TargetESP());
        modules.add(new ESP());
        modules.add(new Fullbright());
        modules.add(new NoFog());
        modules.add(new Speed());
        modules.add(new Sprint());
        modules.add(new Fly());
        modules.add(new NoFall());
        modules.add(new BunnyHop());
    }

    public List<Module> getModules() {
        return modules;
    }
}
