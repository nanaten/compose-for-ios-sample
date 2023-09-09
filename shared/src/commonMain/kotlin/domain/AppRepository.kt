package domain

import JsonBuilder
import api.ApiClient
import api.Search
import data.Breeds
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.resources.get
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.decodeFromJsonElement

class AppRepository {
    private val client: HttpClient = ApiClient().create()

    suspend fun fetchCats(): Result<List<Cat>> =
        runCatching {
            client.get(Search()).body<JsonArray>().parse()
        }

    private fun JsonArray.parse(): List<Cat> {
        val json = JsonBuilder.build()
        return this.mapNotNull { element ->
            json.decodeFromJsonElement<Breeds>(element)
        }.toDomain()
    }

    private fun List<Breeds>.toDomain(): List<Cat> =
        this.map {
            Cat(
                name = it.breeds.first().name,
                imageUrl = it.url,
                description = it.breeds.first().description,
            )
        }
}
