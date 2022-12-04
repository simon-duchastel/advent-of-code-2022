package com.duchastel.simon.adventofcode2022

import kotlinx.coroutines.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.duchastel.simon.adventofcode2022.network.InputType
import com.duchastel.simon.adventofcode2022.network.fetchInput
import com.duchastel.simon.adventofcode2022.problems.Problem
import com.duchastel.simon.adventofcode2022.problems.problems
import com.duchastel.simon.adventofcode2022.ui.AdventButton
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.renderComposable

fun main() {
    var selectedProblem: Problem? by mutableStateOf(null)
    var result: String by mutableStateOf("Not yet computed")

    renderComposable(rootElementId = "root") {
        Div {
            problems.map {
                AdventButton(text = it.name) {
                    selectedProblem = it
                    result = "Not yet computed"
                }
            }
        }

        Text(if (selectedProblem != null) "Selected ${selectedProblem!!.name}" else "No problem selected")

        selectedProblem?.let { problem ->
            Div {
                val inputs = listOf(InputType.Sample) + (1..problem.numInputs).map { InputType.MainInput(it) }
                Div {
                    inputs.map { inputType ->
                        val inputTypeString = when (inputType) {
                            is InputType.Sample -> "Sample"
                            is InputType.MainInput -> "Input ${inputType.num}"
                        }
                        AdventButton(text = "Part 1 ($inputTypeString)") {
                            result = "Loading..."
                            fetchInput(problem, inputType) {
                                result = problem.partOneSolution(it)
                            }
                        }
                    }
                }
                Div {
                    inputs.map { inputType ->
                        val inputTypeString = when (inputType) {
                            is InputType.Sample -> "Sample"
                            is InputType.MainInput -> "Input ${inputType.num}"
                        }
                        AdventButton(text = "Part 2 ($inputTypeString)") {
                            result = "Loading..."
                            fetchInput(problem, inputType) {
                                result = problem.partTwoSolution(it)
                            }
                        }
                    }
                }
                Div {
                    Text("Result: $result")
                }
            }
        }
    }
}
