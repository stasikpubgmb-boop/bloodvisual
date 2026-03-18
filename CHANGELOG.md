# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.0.0] - 2026-03-18

### Added - Initial Release

#### ClickGUI System
- Professional ClickGUI in Nursultan/Celestial/Expensive style
- Dark theme (#0D0D0D base) with smooth animations
- Blurred background outside GUI
- 5 category tabs: Combat, Player, Movement, Visuals, Misc
- Module list with expand/collapse functionality
- Smooth fade-in/out animations (200ms)
- Custom font system (ready for TTF fonts)
- Hover effects on all interactive elements
- Scroll support for module lists
- Right Shift keybind to open GUI

#### GUI Components
- **CategoryTab**: Horizontal tabs with active/inactive states
- **ModuleButton**: Expandable module cards with settings
- **BooleanSettingComponent**: Checkbox (12x12px) for boolean settings
- **SliderSettingComponent**: Draggable sliders for number settings
- **BlurRenderer**: Background blur/gradient effect
- **Animation**: Smooth interpolation system

#### Module System
- Module base class with enable/disable lifecycle
- Setting system (Boolean, Number, Keybind)
- ModuleManager singleton for module registration
- Category enum with icons

#### Modules Included

**Combat (2)**:
- Velocity - Control knockback percentage (horizontal/vertical)
- Auto Clicker - Automatic clicking with configurable CPS (1-20)

**Player (1)**:
- No Fall - Prevent fall damage with packet mode

**Movement (1)**:
- Sprint - Automatic sprinting with omni-sprint option

**Visuals (4)**:
- Visual Enhance - Visual improvements (smooth camera, FOV modifier)
- Dark Mode - Screen darkening with adjustable darkness level
- Custom Particles - Custom blood particles with physics
- Full Bright - Increase brightness (1-15)

#### Documentation
- Comprehensive README.md with installation guide
- CLICKGUI_DOCUMENTATION.md - Full GUI system documentation
- DEVELOPMENT_GUIDE.md - Developer guide with examples
- ARCHITECTURE.md - Architecture diagrams and design patterns
- CHANGELOG.md - Version history

#### Technical
- Fabric 1.21.4 support
- Java 21 requirement
- Fabric API integration
- Gradle 8.11 build system
- Clean code with Russian comments
- MIT License

### Technical Details

**Colors**:
- Background: #0D0D0D (alpha 94%)
- Modules: #111111-#151515
- Hover: #1E1E1E
- Active: #1A2A1A (dark green)
- Text: #E0E0E0 (light gray)
- Active text: #FFFFFF (white)
- Separators: #333333
- Slider: #AAAAAA

**Performance**:
- Delta-based animations for framerate independence
- Lazy updates (only when GUI is open)
- Optimized rendering pipeline
- Minimal memory footprint

**Architecture**:
- Singleton pattern for managers
- Factory pattern for setting components
- Observer pattern for events
- Strategy pattern for settings

### Known Limitations

- TTF font loading not yet implemented (uses default font)
- True Gaussian blur not implemented (uses gradient)
- No configuration save/load system
- Scissor clipping for scroll not implemented
- Rounded corners are simulated (not true rounded)
- KeybindSetting component render not implemented

### Future Plans

See README.md for planned improvements:
- Real Gaussian blur through shaders
- TTF font loading (Inter/Manrope)
- Configuration system (JSON)
- Additional modules (Killaura, Fly, ESP, etc.)
- Module search functionality
- Theme system
- Multi-version support

---

## Version History

- **1.0.0** (2026-03-18) - Initial release with professional ClickGUI
