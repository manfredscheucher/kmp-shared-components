package utils

expect fun initializeLogger(fileHandler: FileHandler, settings: LoggerSettings)

data class LoggerSettings(
    val logLevel: LogLevel,
    val logFilePath: String = "log.txt",
    val dataFilePath: String = "data.json",
    val settingsFilePath: String = "settings.json",
    val filesDirPath: String = "."
)

object Logger {

    private var fileHandler: FileHandler? = null
    private lateinit var settings: LoggerSettings

    fun init(fileHandler: FileHandler, settings: LoggerSettings) {
        if (this.fileHandler == null) {
            this.fileHandler = fileHandler
        }
        this.settings = settings
    }

    fun updateSettings(settings: LoggerSettings) {
        this.settings = settings
    }

    suspend fun log(level: LogLevel, message: String, throwable: Throwable? = null) {
        if (fileHandler == null) {
            println("Logger fileHandler not initialized! losing message: $message")
            return
        }
        if (settings.logLevel == LogLevel.OFF) return
        if (level.ordinal > settings.logLevel.ordinal) return

        val timestamp = getCurrentTimestamp()
        val logMessage = buildString {
            append("$timestamp: [${level.name}] $message")
            if (throwable != null && settings.logLevel.ordinal >= LogLevel.DEBUG.ordinal) {
                append("\n")
                append(throwable.stackTraceToString())
            }
            append("\n")
        }

        println(logMessage.trimEnd())


        try {
            fileHandler?.appendText(settings.logFilePath, logMessage)
        } catch (e: Exception) {
            println("Error writing to log file: ${e.message}")
        }
    }

    suspend fun logImportantFiles(level: LogLevel) {
        if (fileHandler == null) {
            println("Logger fileHandler not initialized!")
            return
        }
        if (settings.logLevel == LogLevel.OFF) return
        if (level.ordinal > settings.logLevel.ordinal) return

        logFile(level, settings.dataFilePath)
        logFile(level, settings.settingsFilePath)
        logDirectoryContents(level, settings.filesDirPath)
    }

    private suspend fun logFile(level: LogLevel, filePath: String) {
        val content = try {
            fileHandler?.readText(filePath)
        } catch (e: Exception) {
            "Error reading file: ${e.message}"
        }
        log(level, "Content of $filePath:\n$content")
    }

    suspend fun logDirectoryContents(level: LogLevel, dirPath: String) {
        val fileList = try {
            fileHandler?.listFilesRecursively(dirPath)
        } catch (e: Exception) {
            listOf("Error listing directory: ${e.message}")
        }

        val hashedFiles = fileList?.map { filePath ->
            val hash = fileHandler?.getFileHash(filePath) ?: "NO HASH"
            "$hash  $filePath"
        }

        log(level, "Recursive listing of '$dirPath':\n${hashedFiles?.joinToString("\n")}")
    }
}
