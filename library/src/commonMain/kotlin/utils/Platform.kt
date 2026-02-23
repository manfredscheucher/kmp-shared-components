package utils

interface Platform {
    val name: String
    val fileHandler: FileHandler
}

expect fun getPlatform(context: Any? = null): Platform
