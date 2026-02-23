package utils

import kotlinx.serialization.Serializable

@Serializable
enum class LogLevel {
    OFF,
    FATAL,
    ERROR,
    WARN,
    INFO,
    DEBUG,
    TRACE
}
