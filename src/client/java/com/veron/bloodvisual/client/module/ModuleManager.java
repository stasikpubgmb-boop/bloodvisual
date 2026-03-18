package com.veron.bloodvisual.client.module;

import com.veron.bloodvisual.client.module.modules.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Менеджер всех модулей (Singleton)
 */
public class ModuleManager {
    private static ModuleManager instance;
    private final List<Module> modules = new ArrayList<>();

    private ModuleManager() {
        // Регистрация модулей - Combat
        registerModule(new Velocity());
        registerModule(new AutoClicker());
        
        // Player
        registerModule(new NoFall());
        
        // Movement
        registerModule(new Sprint());
        
        // Visuals
        registerModule(new VisualEnhance());
        registerModule(new DarkModeToggle());
        registerModule(new CustomParticles());
        registerModule(new FullBright());
    }

    public static ModuleManager getInstance() {
        if (instance == null) {
            instance = new ModuleManager();
        }
        return instance;
    }

    private void registerModule(Module module) {
        modules.add(module);
    }

    public List<Module> getModules() {
        return modules;
    }

    public List<Module> getModulesByCategory(Category category) {
        return modules.stream()
                .filter(m -> m.getCategory() == category)
                .collect(Collectors.toList());
    }

    public Module getModuleByName(String name) {
        return modules.stream()
                .filter(m -> m.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
