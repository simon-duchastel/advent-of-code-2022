package com.duchastel.simon.adventofcode2022.problem5

object Problem5 {
    fun solvePartOne(input: String): String {
        val parsedInput = input.parseInput()
        val finalMap = parsedInput.moveCrates(reverseWhenMoved = true)

        return finalMap.map { it.first() }.concatAsString()
    }

    fun solvePartTwo(input: String): String {
        val parsedInput = input.parseInput()
        val finalMap = parsedInput.moveCrates(reverseWhenMoved = false)

        return finalMap.map { it.first() }.concatAsString()
    }


    ////
    // Helpers
    //////

    private data class Input(
        val crateMap: List<List<Char>>,
        val actions: List<Action>,
    )

    private data class Action(
        val startStack: Int,
        val endStack: Int,
        val count: Int,
    )

    /**
     * [reverseWhenMoved] When true, the crates are moved one-by-one across the stacks (meaning
     * they come out reversed). When false, they're moved all at once (meaning they don't come
     * out reversed).
     */
    private fun Input.moveCrates(reverseWhenMoved: Boolean = true): List<List<Char>> {
        return actions.fold(crateMap) { map, action ->
            val startStack = map[action.startStack]
            val endStack = map[action.endStack]

            // remove the crates from the starting stack and add them (reversed, since they move
            // one-by-one) in the end stack
            val newStartStack = startStack.drop(action.count)
            val subStack = startStack.subList(0, action.count)
            val newEndStack = if (reverseWhenMoved) {
                subStack.reversed() // if we're moving one-by-one, reverse the stacks
            } else {
                subStack
            } + endStack

            // Replace the stacks as appropriate
            map.replaceAt(action.startStack, newStartStack)
                .replaceAt(action.endStack, newEndStack)
        }
    }

    private fun String.parseInput(): Input {
        // Parses the input in the following format:
        //    [C]
        //[Z] [M] [P]
        // 1   2   3
        //
        //move 1 from 2 to 1
        //move 3 from 1 to 3
        // ...

        return this.split("\n")
            .fold(Input(emptyList(), emptyList())) { input, nextLine ->
                val firstNonBlankChar = nextLine.trim().firstOrNull()

                when {
                    nextLine.isBlank() -> input // ignore empty lines
                    firstNonBlankChar == '1' -> input // ignore the line labeling the stacks
                    firstNonBlankChar == '[' -> {
                        // Each group of 3 characters (with each group separated by a space)
                        // is either a crate (ex. "[4]") or spaces (meaning no crate).
                        // Parse these out and add them to the crate stack being accumulated.
                        var nextThree = nextLine.take(3)
                        var rest = nextLine.drop(3)
                        val crates = mutableListOf<Char?>()
                        while (nextThree.count() == 3) {
                            val match = Regex("\\[([A-Z])\\]").matchEntire(nextThree)
                            if (match != null) {
                                crates += match.groupValues[1].first()
                            } else {
                                crates += null
                            }

                            rest.drop(1).let { restDropped ->
                                nextThree = restDropped.take(3)
                                rest = restDropped.drop(3)
                            }
                        }

                        crates.foldIndexed(input) { index, newInput, crate ->
                            // Fill up the crate map with empty lists of crates, as needed
                            if (crate != null) {
                                val crateMap = if (newInput.crateMap.size <= index) {
                                    newInput.crateMap + (newInput.crateMap.size..index).map { emptyList() }
                                } else {
                                    newInput.crateMap
                                }

                                // Append the crate to the stack, and replace this new stack in the crate map
                                val newStack = crateMap[index] + crate
                                newInput.copy(
                                    crateMap = crateMap.replaceAt(index, newStack)
                                )
                            } else {
                                newInput
                            }
                        }
                    }
                    nextLine.startsWith("move") -> {
                        // Take each "move" command and add it to the accumulated Actions to execute
                        val match = Regex("move ([0-9]+) from ([0-9]+) to ([0-9]+)").matchEntire(nextLine.trim())
                        if (match != null) {
                            // normalize the start and end stack indices, since they're 1-indexed and we operate
                            // on 0-indexed
                            val (rawCount, rawStart, rawEnd) = match.destructured
                            val count = rawCount.toInt()
                            val start = rawStart.toInt() - 1
                            val end = rawEnd.toInt() - 1
                            input.copy(actions = input.actions + Action(start, end, count))
                        } else {
                            input // if our Regex is wrong ignore this line
                        }
                    }
                    else -> input // ignore anything we don't understand
                }
            }
    }

    private fun List<Char>.concatAsString(): String {
        return this.fold("") { acc, cur -> acc + cur }
    }

    private fun <T> List<T>.replaceAt(index: Int, replaceWith: T): List<T> {
        // Use mapIndexed to replace an element with a new element
        return mapIndexed { i, original ->
            if (i == index) {
                replaceWith
            } else {
                original
            }
        }
    }
}