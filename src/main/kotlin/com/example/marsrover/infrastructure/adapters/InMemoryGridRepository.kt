package com.example.marsrover.infrastructure.adapters

import com.example.marsrover.application.ports.GridRepositoryPort
import com.example.marsrover.domain.Position
import org.springframework.stereotype.Component

@Component
class InMemoryGridRepository : GridRepositoryPort {
    
    private val obstacles = mutableSetOf<Position>()
    
    fun addObstacle(position: Position) {
        obstacles.add(position)
    }
    
    override fun hasObstacle(position: Position): Boolean {
        return obstacles.contains(position)
    }

    override fun getGridSize(): Pair<Int, Int> {
        return Pair(10, 10) // Default 10x10 grid
    }
}
