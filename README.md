# KMP Shared Components

Shared Kotlin Multiplatform components for Compose-based applications.

## Components

### NumberInput
- `IntegerInput`: Input field with +/- buttons for integer values
- `DoubleInput`: Input field with +/- buttons for decimal values

## Usage

Add to your project's `settings.gradle.kts`:

```kotlin
includeBuild("../kmp-shared-components")
```

Add dependency in `build.gradle.kts`:

```kotlin
dependencies {
    implementation("com.manfredscheucher.kmp.shared:library:1.0.0")
}
```

Import and use:

```kotlin
import components.IntegerInput
import components.DoubleInput

@Composable
fun MyScreen() {
    var count by remember { mutableStateOf(0) }
    IntegerInput(
        value = count,
        onValueChange = { count = it },
        min = 0,
        max = 100
    )
}
```

## Platforms

- Android
- iOS (x64, arm64, simulatorArm64)

## License

MIT
