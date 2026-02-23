package utils

import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

class JsonSettingsManager<T>(
    private val fileHandler: FileHandler,
    private val filePath: String,
    private val defaultSettings: T,
    private val json: Json = Json
) {

    var settings: T = defaultSettings
        private set

    suspend inline fun <reified T> loadSettings(): T {
        val content = fileHandler.readText(filePath)
        settings = if (content.isNotEmpty()) {
            try {
                json.decodeFromString<T>(content)
            } catch (e: SerializationException) {
                Logger.log(LogLevel.ERROR, "Error decoding settings JSON: ${e.message}", e)
                defaultSettings as T
            } catch (e: Exception) {
                Logger.log(LogLevel.ERROR, "An unexpected error occurred while loading settings: ${e.message}", e)
                defaultSettings as T
            }
        } else {
            defaultSettings as T
        } as T
        return settings as T
    }

    suspend inline fun <reified T> saveSettings(settings: T) {
        this.settings = settings as @kotlin.Suppress("UNCHECKED_CAST") T
        val content = json.encodeToString(serializer<T>(), settings)
        fileHandler.writeText(filePath, content)
    }
}
