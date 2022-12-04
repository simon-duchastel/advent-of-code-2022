package problem2

object Problem2 {
    fun solvePartOne(input: String): Int {
        return input.split("\n")
            .map { it.trim() }
            .sumOf {
                val (opponent, myself) = it.split(" ")
                calculatePartOneScore(myself.first(), opponent.first())
            }
    }

    fun solvePartTwo(input: String): Int {
        return input.split("\n")
            .map { it.trim() }
            .sumOf {
                val (opponent, expectedOutcome) = it.split(" ")
                calculatePartTwoScore(opponent.first(), expectedOutcome.first())
            }
    }


    ////
    // Helpers
    //////

    enum class Outcome {
        WIN,
        LOSS,
        DRAW
    }


    ////
    //Helpers Part 1
    //////

    // Key
    // ======
    // A/X = Rock
    // B/Y = Paper
    // C/Z = Scissors

    private const val CHOICE_NORMALIZATION = 23 // 24 characters between A and X (rock for opponent and myself)

    private fun calculateOutcome(choice: Char, opponentChoice: Char): Outcome {
        return when {
            (choice - CHOICE_NORMALIZATION) == opponentChoice -> Outcome.DRAW
            choice == 'X' && opponentChoice == 'C' -> Outcome.WIN
            choice == 'Y' && opponentChoice == 'A' -> Outcome.WIN
            choice == 'Z' && opponentChoice == 'B' -> Outcome.WIN
            else -> Outcome.LOSS
        }
    }

    private fun calculatePartOneScore(choice: Char, opponentChoice: Char): Int {
        val baseScore = when (choice) {
            'X' -> 1
            'Y' -> 2
            'Z' -> 3
            else -> 0
        }
        val winScore = when (calculateOutcome(choice, opponentChoice)) {
            Outcome.LOSS -> 0
            Outcome.DRAW -> 3
            Outcome.WIN -> 6
        }
        return baseScore + winScore
    }

    ////
    // Helpers Part 2
    //////

    // Key
    // ======
    // A = Rock
    // B = Paper
    // C = Scissors
    //
    // X = Lose
    // Y = Draw
    // Z = Win

    private fun calculateScoreFromOutcome(expectedOutcome: Char): Int {
        return when (expectedOutcome) {
            'X' -> 0
            'Y' -> 3
            'Z' -> 6
            else -> 0
        }
    }

    private fun getExpectedChoice(opponentChoice: Char, expectedOutcome: Char): Char {
        val expectedChoice: Char = when (expectedOutcome) {
            'X' -> opponentChoice - 1
            'Y' -> opponentChoice
            'Z' -> opponentChoice + 1
            else -> 0.toChar()
        }
        return when {
            expectedChoice < 65.toChar() -> 'C'
            expectedChoice > 67.toChar() -> 'A'
            else -> expectedChoice
        }
    }

    private fun calculatePartTwoScore(opponentChoice: Char, expectedOutcome: Char): Int {
        val winScore = calculateScoreFromOutcome(expectedOutcome)
        val choiceScore = when (getExpectedChoice(opponentChoice, expectedOutcome)) {
            'A' -> 1
            'B' -> 2
            'C' -> 3
            else -> 0
        }
        return choiceScore + winScore
    }
}