package com.example.marsrover.infrastructure.adapters

import com.example.marsrover.application.ports.GridRepositoryPort
import com.example.marsrover.domain.Position
import org.springframework.stereotype.Component

@Component
class InMemoryGridRepository : GridRepositoryPort {
    override fun hasObstacle(position: Position): Boolean {
        return false // No obstacles for now
    }

    override fun getGridSize(): Pair<Int, Int> {
        return Pair(10, 10) // Default 10x10 grid
    }
}
