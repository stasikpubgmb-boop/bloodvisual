# BloodVisual ClickGUI - Документация

## 📋 Обзор

Профессиональный ClickGUI в стиле **Nursultan**, **Celestial**, **Expensive** (2025–2026 премиум-клиенты): современный, минималистичный, тёмный дизайн без неона/rainbow/читерского вида.

## 🎨 Дизайн

### Цветовая схема
- **Основной фон**: `#0D0D0D` (alpha ~94%)
- **Фон модулей**: `#111111` - `#151515`
- **Hover эффект**: `#1E1E1E`
- **Активный модуль**: `#1A2A1A` (тёмно-зелёный)
- **Текст**: `#E0E0E0` (светло-серый)
- **Активный текст**: `#FFFFFF` (белый)
- **Разделители**: `#333333`

### Особенности UI
- ✅ Размытый фон за пределами GUI (градиент + затемнение)
- ✅ Сплошной тёмный фон самого GUI без прозрачности
- ✅ Закруглённые углы (~5px) на вкладках
- ✅ Плавные анимации (fade in/out за ~200ms)
- ✅ Гладкий кастомный шрифт (готов для TTF)
- ✅ Минималистичные иконки (✗, ○, ✓)

## 🎮 Управление

### Открытие GUI
- **Клавиша**: `Right Shift` (правый Shift)
- **Альтернатива**: `Insert` (можно добавить)
- **Закрытие**: `ESC` или повторное нажатие `Right Shift`

### Взаимодействие
- **ЛКМ по модулю** - включить/выключить
- **ПКМ по модулю** - раскрыть/скрыть настройки
- **ЛКМ по вкладке** - переключить категорию
- **Скролл** - прокрутка списка модулей
- **ЛКМ + Drag на слайдере** - изменить значение

## 🏗️ Структура проекта

```
src/client/java/com/veron/bloodvisual/client/
├── gui/
│   ├── ClickGuiScreen.java              # Главный экран GUI
│   ├── components/
│   │   ├── CategoryTab.java             # Вкладка категории
│   │   ├── ModuleButton.java            # Кнопка модуля
│   │   └── settings/
│   │       ├── SettingComponent.java    # Абстрактный компонент
│   │       ├── BooleanSettingComponent.java  # Чекбокс
│   │       └── SliderSettingComponent.java   # Слайдер
│   ├── render/
│   │   ├── BlurRenderer.java            # Размытие фона
│   │   └── CustomFont.java              # Кастомный шрифт
│   └── animation/
│       └── Animation.java               # Плавные анимации
├── module/
│   ├── Module.java                      # Базовый класс модуля
│   ├── Category.java                    # Категории (enum)
│   ├── ModuleManager.java               # Менеджер модулей (Singleton)
│   └── modules/                         # Примеры модулей
│       ├── VisualEnhance.java
│       ├── DarkModeToggle.java
│       └── CustomParticles.java
└── settings/
    ├── Setting.java                     # Базовая настройка
    ├── BoolSetting.java                 # Boolean настройка
    ├── NumberSetting.java               # Числовая настройка
    └── KeybindSetting.java              # Кейбинд настройка
```

## 📦 Компоненты

### 1. ClickGuiScreen
Главный экран с:
- Центрированное окно 500×400px
- Верхняя панель (34px) с вкладками категорий
- Логотип [BV] слева
- Область модулей с поддержкой скролла
- Fade-in/out анимация при открытии/закрытии

### 2. CategoryTab
Вкладка категории:
- Размер: 90×24px
- Иконка справа (✗ для активной, ○ для неактивной)
- Hover эффект
- Плавная анимация переключения

### 3. ModuleButton
Кнопка модуля:
- Высота: 32px (+ высота настроек при раскрытии)
- ЛКМ - toggle модуля
- ПКМ - раскрыть настройки
- Статус справа (✓ если включен, ○ если нет)
- Тёмно-зелёный фон если включен

### 4. Setting Components

