package com.duchastel.simon.adventofcode2022.problem4

object Problem4 {
    fun solvePartOne(input: String): Int {
        return input.parseInput()
            .count { (first, second) -> first.fullyContains(second) || second.fullyContains(first) }
    }

    fun solvePartTwo(input: String): Int {
        return input.parseInput()
            .count { (first, second) -> first.doesOverlap(second) }
    }


    ////
    // Helpers
    //////

    private fun String.parseInput(): List<Pair<Assignment, Assignment>> {
        return split("\n")
            .map { it.trim() }
            .map {
                val (first, second) = it.split(",")
                val (firstStart, firstEnd) = first.split("-")
                val (secondStart, secondEnd) = second.split("-")
                Assignment(firstStart.toInt(), firstEnd.toInt()) to Assignment(secondStart.toInt(), secondEnd.toInt())
            }
    }

    private data class Assignment(val start: Int, val end: Int) {
        fun fullyContains(other: Assignment): Boolean {
            return this.start <= other.start && this.end >= other.end
        }

        fun doesOverlap(other: Assignment): Boolean {
            return !(this.end < other.start || this.start > other.end)
        }
    }
}