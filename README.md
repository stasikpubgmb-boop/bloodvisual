# BloodVisual

<div align="center">

**Профессиональный визуальный/утилитарный клиент для Minecraft 1.21.4**

[![Fabric](https://img.shields.io/badge/Fabric-1.21.4-brightgreen)](https://fabricmc.net/)
[![Java](https://img.shields.io/badge/Java-21-orange)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

*Премиум ClickGUI в стиле Nursultan • Celestial • Expensive*

</div>

---

## ✨ Особенности

### 🎨 Профессиональный ClickGUI
- **Современный дизайн** в стиле 2025-2026 премиум-клиентов
- **Тёмная цветовая схема** без неона и rainbow эффектов
- **Размытый фон** за пределами GUI для глубины
- **Плавные анимации** с fade-in/out эффектами
- **Минималистичные иконки** и закруглённые углы
- **Responsive интерфейс** с hover эффектами

### ⚙️ Система модулей
- **5 категорий**: Combat, Player, Movement, Visuals, Misc
- **8+ модулей** с настраиваемыми параметрами
- **Настройки**: Boolean (чекбоксы), Number (слайдеры), Keybinds
- **Hot-toggle** модулей без перезагрузки

### 🎯 Включённые модули

#### Combat
- `Velocity` - контроль отбрасывания
- `Auto Clicker` - автоматический клик с настраиваемым CPS

#### Player  
- `No Fall` - защита от урона при падении

#### Movement
- `Sprint` - автоматический спринт

#### Visuals
- `Visual Enhance` - улучшение визуальных эффектов
- `Dark Mode` - затемнение экрана
- `Custom Particles` - кастомные частицы крови
- `Full Bright` - увеличение яркости

## 🎮 Управление

| Действие | Клавиша/Действие |
|----------|------------------|
| Открыть GUI | `Right Shift` |
| Закрыть GUI | `ESC` или `Right Shift` |
| Включить/выключить модуль | `ЛКМ` по модулю |
| Раскрыть настройки | `ПКМ` по модулю |
| Переключить категорию | `ЛКМ` по вкладке |
| Изменить слайдер | `ЛКМ + Drag` |
| Скролл модулей | `Колёсико мыши` |

## 📦 Установка

### Требования
- **Minecraft**: 1.21.4
- **Fabric Loader**: 0.16.0+
- **Fabric API**: 0.110.0+
- **Java**: 21+

### Шаги

1. **Установите Fabric Loader**:
   ```bash
   # Скачайте с https://fabricmc.net/use/installer/
   ```

2. **Скачайте мод**:
   - Releases: [GitHub Releases](https://github.com/veron/bloodvisual/releases)
   - Или соберите самостоятельно (см. ниже)

3. **Установите мод**:
   ```bash
   # Поместите JAR в папку mods
   cp bloodvisual-1.0.0.jar ~/.minecraft/mods/
   ```

4. **Запустите игру** с профилем Fabric

## 🔨 Сборка из исходников

```bash
# Клонировать репозиторий
git clone https://github.com/veron/bloodvisual.git
cd bloodvisual

# Собрать проект
./gradlew build

# JAR будет в build/libs/bloodvisual-1.0.0.jar
```

### Запуск в режиме разработки

```bash
# Запустить тестовый клиент
./gradlew runClient

# Запустить с hot-reload (если настроено)
./gradlew runClient --continuous
```

## 📚 Документация

- **[ClickGUI Documentation](CLICKGUI_DOCUMENTATION.md)** - полное описание GUI системы
- **[Development Guide](DEVELOPMENT_GUIDE.md)** - руководство по разработке
- **[Architecture](ARCHITECTURE.md)** - архитектура и диаграммы

## 🎨 Скриншоты

*Coming soon - скриншоты GUI будут добавлены после тестирования*

### Preview дизайна:

```
┌─────────────────────────────────────────────────────┐
│ [BV] Combat  Player  Movement  Visuals✗  Misc      │ ← Хедер с вкладками
├─────────────────────────────────────────────────────┤
│                                                     │
│ Visual Enhance                                  ✓   │ ← Включенный модуль
│   ☐ Smooth Camera                                  │
│   FOV Modifier                          1.0 ━━━━   │
│   ☑ Particle Multiplier                             │
│                                                     │
│ Dark Mode                                       ○   │ ← Выключенный
│                                                     │
│ Custom Particles                                ○   │
│                                                     │
│ Full Bright                                     ○   │
│                                                     │
└─────────────────────────────────────────────────────┘
```

## 🛠️ Технологии

- **Fabric API** - модификация Minecraft
- **LWJGL** - OpenGL рендеринг
- **Java 21** - современные языковые фичи
- **Gradle 8.11** - система сборки

## 🤝 Вклад в проект

Приветствуются Pull Request'ы!

1. Fork проекта
2. Создайте feature branch: `git checkout -b feature/AmazingFeature`
3. Commit изменения: `git commit -m 'Add some AmazingFeature'`
4. Push в branch: `git push origin feature/AmazingFeature`
5. Откройте Pull Request

### Идеи для улучшений

- [ ] Настоящий Gaussian Blur через шейдеры
- [ ] Загрузка кастомных TTF шрифтов
- [ ] Система сохранения конфигурации
- [ ] Дополнительные модули (Killaura, Fly, ESP и т.д.)
- [ ] Поиск модулей в GUI
- [ ] Темы оформления
- [ ] Поддержка других версий Minecraft

## 📄 Лицензия

MIT License - см. файл [LICENSE](LICENSE) для деталей

## 👤 Автор

**Veron**

- GitHub: [@veron](https://github.com/veron)
- Email: veron@example.com

## 🙏 Благодарности

- **FabricMC** - за отличный API
- **Nursultan, Celestial, Expensive** - вдохновение для дизайна GUI
- **Minecraft Community** - за поддержку и фидбек

---

<div align="center">

**⭐ Поставьте звезду, если проект понравился!**

*Made with ❤️ for the Minecraft community*

</div>
