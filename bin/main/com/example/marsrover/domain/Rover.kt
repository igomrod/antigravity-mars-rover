package com.example.marsrover.domain

data class Rover(
    val position: Position,
    val direction: Direction
) {
    fun move(commands: String, grid: Grid): Rover {
        var currentRover = this
        commands.forEach { command ->
            currentRover = when (command) {
                'f' -> {
                    val newPosition = currentRover.position.move(currentRover.direction, 1)
                    val (wrappedPosition, wrappedDirection) = grid.wrap(newPosition, currentRover.direction)
                    currentRover.copy(position = wrappedPosition, direction = wrappedDirection)
                }
                'b' -> {
                    val newPosition = currentRover.position.move(currentRover.direction, -1)
                    val (wrappedPosition, wrappedDirection) = grid.wrap(newPosition, currentRover.direction)
                    currentRover.copy(position = wrappedPosition, direction = wrappedDirection)
                }
                'l' -> currentRover.copy(direction = currentRover.direction.left())
                'r' -> currentRover.copy(direction = currentRover.direction.right())
                else -> currentRover // Ignore unknown commands for now
            }
        }
        return currentRover
    }
}
