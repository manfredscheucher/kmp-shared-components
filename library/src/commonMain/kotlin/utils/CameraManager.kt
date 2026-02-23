package utils

import androidx.compose.runtime.Composable

@Composable
expect fun rememberCameraLauncher(onResult: (ByteArray?) -> Unit): CameraLauncher?

expect class CameraLauncher {
    fun launch()
}