#### BooleanSettingComponent
- Квадратный чекбокс 12×12px
- Белая заливка если true
- Серый фон если false

#### SliderSettingComponent
- Тонкая линия с заполнением
- Значение отображается справа
- Поддержка drag для изменения

#### KeybindSettingComponent (TODO)
- Отображение текущей клавиши
- Режим listening для биндинга

## 🎯 Категории модулей

```java
public enum Category {
    COMBAT("Combat", "⚔"),      // Боевые модули
    PLAYER("Player", "◈"),      // Игровые модули
    MOVEMENT("Movement", "➤"),  // Движение
    VISUALS("Visuals", "◉"),    // Визуальные эффекты
    MISC("Misc", "✦")           // Разное
}
```

## 🔧 Как добавить модуль

### 1. Создать класс модуля

```java
package com.veron.bloodvisual.client.module.modules;

import com.veron.bloodvisual.client.module.Category;
import com.veron.bloodvisual.client.module.Module;
import com.veron.bloodvisual.client.settings.BoolSetting;
import com.veron.bloodvisual.client.settings.NumberSetting;

public class MyModule extends Module {
    private final BoolSetting myBool;
    private final NumberSetting myNumber;

    public MyModule() {
        super("My Module", "Описание модуля", Category.VISUALS);
        
        myBool = new BoolSetting("Enable Feature", true);
        myNumber = new NumberSetting("Amount", 5.0, 1.0, 10.0, 0.5);
        
        addSetting(myBool);
        addSetting(myNumber);
    }

    @Override
    protected void onEnable() {
        // Логика при включении
    }

    @Override
    protected void onDisable() {
        // Логика при выключении
    }
}
```

### 2. Зарегистрировать в ModuleManager

```java
// В ModuleManager.java, метод constructor
registerModule(new MyModule());
```

## 🎨 Добавление кастомного TTF шрифта

### Шаг 1: Поместить шрифт
Скопировать `Inter-Regular.ttf` или `Manrope-Regular.ttf` в:
```
src/client/resources/assets/bloodvisual/fonts/inter.ttf
```

### Шаг 2: Создать font provider (TODO)
Использовать Caxton или создать кастомный font provider через ресурспаки Minecraft.

### Шаг 3: Обновить CustomFont
Заменить стандартный `textRenderer` на загруженный кастомный шрифт.

## 🚀 Будущие улучшения

### Критичные (TODO)
- [ ] Настоящий Gaussian Blur через шейдеры
- [ ] Загрузка TTF шрифта (Inter/Manrope)
- [ ] Scissor для обрезки контента при скролле
- [ ] KeybindSettingComponent рендеринг
- [ ] Настоящие закруглённые углы (stencil buffer)

### Дополнительные
- [ ] Поиск модулей (search bar)
- [ ] Drag & Drop для перемещения GUI
- [ ] Сохранение конфигурации (JSON)
- [ ] Темы оформления (несколько цветовых схем)
- [ ] Анимация раскрытия настроек (expand/collapse)
- [ ] Tooltip при наведении на модули

## 🎨 Референсы дизайна

Вдохновлено:
- **Nursultan** - минималистичный, сдержанный стиль
- **Celestial** - премиум UI/UX, плавные анимации
- **Expensive** - тёмная цветовая схема, профессиональный вид

**Ключевые принципы**:
- Никакого неона/rainbow/glow
- Сплошной тёмный фон, не прозрачный
- Белый/серый текст, минимум акцентов
- Плавные анимации без резких переходов

## 📝 Примечания

### Производительность
- Blur реализован через простое затемнение + градиент для оптимизации
- Анимации оптимизированы через delta time
- Рендеринг только видимых компонентов (с учётом скролла)

### Совместимость
- Fabric 1.21.4
- Java 21
- Fabric API required

### Лицензия
MIT License - свободное использование и модификация

---

**Автор**: Veron  
**Версия**: 1.0.0  
**Дата**: 2026-03-18
