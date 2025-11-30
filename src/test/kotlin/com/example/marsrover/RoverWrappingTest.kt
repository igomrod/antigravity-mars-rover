package com.example.marsrover

import com.example.marsrover.application.usecases.RoverUseCase
import com.example.marsrover.domain.Direction
import com.example.marsrover.domain.Position
import com.example.marsrover.infrastructure.adapters.InMemoryGridRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RoverWrappingTest {

    private lateinit var useCase: RoverUseCase
    private lateinit var gridRepository: InMemoryGridRepository

    @BeforeEach
    fun setup() {
        gridRepository = InMemoryGridRepository()
        useCase = RoverUseCase(gridRepository)
    }

    @Test
    fun `should wrap horizontally East`() {
        useCase.initialize(9, 5, Direction.EAST)
        val rover = useCase.execute("f")
        assertEquals(Position(0, 5), rover.position)
        assertEquals(Direction.EAST, rover.direction)
    }

    @Test
    fun `should wrap horizontally West`() {
        useCase.initialize(0, 5, Direction.WEST)
        val rover = useCase.execute("f")
        assertEquals(Position(9, 5), rover.position)
        assertEquals(Direction.WEST, rover.direction)
    }

    @Test
    fun `should wrap vertically North crossing pole`() {
        useCase.initialize(0, 9, Direction.NORTH)
        val rover = useCase.execute("f")
        assertEquals(Position(5, 9), rover.position)
        assertEquals(Direction.SOUTH, rover.direction)
    }

    @Test
    fun `should wrap vertically South crossing pole`() {
        useCase.initialize(5, 0, Direction.SOUTH)
        val rover = useCase.execute("f")
        assertEquals(Position(0, 0), rover.position)
        assertEquals(Direction.NORTH, rover.direction)
    }
}
