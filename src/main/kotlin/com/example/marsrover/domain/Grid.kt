package com.example.marsrover.domain

data class Grid(val width: Int, val height: Int) {
    
    fun wrap(position: Position, direction: Direction): Pair<Position, Direction> {
        var newX = position.x
        var newY = position.y
        var newDirection = direction

        // Horizontal wrapping (cylinder-like)
        if (newX < 0) {
            newX = width - 1
        } else if (newX >= width) {
            newX = 0
        }

        // Vertical wrapping (spherical - crossing poles)
        if (newY < 0) {
            newY = 0
            newX = (newX + width / 2) % width
            newDirection = direction.opposite()
        } else if (newY >= height) {
            newY = height - 1
            newX = (newX + width / 2) % width
            newDirection = direction.opposite()
        }

        return Pair(Position(newX, newY), newDirection)
    }
}
