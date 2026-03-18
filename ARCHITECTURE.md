# BloodVisual - Архитектура

## 📐 Общая структура

```mermaid
graph TD
    A[BloodVisual Mod] --> B[Client Side]
    A --> C[Common/Server Side]
    
    B --> D[BloodVisualClient]
    B --> E[GUI System]
    B --> F[Module System]
    
    D --> G[Keybind Registration]
    D --> H[Event Handlers]
    
    E --> I[ClickGuiScreen]
    E --> J[Components]
    E --> K[Rendering]
    
    F --> L[ModuleManager]
    F --> M[Modules]
    F --> N[Settings]
    
    I --> O[CategoryTabs]
    I --> P[ModuleButtons]
    
    J --> Q[CategoryTab]
    J --> R[ModuleButton]
    J --> S[SettingComponents]
    
    K --> T[CustomFont]
    K --> U[BlurRenderer]
    K --> V[Animation]
    
    L --> W[Module Registration]
    L --> X[Module Lookup]
    
    M --> Y[Combat Modules]
    M --> Z[Visual Modules]
    M --> AA[Movement Modules]
    M --> AB[Player Modules]
    
    N --> AC[BoolSetting]
    N --> AD[NumberSetting]
    N --> AE[KeybindSetting]
```

## 🏗️ Подробная архитектура компонентов

### 1. Client Initialization Flow

```mermaid
sequenceDiagram
    participant MC as Minecraft Client
    participant BVC as BloodVisualClient
    participant MM as ModuleManager
    participant KB as KeyBinding
    
    MC->>BVC: onInitializeClient()
    BVC->>MM: getInstance() (initialize)
    MM->>MM: registerModule() for each module
    BVC->>KB: registerKeyBinding(RIGHT_SHIFT)
    BVC->>MC: register ClientTickEvent
    
    loop Every Client Tick
        MC->>BVC: tick event
        BVC->>KB: check if key pressed
        alt Key Pressed
            BVC->>MC: setScreen(ClickGuiScreen)
        end
    end
```

### 2. GUI Rendering Pipeline

```mermaid
flowchart LR
    A[render start] --> B{fadeAnimation > 0?}
    B -->|Yes| C[BlurRenderer.renderBlur]
    B -->|No| Z[return]
    
    C --> D[Draw main BG]
    D --> E[renderHeader]
    E --> F[Draw logo BV]
    F --> G[Render CategoryTabs]
    
    G --> H[renderModules]
    H --> I{for each ModuleButton}
    I --> J[setPosition with scroll]
    J --> K[render ModuleButton]
    K --> L{expanded?}
    L -->|Yes| M[render settings]
    L -->|No| N[next button]
    M --> N
    
    N --> I
    I -->|done| O[super.render]
    O --> P[Update animations]
    P --> Z[end]
```

### 3. Module System Architecture

```mermaid
classDiagram
    class Module {
        -String name
        -String description
        -Category category
        -boolean enabled
        -int keybind
        -List~Setting~ settings
        +toggle()
        +setEnabled(boolean)
        #onEnable()
        #onDisable()
        +addSetting(Setting)
    }
    
    class ModuleManager {
        -static ModuleManager instance
        -List~Module~ modules
        +getInstance()
        +registerModule(Module)
        +getModules()
        +getModulesByCategory(Category)
        +getModuleByName(String)
    }
    
    class Category {
        <<enumeration>>
        COMBAT
        PLAYER
        MOVEMENT
        VISUALS
        MISC
    }
    
    class Setting~T~ {
        -String name
        -T value
        +getValue()
        +setValue(T)
    }
    
    class BoolSetting {
        +toggle()
    }
    
    class NumberSetting {
        -double min
        -double max
        -double increment
        +getPercentage()
    }
    
    ModuleManager o-- "*" Module
    Module --> Category
    Module o-- "*" Setting
    Setting <|-- BoolSetting
    Setting <|-- NumberSetting
```

### 4. GUI Component Hierarchy

```mermaid
classDiagram
    class Screen {
        <<Minecraft>>
        +init()
        +render()
        +mouseClicked()
        +keyPressed()
    }
    
    class ClickGuiScreen {
        -int guiX, guiY
        -Category selectedCategory
        -List~CategoryTab~ categoryTabs
        -List~ModuleButton~ moduleButtons
        -float scrollOffset
        +initCategoryTabs()
        +updateModuleList()
        +renderHeader()
        +renderModules()
    }
    
    class CategoryTab {
        -Category category
        -float x, y, width, height
        -boolean active, hovered
        -Animation hoverAnimation
        +render()
        +onClick()
        +isMouseOver()
    }
    
    class ModuleButton {
        -Module module
        -boolean expanded
        -List~SettingComponent~ settingComponents
        +render()
        +onClick()
        +renderSettings()
    }
    
    class SettingComponent {
        <<abstract>>
        #float x, y, width
        +render()*
        +onClick()*
        +getHeight()*
    }
    
    class BooleanSettingComponent {
        -BoolSetting setting
        +render()
        +onClick()
    }
    
    class SliderSettingComponent {
        -NumberSetting setting
        -boolean dragging
        +render()
        +onClick()
        +onMouseDragged()
    }
    
    Screen <|-- ClickGuiScreen
    ClickGuiScreen o-- "*" CategoryTab
    ClickGuiScreen o-- "*" ModuleButton
    ModuleButton o-- "*" SettingComponent
    SettingComponent <|-- BooleanSettingComponent
    SettingComponent <|-- SliderSettingComponent
```

