package api

import JsonBuilder
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.resources.Resources
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json

class ApiClient {
    fun create(): HttpClient = HttpClient {
        install(ContentNegotiation) {
            json(JsonBuilder.build())
        }
        install(Resources)
        defaultRequest {
            host = "api.thecatapi.com"
            url {
                protocol = URLProtocol.HTTPS
            }
        }
    }
}
