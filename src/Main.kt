import kotlin.random.Random

fun main() {
    val choices = listOf("Sang", "Kaghaz", "Gheichi")
    var userScore = 0
    var computerScore = 0

    println("=== Bazi Sang, Kaghaz, Gheichi ===")

    while (true) {
        println("\nEntekhab kon:")
        println("1. Sang")
        println("2. Kaghaz")
        println("3. Gheichi")
        println("0. Khorooj")

        print("Entekhab shoma: ")
        val input = readlnOrNull()?.trim()

        if (input == "0") {
            println("Bazi tamam shod!")
            println("Emtiaz shoma: $userScore")
            println("Emtiaz computer: $computerScore")
            break
        }

        val userChoice = when (input) {
            "1" -> "Sang"
            "2" -> "Kaghaz"
            "3" -> "Gheichi"
            else -> {
                println("Entekhab namotabar ast!")
                continue
            }
        }

        val computerChoice = choices[Random.nextInt(choices.size)]

        println("Entekhab shoma: $userChoice")
        println("Entekhab computer: $computerChoice")

        when {
            userChoice == computerChoice -> {
                println("Mosavi shodid!")
            }

            (userChoice == "Sang" && computerChoice == "Gheichi") ||
            (userChoice == "Kaghaz" && computerChoice == "Sang") ||
            (userChoice == "Gheichi" && computerChoice == "Kaghaz") -> {
                println("Afarin! Shoma barande shodid.")
                userScore++
            }

            else -> {
                println("Computer barande shod!")
                computerScore++
            }
        }

        println("Emtiaz shoma: $userScore | Emtiaz computer: $computerScore")
    }
}