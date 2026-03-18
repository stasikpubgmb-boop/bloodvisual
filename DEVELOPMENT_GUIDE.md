# BloodVisual - Руководство разработчика

## 🚀 Быстрый старт

### Требования
- Java 21 JDK
- Gradle 8.11+ (wrapper уже включен)
- Git

### Сборка проекта

```bash
# Клонировать репозиторий
git clone https://github.com/veron/bloodvisual.git
cd bloodvisual

# Собрать проект
./gradlew build

# Запустить клиент Minecraft с модом
./gradlew runClient
```

### Структура файлов

```
bloodvisual/
├── src/
│   ├── main/                           # Серверная/общая часть
│   │   ├── java/
│   │   │   └── com/veron/bloodvisual/
│   │   │       └── BloodVisual.java   # Главный класс мода
│   │   └── resources/
│   │       ├── fabric.mod.json        # Метаданные мода
│   │       └── assets/bloodvisual/
│   │           └── lang/              # Переводы
│   └── client/                         # Клиентская часть
│       ├── java/
│       │   └── com/veron/bloodvisual/client/
│       │       ├── BloodVisualClient.java  # Клиентская инициализация
│       │       ├── gui/               # ClickGUI компоненты
│       │       ├── module/            # Система модулей
│       │       └── settings/          # Настройки модулей
│       └── resources/
│           ├── bloodvisual.client.mixins.json
│           └── assets/bloodvisual/
│               └── fonts/             # Кастомные шрифты (TODO)
├── build.gradle                        # Конфигурация Gradle
├── gradle.properties                   # Версии зависимостей
└── README.md
```

## 🎯 Основные концепции

### 1. Модули (Modules)

Модуль - это функциональность, которую можно включить/выключить через ClickGUI.

**Базовый класс**: `Module.java`

```java
public abstract class Module {
    private String name;
    private Category category;
    private boolean enabled;
    private List<Setting<?>> settings;
    
    protected void onEnable() { }
    protected void onDisable() { }
}
```

**Пример использования**:
```java
public class MyModule extends Module {
    public MyModule() {
        super("My Module", "Description", Category.VISUALS);
    }
    
    @Override
    protected void onEnable() {
        // Код при включении модуля
    }
}
```

### 2. Настройки (Settings)

Настройки позволяют настраивать поведение модулей.

**Типы настроек**:
- `BoolSetting` - да/нет (чекбокс)
- `NumberSetting` - числовое значение (слайдер)
- `KeybindSetting` - привязка клавиши

**Пример**:
```java
BoolSetting enableFeature = new BoolSetting("Enable", true);
NumberSetting radius = new NumberSetting("Radius", 5.0, 1.0, 10.0, 0.5);
addSetting(enableFeature);
addSetting(radius);
```

### 3. ClickGUI Screen

GUI построен на `Screen` из Minecraft.

**Основные методы**:
- `init()` - инициализация GUI
- `render()` - рендеринг каждый кадр
- `mouseClicked()` - обработка кликов
- `mouseScrolled()` - обработка скролла
- `keyPressed()` - обработка клавиш

### 4. Анимации

Плавные анимации через класс `Animation`:

```java
Animation fadeAnimation = new Animation(0.15f); // speed
fadeAnimation.setTarget(1.0f); // целевое значение
fadeAnimation.update(delta);   // обновление в render()
float currentValue = fadeAnimation.getValue();
```

## 🔧 Разработка

### Добавление нового модуля

1. **Создать класс**:
```java
// src/client/java/.../module/modules/MyNewModule.java
public class MyNewModule extends Module {
    public MyNewModule() {
        super("Module Name", "Description", Category.COMBAT);
    }
}
```

2. **Зарегистрировать**:
```java
// ModuleManager.java - constructor
registerModule(new MyNewModule());
```

3. **Протестировать**:
```bash
./gradlew runClient
# В игре нажать Right Shift
# Найти модуль в соответствующей категории
```

### Добавление новой категории

1. **Обновить enum**:
```java
// Category.java
public enum Category {
    COMBAT("Combat", "⚔"),
    WORLD("World", "🌍"),  // новая категория
    // ...
}
```

2. **Создать модули для категории**
3. **GUI автоматически добавит вкладку**

### Добавление нового типа настройки

1. **Создать класс настройки**:
```java
// settings/ColorSetting.java
public class ColorSetting extends Setting<Color> {
    public ColorSetting(String name, Color defaultValue) {
        super(name, defaultValue);
    }
}
```

2. **Создать компонент GUI**:
```java
// gui/components/settings/ColorSettingComponent.java
public class ColorSettingComponent extends SettingComponent {
    @Override
    public void render(DrawContext context, ...) {
        // Рендер color picker
    }
}
```

3. **Обновить ModuleButton**:
```java
// ModuleButton.java - initSettings()
if (setting instanceof ColorSetting) {
    component = new ColorSettingComponent(...);
}
```

## 🎨 Стилизация

