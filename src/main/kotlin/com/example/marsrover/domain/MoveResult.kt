package com.example.marsrover.domain

data class MoveResult(
    val rover: Rover,
    val obstacleEncountered: Boolean = false,
    val obstaclePosition: Position? = null
)
