package com.blood.visual.module;

import com.blood.visual.module.impl.movement.*;
import com.blood.visual.module.impl.visual.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleManager {
    private static final List<Module> modules = new ArrayList<>();

    public static void init() {
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

    public static List<Module> getModules() { return modules; }

    public static List<Module> getByCategory(Category cat) {
        return modules.stream().filter(m -> m.getCategory() == cat).collect(Collectors.toList());
    }

    public static void onTick() {
        for (Module m : modules) if (m.isEnabled()) m.onTick();
    }

    public static void onKey(int key) {
        for (Module m : modules) if (m.getKey() == key) m.toggle();
    }
}