### Изменение цветов

Все цвета определены как константы:

```java
// ClickGuiScreen.java
private static final int COLOR_BG = 0xF00D0D0D;
private static final int COLOR_TEXT = 0xFFE0E0E0;
```

Формат: `0xAARRGGBB` (Alpha, Red, Green, Blue)

### Изменение размеров

```java
// ClickGuiScreen.java
private static final int GUI_WIDTH = 500;
private static final int GUI_HEIGHT = 400;
private static final int TAB_WIDTH = 90;
```

### Изменение анимаций

```java
// Animation speed (чем больше - тем быстрее)
Animation animation = new Animation(0.15f);

// В ClickGuiScreen.java - fade duration
fadeAnimation.update(delta); // ~200ms при speed=0.15
```

## 🐛 Отладка

### Логирование

```java
import com.veron.bloodvisual.BloodVisual;

BloodVisual.LOGGER.info("Сообщение");
BloodVisual.LOGGER.warn("Предупреждение");
BloodVisual.LOGGER.error("Ошибка");
```

### Дебаг информация в GUI

```java
// В render() метод:
CustomFont font = CustomFont.getInstance();
font.draw(context, "Debug: " + someValue, 10, 10, 0xFFFFFFFF);
```

### Хотрелоад (LiveReload)

```bash
# Для быстрого тестирования изменений:
./gradlew runClient --console=plain

# Пересобрать без перезапуска (если возможно):
./gradlew build
```

## 📊 Тестирование

### Ручное тестирование

1. **Запустить клиент**:
```bash
./gradlew runClient
```

2. **Открыть GUI**: Нажать `Right Shift` в игре

3. **Проверить**:
   - [ ] Все категории отображаются
   - [ ] Модули кликабельны
   - [ ] ПКМ раскрывает настройки
   - [ ] Слайдеры работают (drag)
   - [ ] Чекбоксы переключаются
   - [ ] Скролл работает
   - [ ] ESC закрывает GUI

### Проверка сборки

```bash
# Очистить и собрать заново
./gradlew clean build

# Проверить JAR
ls -lh build/libs/
# Должен быть файл: bloodvisual-1.0.0.jar
```

## 📦 Релиз

### Подготовка к релизу

1. **Обновить версию**:
```properties
# gradle.properties
mod_version=1.0.0
```

2. **Собрать финальный JAR**:
```bash
./gradlew clean build
```

3. **Проверить артефакты**:
```bash
ls build/libs/
# bloodvisual-1.0.0.jar          - основной мод
# bloodvisual-1.0.0-sources.jar  - исходники
```

4. **Протестировать JAR**:
   - Скопировать в `.minecraft/mods/`
   - Запустить игру
   - Проверить функциональность

### Публикация

- **GitHub Releases**: Приложить JAR файлы
- **Modrinth/CurseForge**: Загрузить через их интерфейс
- **Описание**: Использовать README.md как основу

## 🔍 Полезные команды

```bash
# Компиляция
./gradlew compileJava
./gradlew compileClientJava

# Запуск
./gradlew runClient         # Клиент
./gradlew runServer         # Сервер (если нужно)

# Очистка
./gradlew clean

# Полная пересборка
./gradlew clean build

# Генерация исходников Minecraft (для изучения API)
./gradlew genSources

# Обновление зависимостей
./gradlew --refresh-dependencies
```

## 📚 Ресурсы

### Документация Fabric
- [Fabric Wiki](https://fabricmc.net/wiki/)
- [Fabric API Docs](https://maven.fabricmc.net/docs/fabric-api-0.92.0+1.21.4/)
- [Yarn Mappings](https://maven.fabricmc.net/docs/yarn-1.21.4/)

### Minecraft Rendering
- [LWJGL Docs](https://www.lwjgl.org/guide)
- [Blaze3D](https://github.com/MinecraftForge/RenderSystem)

### Примеры кода
- [Fabric Example Mods](https://github.com/FabricMC/fabric-example-mod)
- [Meteor Client](https://github.com/MeteorDevelopment/meteor-client) - reference for GUI

## ❓ FAQ

**Q: Как изменить клавишу открытия GUI?**  
A: В `BloodVisualClient.java` изменить `GLFW.GLFW_KEY_RIGHT_SHIFT` на другую клавишу.

**Q: Как добавить поддержку других версий Minecraft?**  
A: Обновить `minecraft_version` и `fabric_version` в `gradle.properties`, затем проверить API изменения.

**Q: Почему не работает кастомный шрифт?**  
A: Пока используется стандартный шрифт. TTF загрузка в TODO.

**Q: Как сделать настоящий Gaussian Blur?**  
A: Нужно использовать custom шейдеры через `PostProcessShader` или `Framebuffer` с blur passes.

**Q: Модуль не сохраняет состояние?**  
A: Система сохранения конфигурации ещё не реализована (TODO).

---

**Нужна помощь?** Открой Issue на GitHub!
