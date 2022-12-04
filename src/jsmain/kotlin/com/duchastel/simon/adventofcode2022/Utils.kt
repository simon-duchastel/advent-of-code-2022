package com.duchastel.simon.adventofcode2022

fun logToConsole(tag: String = "", value: String) {
    js("console.log(tag + ' - ' + value)")
}