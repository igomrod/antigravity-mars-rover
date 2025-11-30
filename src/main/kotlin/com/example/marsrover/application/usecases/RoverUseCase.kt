package com.example.marsrover.application.usecases

import com.example.marsrover.application.ports.GridRepositoryPort
import com.example.marsrover.application.ports.RoverCommandPort
import com.example.marsrover.domain.Direction
import com.example.marsrover.domain.Position
import com.example.marsrover.domain.Rover
import org.springframework.stereotype.Service

@Service
class RoverUseCase(private val gridRepository: GridRepositoryPort) : RoverCommandPort {

    private var rover: Rover? = null

    override fun initialize(x: Int, y: Int, direction: Direction): Rover {
        // In a real app, we might validate against grid size here
        rover = Rover(Position(x, y), direction)
        return rover!!
    }

    override fun execute(commands: String): Rover {
        // To be implemented in US-2
        return rover ?: throw IllegalStateException("Rover not initialized")
    }
}
