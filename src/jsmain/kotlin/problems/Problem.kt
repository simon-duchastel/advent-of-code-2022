package com.duchastel.simon.adventofcode2022.problems

import problem1.Problem1
import problem2.Problem2
import problem3.Problem3

data class Problem(
    val name: String,
    val urlPrefix: String,
    val numInputs: Int,
    val partOneSolution: (input: String) -> String,
    val partTwoSolution: (input: String) -> String,
)

val problems = listOf(
    Problem(
        name = "Problem One",
        urlPrefix = "problem1",
        numInputs = 1,
        partOneSolution = { Problem1.solvePartOne(it).toString() },
        partTwoSolution = { Problem1.solvePartTwo(it).toString() },
    ),
    Problem(
        name = "Problem Two",
        urlPrefix = "problem2",
        numInputs = 1,
        partOneSolution = { Problem2.solvePartOne(it).toString() },
        partTwoSolution = { Problem2.solvePartTwo(it).toString() },
    ),
    Problem(
        name = "Problem Three",
        urlPrefix = "problem3",
        numInputs = 1,
        partOneSolution = { Problem3.solvePartOne(it).toString() },
        partTwoSolution = { Problem3.solvePartTwo(it).toString() },
    ),
)