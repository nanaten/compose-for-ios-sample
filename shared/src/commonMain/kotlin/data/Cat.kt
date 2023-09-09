package data

import kotlinx.serialization.Serializable

@Serializable
data class Cat(
    val name: String,
    val description: String,
) {
}
