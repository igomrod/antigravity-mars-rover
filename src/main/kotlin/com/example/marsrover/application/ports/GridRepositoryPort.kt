package com.example.marsrover.application.ports

import com.example.marsrover.domain.Position

interface GridRepositoryPort {
    fun hasObstacle(position: Position): Boolean
    fun getGridSize(): Pair<Int, Int>
}
