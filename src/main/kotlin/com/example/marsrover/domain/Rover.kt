package com.example.marsrover.domain

data class Rover(
    val position: Position,
    val direction: Direction
) {
    fun move(commands: String): Rover {
        var currentRover = this
        commands.forEach { command ->
            currentRover = when (command) {
                'f' -> currentRover.copy(position = currentRover.position.move(currentRover.direction, 1))
                'b' -> currentRover.copy(position = currentRover.position.move(currentRover.direction, -1))
                'l' -> currentRover.copy(direction = currentRover.direction.left())
                'r' -> currentRover.copy(direction = currentRover.direction.right())
                else -> currentRover // Ignore unknown commands for now
            }
        }
        return currentRover
    }
}
