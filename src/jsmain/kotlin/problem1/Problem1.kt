package com.duchastel.simon.adventofcode2022.problem1

object Problem1 {
    fun solvePartOne(input: String): Int {
        return input.parseGroups().maxOfOrNull { it.sumGroup() } ?: -1
    }

    fun solvePartTwo(input: String): Int {
        return input.parseGroups().map { it.sumGroup() }.sortedDescending().take(3).sum()
    }

    ////
    // Helpers
    //////

    private fun String.parseGroups(): List<List<Int>> {
        val finalGroups = mutableListOf<List<Int>>(emptyList())

        split("\n")
            .forEach {
                if (it.isNotBlank()) {
                    val last = finalGroups[finalGroups.lastIndex]
                    finalGroups[finalGroups.lastIndex] = last + it.trim().toInt()
                } else {
                    finalGroups.add(emptyList())
                }
            }

        return finalGroups
    }

    private fun List<Int>.sumGroup(): Int {
        return reduce { acc, i -> acc + i }
    }
}