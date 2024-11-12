import services.SuperheroService
import utils.InputValidator

fun main() {

    println("Liste des super héros compatibles: https://www.superheroapi.com/ids.html")
    println("Entrez le nom du premier super-héros :")
    val hero1 = InputValidator.readValidatedHeroName()
    println("Entrez le nom du deuxième super-héros :")
    val hero2 = InputValidator.readValidatedHeroName()

    kotlinx.coroutines.runBlocking {
        println("url : https://www.superheroapi.com/api.php/a8b7d0b3032c122efe0e1df15c53eb11/search/$hero1")
        println("url : https://www.superheroapi.com/api.php/a8b7d0b3032c122efe0e1df15c53eb11/search/$hero2")
        val intelligence1 = SuperheroService.fetchHeroIntelligence(hero1)
        val intelligence2 = SuperheroService.fetchHeroIntelligence(hero2)
        println(hero1+ " ("+intelligence1+") - "+ hero2 +" ("+intelligence2+")")

        if (intelligence1 == null && intelligence2 == null) {
            println("Impossible de déterminer l'intelligence des deux héros.")
        } else if (intelligence1 == null) {
            println("$hero2 est plus intelligent car aucune donnée pour $hero1.")
        } else if (intelligence2 == null) {
            println("$hero1 est plus intelligent car aucune donnée pour $hero2.")
        } else {
            val moreIntelligentHero = if (intelligence1 > intelligence2) hero1 else hero2
            println("$moreIntelligentHero est le plus intelligent")
        }
    }
}
