package com.duchastel.simon.adventofcode2022.ui

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.renderComposable

@Composable
fun AdventButton(text: String, onClickFn: () -> Unit) {
    Button(attrs = { onClick { onClickFn() } }) {
        Text(text)
    }
}