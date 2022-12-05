package com.duchastel.simon.adventofcode2022.problems

import com.duchastel.simon.adventofcode2022.problem1.Problem1
import com.duchastel.simon.adventofcode2022.problem2.Problem2
import com.duchastel.simon.adventofcode2022.problem3.Problem3
import com.duchastel.simon.adventofcode2022.problem4.Problem4
import com.duchastel.simon.adventofcode2022.problem5.Problem5

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
    Problem(
        name = "Problem Four",
        urlPrefix = "problem4",
        numInputs = 1,
        partOneSolution = { Problem4.solvePartOne(it).toString() },
        partTwoSolution = { Problem4.solvePartTwo(it).toString() },
    ),
    Problem(
        name = "Problem Five",
        urlPrefix = "problem5",
        numInputs = 1,
        partOneSolution = Problem5::solvePartOne,
        partTwoSolution = Problem5::solvePartTwo,
    )
)