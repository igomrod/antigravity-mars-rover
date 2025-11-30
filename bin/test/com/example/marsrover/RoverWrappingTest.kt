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
        val result = useCase.execute("f")
        assertEquals(Position(0, 5), result.rover.position)
        assertEquals(Direction.EAST, result.rover.direction)
    }

    @Test
    fun `should wrap horizontally West`() {
        useCase.initialize(0, 5, Direction.WEST)
        val result = useCase.execute("f")
        assertEquals(Position(9, 5), result.rover.position)
        assertEquals(Direction.WEST, result.rover.direction)
    }

    @Test
    fun `should wrap vertically North crossing pole`() {
        useCase.initialize(0, 9, Direction.NORTH)
        val result = useCase.execute("f")
        assertEquals(Position(5, 9), result.rover.position)
        assertEquals(Direction.SOUTH, result.rover.direction)
    }

    @Test
    fun `should wrap vertically South crossing pole`() {
        useCase.initialize(5, 0, Direction.SOUTH)
        val result = useCase.execute("f")
        assertEquals(Position(0, 0), result.rover.position)
        assertEquals(Direction.NORTH, result.rover.direction)
    }
}
