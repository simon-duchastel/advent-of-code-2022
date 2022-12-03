package com.duchastel.simon.adventofcode2022.network

import com.duchastel.simon.adventofcode2022.Problem
import com.duchastel.simon.adventofcode2022.network.InputType.MainInput
import com.duchastel.simon.adventofcode2022.network.InputType.Sample
import kotlinx.browser.window

fun fetchInput(problem: Problem, inputType: InputType, onComplete: (result: String) -> Unit) {
    val problemUrl = when(problem) {
        Problem.ONE -> "problem1"
        Problem.TWO -> "problem2"
    }
    val inputTypeUrl = when (inputType) {
        is Sample -> "sample.txt"
        is MainInput -> "input${inputType.num}.txt"
    }

    window.fetch("/${problemUrl}/${inputTypeUrl}").then { response ->
        response.text().then {
            onComplete(it)
        }
    }
}

sealed class InputType {
    object Sample: InputType()
    data class MainInput(val num: Int): InputType()
}