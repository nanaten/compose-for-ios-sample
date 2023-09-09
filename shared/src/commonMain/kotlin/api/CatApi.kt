package api

import io.ktor.resources.Resource

@Resource("v1/images/search")
class Search(
    val api_key: String? = "{YOUR_API_KEY}",
    val has_breeds: String? = "1",
    val limit: String? = "30"
)