package utils

object InputValidator {
    fun readValidatedHeroName(): String {
        while (true) {
            val input = readLine()?.trim()
            if (input != null && input.length >= 4) {
                return input
            }
            println("Le nom doit contenir au moins 4 caractères non espacés. Veuillez réessayer :")
        }
    }
}
