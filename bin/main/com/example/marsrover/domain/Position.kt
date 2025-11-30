package com.example.marsrover.domain

data class Position(val x: Int, val y: Int) {
    fun move(direction: Direction, step: Int): Position {
        return when (direction) {
            Direction.NORTH -> copy(y = y + step)
            Direction.SOUTH -> copy(y = y - step)
            Direction.EAST -> copy(x = x + step)
            Direction.WEST -> copy(x = x - step)
        }
    }
}
