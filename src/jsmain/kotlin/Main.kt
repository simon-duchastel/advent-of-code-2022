package com.duchastel.simon.adventofcode2022

import kotlinx.browser.window
import kotlinx.coroutines.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.duchastel.simon.adventofcode2022.network.InputType
import com.duchastel.simon.adventofcode2022.network.fetchInput
import com.duchastel.simon.adventofcode2022.problem1.Problem1
import com.duchastel.simon.adventofcode2022.problem2.Problem2
import com.duchastel.simon.adventofcode2022.problem3.Problem3
import com.duchastel.simon.adventofcode2022.ui.AdventButton
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.renderComposable

fun main() {
    var selectedProblem: Problem? by mutableStateOf(null)
    var result: String by mutableStateOf("Not yet computed")

    renderComposable(rootElementId = "root") {
//        Button(attrs = { onClick { document.bgColor = "red" } }) { Text("Make Red") }
//        Button(attrs = { onClick { document.bgColor = "blue" } }) { Text("Make Blue") }

        AdventButton(text = "Problem 1") {
            selectedProblem = Problem.ONE
            result = "Not yet computed"
        }
        AdventButton(text = "Problem 2") {
            selectedProblem = Problem.TWO
            result = "Not yet computed"
        }
        AdventButton(text = "Problem 3") {
            selectedProblem = Problem.THREE
            result = "Not yet computed"
        }

        Text(if (selectedProblem != null) "Selected problem #${selectedProblem}" else "No problem selected")
        when (selectedProblem) {
            Problem.ONE -> {
                AdventButton(text = "Part 1 (Sample)") {
                    result = "Loading..."
                    fetchInput(Problem.ONE, InputType.Sample) {
                        result = Problem1.solvePartOne(it).toString()
                    }
                }
                AdventButton(text = "Part 1 (Actual)") {
                    result = "Loading..."
                    fetchInput(Problem.ONE, InputType.MainInput(1)) {
                        result = Problem1.solvePartOne(it).toString()
                    }
                }
                AdventButton(text = "Part 2 (Sample)") {
                    result = "Loading..."
                    fetchInput(Problem.ONE, InputType.Sample) {
                        result = Problem1.solvePartTwo(it).toString()
                    }
                }
                AdventButton(text = "Part 2 (Actual)") {
                    result = "Loading..."
                    fetchInput(Problem.ONE, InputType.MainInput(1)) {
                        result = Problem1.solvePartTwo(it).toString()
                    }
                }
                Text("Result: $result")
            }
            Problem.TWO -> {
                AdventButton(text = "Part 1 (Sample)") {
                    result = "Loading..."
                    fetchInput(Problem.TWO, InputType.Sample) {
                        result = Problem2.solvePartOne(it).toString()
                    }
                }
                AdventButton(text = "Part 1 (Actual)") {
                    result = "Loading..."
                    fetchInput(Problem.TWO, InputType.MainInput(1)) {
                        result = Problem2.solvePartOne(it).toString()
                    }
                }
                AdventButton(text = "Part 2 (Sample)") {
                    result = "Loading..."
                    fetchInput(Problem.TWO, InputType.Sample) {
                        result = Problem2.solvePartTwo(it).toString()
                    }
                }
                AdventButton(text = "Part 2 (Actual)") {
                    result = "Loading..."
                    fetchInput(Problem.TWO, InputType.MainInput(1)) {
                        result = Problem2.solvePartTwo(it).toString()
                    }
                }
                Text("Result: $result")
            }

            Problem.THREE -> {
                AdventButton(text = "Part 1 (Sample)") {
                    result = "Loading..."
                    fetchInput(Problem.THREE, InputType.Sample) {
                        result = Problem3.solvePartOne(it).toString()
                    }
                }
                AdventButton(text = "Part 1 (Actual)") {
                    result = "Loading..."
                    fetchInput(Problem.THREE, InputType.MainInput(1)) {
                        result = Problem3.solvePartOne(it).toString()
                    }
                }
                AdventButton(text = "Part 2 (Sample)") {
                    result = "Loading..."
                    fetchInput(Problem.THREE, InputType.Sample) {
                        result = Problem3.solvePartTwo(it).toString()
                    }
                }
                AdventButton(text = "Part 2 (Actual)") {
                    result = "Loading..."
                    fetchInput(Problem.THREE, InputType.MainInput(1)) {
                        result = Problem3.solvePartTwo(it).toString()
                    }
                }
                Text("Result: $result")
            }
            null -> { /* Don't render anything */ }
        }
    }
}

enum class Problem {
    ONE,
    TWO,
    THREE,
}