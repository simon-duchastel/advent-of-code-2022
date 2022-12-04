package problem3

object Problem3 {
    fun solvePartOne(input: String): Int {
        return input.split("\n")
            .map { it.trim() }
            .map { it.findMixedUpItem() }
            .sumOf { it.calculatePriority() }
    }

    fun solvePartTwo(input: String): Int {
        return input.split("\n")
            .map { it.trim() }
            .let { allRuckSacks ->
                (allRuckSacks.indices step 3).map { i ->
                    findCommonItem(Triple(allRuckSacks[i], allRuckSacks[i + 1], allRuckSacks[i + 2]))
                }
            }
            .sumOf { it.calculatePriority() }
    }


    ////
    // Helpers
    //////

    private fun Char.calculatePriority(): Int {
        return if (this > 90.toChar()) {
            this.code - 96 // this is a lowercase letter
        } else {
            this.code - 38 // this is an uppercase letter
        }
    }


    ////
    // Helpers Part One
    //////

    private fun String.findMixedUpItem(): Char {
        val first = subSequence(0, length / 2)
        val second = subSequence(length / 2, length)

        first.forEach { target ->
            if (second.contains(target, ignoreCase = false)) {
                return target
            }
        }

        return first.first()
    }


    ////
    // Helpers Part Two
    //////

    private fun findCommonItem(groupOfRucksacks: Triple<String, String, String>): Char {
        val (first, second, third) = groupOfRucksacks

        first.forEach { target ->
            if (second.contains(target, ignoreCase = false) && third.contains(target, ignoreCase = false)) {
                return target
            }
        }

        return first.first()
    }
}