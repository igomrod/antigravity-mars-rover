package com.example.marsrover.application.usecases

import com.example.marsrover.application.ports.GridRepositoryPort
import com.example.marsrover.application.ports.RoverCommandPort
import com.example.marsrover.domain.Direction
import com.example.marsrover.domain.Grid
import com.example.marsrover.domain.MoveResult
import com.example.marsrover.domain.Position
import com.example.marsrover.domain.Rover
import org.springframework.stereotype.Service

@Service
class RoverUseCase(private val gridRepository: GridRepositoryPort) : RoverCommandPort {

    private var rover: Rover? = null
    private lateinit var grid: Grid

    override fun initialize(x: Int, y: Int, direction: Direction): Rover {
        val gridSize = gridRepository.getGridSize()
        grid = Grid(gridSize.first, gridSize.second)
        rover = Rover(Position(x, y), direction)
        return rover!!
    }

    override fun execute(commands: String): MoveResult {
        if (rover == null) {
            throw IllegalStateException("Rover not initialized")
        }
        
        val result = rover!!.move(commands, grid) { position ->
            gridRepository.hasObstacle(position)
        }
        
        rover = result.rover
        return result
    }
}
