package com.blood.visual.module;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private final List<Module> modules;

    public ModuleManager() {
        this.modules = new ArrayList<>();
        init();
    }

    public void init() {
        // Initialize modules here
    }

    public List<Module> getModules() {
        return modules;
    }

    public List<Module> getByCategory(Category category) {
        List<Module> modulesInCategory = new ArrayList<>();
        for (Module module : modules) {
            if (module.getCategory() == category) {
                modulesInCategory.add(module);
            }
        }
        return modulesInCategory;
    }

    public void onTick() {
        for (Module module : modules) {
            module.onTick();
        }
    }

    public void onKey(int key) {
        for (Module module : modules) {
            module.onKey(key);
        }
    }
}
