package services

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString
import models.SuperheroResponse

object SuperheroService {
    private const val API_KEY = "a8b7d0b3032c122efe0e1df15c53eb11"
    private const val BASE_URL = "https://www.superheroapi.com/api.php/$API_KEY"

    suspend fun fetchHeroIntelligence(heroName: String): Double? {
        val client = HttpClient()
        val url = "$BASE_URL/search/${heroName.replace(" ", "%20")}"

        return try {
            // Configurer Json pour ignorer les clés inconnues dans la réponse
            val response: HttpResponse = client.get(url)
            val json = Json {
                ignoreUnknownKeys = true // Ignorer les clés inconnues
            }

            // Désérialiser la réponse JSON dans le modèle SuperheroResponse
            val superheroResponse = json.decodeFromString<SuperheroResponse>(response.bodyAsText())

            // Extraire et calculer la moyenne des valeurs d'intelligence
            val intelligenceValues = superheroResponse.results
                ?.mapNotNull { it.powerstats?.intelligence?.toIntOrNull() }
                ?.filterNotNull()

            if (intelligenceValues.isNullOrEmpty()) null else intelligenceValues.average()
        } catch (e: Exception) {
            println("Erreur lors de la récupération des données pour $heroName : ${e.message}")
            null
        } finally {
            client.close()
        }
    }
}