## 🎨 Rendering Layer

### Color System

```
Цветовая палитра (ARGB):
├── Фоны
│   ├── 0xF00D0D0D - Основной GUI фон (94% непрозрачность)
│   ├── 0xF0111111 - Хедер и модули
│   ├── 0xF01E1E1E - Hover эффект
│   └── 0xF01A2A1A - Активный модуль (зелёный)
│
├── Текст
│   ├── 0xFFE0E0E0 - Обычный текст (светло-серый)
│   └── 0xFFFFFFFF - Активный текст (белый)
│
├── UI элементы
│   ├── 0xFF333333 - Разделители
│   ├── 0xFF888888 - Неактивный статус
│   ├── 0xFFFFFFFF - Активный статус
│   └── 0xFFAAAAAA - Слайдер
│
└── Эффекты
    ├── 0x99000000 - Размытие (60% прозрачность)
    └── 0x44000000 - Градиент начало
```

### Animation System

```mermaid
stateDiagram-v2
    [*] --> Idle: value = 0
    Idle --> Animating: setTarget(1.0)
    Animating --> Animating: update(delta)
    Animating --> Finished: |value - target| < 0.001
    Finished --> [*]
    
    note right of Animating
        Интерполяция:
        change = (target - value) * speed * delta
        value += change
    end note
```

## 🔄 User Interaction Flow

### Opening GUI

```mermaid
sequenceDiagram
    participant U as User
    participant K as Keyboard
    participant C as Client
    participant G as ClickGuiScreen
    participant A as Animation
    
    U->>K: Press RIGHT_SHIFT
    K->>C: keyPressed event
    C->>G: new ClickGuiScreen()
    G->>G: init()
    G->>A: fadeAnimation.setTarget(1.0)
    
    loop Render Loop
        C->>G: render()
        G->>A: update(delta)
        A-->>G: getValue() (0 → 1)
        G->>G: render with alpha
    end
```

### Module Interaction

```mermaid
sequenceDiagram
    participant U as User
    participant G as GUI
    participant MB as ModuleButton
    participant M as Module
    participant S as Setting
    
    U->>G: Left Click on Module
    G->>MB: onClick(LMB)
    MB->>M: toggle()
    M->>M: setEnabled(!enabled)
    M->>M: onEnable() / onDisable()
    
    U->>G: Right Click on Module
    G->>MB: onClick(RMB)
    MB->>MB: expanded = !expanded
    MB->>MB: expandAnimation.setTarget(1.0)
    
    U->>G: Click on Setting
    G->>MB: onClick()
    MB->>S: onClick()
    S->>S: toggle() / setValue()
```

## 📊 Data Flow

### Module State Management

```
User Input → GUI Event → ModuleButton → Module.toggle()
                                          ↓
                                   enabled = !enabled
                                          ↓
                              ┌───────────┴───────────┐
                              ↓                       ↓
                          onEnable()              onDisable()
                              ↓                       ↓
                      Apply effects          Remove effects
```

### Setting Synchronization

```
User adjusts slider → SliderSettingComponent.onMouseDragged()
                                   ↓
                      updateValue(mouseX) → percentage
                                   ↓
                      NumberSetting.setValue(value)
                                   ↓
                            Module reads value
                                   ↓
                          Apply setting effect
```

## 🧩 Extension Points

### Добавление новых компонентов

```mermaid
graph TD
    A[New Feature] --> B{Type?}
    B -->|Module| C[Extend Module class]
    B -->|Setting| D[Extend Setting class]
    B -->|GUI Component| E[Extend SettingComponent]
    
    C --> F[Implement onEnable/onDisable]
    F --> G[Add to ModuleManager]
    
    D --> H[Define value type]
    H --> I[Create GUI component for it]
    
    E --> J[Override render/onClick]
    J --> K[Add to ModuleButton factory]
```

## 🎯 Performance Considerations

### Optimization Points

1. **Rendering**:
   - Render только видимые компоненты (с учётом scroll)
   - Batch render calls где возможно
   - Использовать scissor для clipping

2. **Animations**:
   - Delta-based timing для smooth framerate independence
   - Early exit если анимация завершена
   - Lazy update (только при открытом GUI)

3. **Module Updates**:
   - Только enabled модули update'ятся
   - Event-driven вместо polling где возможно

## 🔐 Code Organization Principles

### SOLID Principles Applied

- **Single Responsibility**: Каждый компонент отвечает за одну задачу
- **Open/Closed**: Легко расширяется через наследование
- **Liskov Substitution**: Setting<T> работает с любым типом
- **Interface Segregation**: Компоненты имеют минимальные интерфейсы
- **Dependency Inversion**: Зависимость от абстракций (Module, Setting)

### Design Patterns

- **Singleton**: ModuleManager, BlurRenderer, CustomFont
- **Factory**: Creation of SettingComponents в ModuleButton
- **Observer**: Event handlers для клавиш
- **Strategy**: Разные типы Setting с общим интерфейсом

---

**Документ обновлён**: 2026-03-18  
**Версия архитектуры**: 1.0.0
