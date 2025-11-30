package com.example.marsrover.domain

data class Rover(
    val position: Position,
    val direction: Direction
) {
    fun move(commands: String, grid: Grid, obstacleChecker: (Position) -> Boolean): MoveResult {
        var currentRover = this
        
        for (command in commands) {
            val nextRover = when (command) {
                'f' -> {
                    val newPosition = currentRover.position.move(currentRover.direction, 1)
                    val (wrappedPosition, wrappedDirection) = grid.wrap(newPosition, currentRover.direction)
                    
                    // Check for obstacle at target position
                    if (grid.hasObstacle(wrappedPosition, obstacleChecker)) {
                        return MoveResult(currentRover, true, wrappedPosition)
                    }
                    
                    currentRover.copy(position = wrappedPosition, direction = wrappedDirection)
                }
                'b' -> {
                    val newPosition = currentRover.position.move(currentRover.direction, -1)
                    val (wrappedPosition, wrappedDirection) = grid.wrap(newPosition, currentRover.direction)
                    
                    // Check for obstacle at target position
                    if (grid.hasObstacle(wrappedPosition, obstacleChecker)) {
                        return MoveResult(currentRover, true, wrappedPosition)
                    }
                    
                    currentRover.copy(position = wrappedPosition, direction = wrappedDirection)
                }
                'l' -> currentRover.copy(direction = currentRover.direction.left())
                'r' -> currentRover.copy(direction = currentRover.direction.right())
                else -> currentRover // Ignore unknown commands
            }
            currentRover = nextRover
        }
        
        return MoveResult(currentRover, false, null)
    }
}
