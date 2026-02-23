# KMP Shared Components

Shared Kotlin Multiplatform components for Compose-based applications.

## Components

### Utils
- **BackButtonHandler** - Composable for handling back button press
- **CameraManager** - Camera launcher for capturing images
- **ColorPalette** - Color palette generator with ID-to-color mapping
- **Context** - Cross-platform context accessor
- **DateUtils** - Date and time utilities (getCurrentTimestamp, formatTimestamp, normalizeDateString, addDaysToDate)
- **EmailHandler** - Email sending functionality
- **FileHandler** - Interface for cross-platform file operations
- **JsonSettingsManager** - Generic settings manager with JSON serialization
- **Locale** - Language and locale management
- **Logger** - Centralized logging system with file output support
- **LoggerSettings** - Configuration for logger (log level, file paths)
- **LogLevel** - Logging levels enum (OFF, FATAL, ERROR, WARN, INFO, DEBUG, TRACE)
- **Platform** - Platform detection and file handler access
- **StringUtils** - String formatting and size formatting utilities
- **UrlHandler** - URL opening functionality

### UI Components
- **IntegerInput** - Integer input with +/- buttons
- **DoubleInput** - Double/decimal input with +/- buttons

## Platforms

- Android
- iOS (x64, arm64, simulatorArm64)

## License

MIT
