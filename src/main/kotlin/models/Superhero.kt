package models

import kotlinx.serialization.Serializable

@Serializable
data class SuperheroResponse(
    val response: String,
    val results: List<Hero>?
)

@Serializable
data class Hero(
    val name: String,
    val powerstats: PowerStats?
)

@Serializable
data class PowerStats(
    val intelligence: String?
)

