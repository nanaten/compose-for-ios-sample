package data

import kotlinx.serialization.Serializable

@Serializable
data class Breeds(
    val breeds: List<Cat>,
    val url: String,
) {
}
