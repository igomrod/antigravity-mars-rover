package com.example.marsrover.domain

enum class Direction {
    NORTH, SOUTH, EAST, WEST;

    fun left(): Direction = when (this) {
        NORTH -> WEST
        WEST -> SOUTH
        SOUTH -> EAST
        EAST -> NORTH
    }

    fun right(): Direction = when (this) {
        NORTH -> EAST
        EAST -> SOUTH
        SOUTH -> WEST
        WEST -> NORTH
    }
}
