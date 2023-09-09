import kotlinx.serialization.json.Json

object JsonBuilder {
    fun build(): Json = Json {
        prettyPrint = true
        isLenient = true
        ignoreUnknownKeys = true
    }
}