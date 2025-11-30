package com.example.marsrover

import com.example.marsrover.application.usecases.RoverUseCase
import com.example.marsrover.domain.Direction
import com.example.marsrover.domain.Position
import com.example.marsrover.infrastructure.adapters.InMemoryGridRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RoverObstacleTest {

    private lateinit var useCase: RoverUseCase
    private lateinit var gridRepository: InMemoryGridRepository

    @BeforeEach
    fun setup() {
        gridRepository = InMemoryGridRepository()
        useCase = RoverUseCase(gridRepository)
    }

    @Test
    fun `should stop at obstacle when moving forward`() {
        gridRepository.addObstacle(Position(0, 2))
        useCase.initialize(0, 1, Direction.NORTH)
        
        val result = useCase.execute("f")
        
        assertEquals(Position(0, 1), result.rover.position)
        assertEquals(Direction.NORTH, result.rover.direction)
        assertTrue(result.obstacleEncountered)
        assertEquals(Position(0, 2), result.obstaclePosition)
    }

    @Test
    fun `should stop command sequence at first obstacle`() {
        gridRepository.addObstacle(Position(0, 3))
        useCase.initialize(0, 1, Direction.NORTH)
        
        val result = useCase.execute("fff")
        
        assertEquals(Position(0, 2), result.rover.position)
        assertEquals(Direction.NORTH, result.rover.direction)
        assertTrue(result.obstacleEncountered)
        assertEquals(Position(0, 3), result.obstaclePosition)
    }

    @Test
    fun `should complete movement when no obstacles`() {
        useCase.initialize(0, 0, Direction.NORTH)
        
        val result = useCase.execute("ff")
        
        assertEquals(Position(0, 2), result.rover.position)
        assertEquals(Direction.NORTH, result.rover.direction)
        assertFalse(result.obstacleEncountered)
        assertNull(result.obstaclePosition)
    }

    @Test
    fun `should stop at obstacle when moving backward`() {
        gridRepository.addObstacle(Position(0, 0))
        useCase.initialize(0, 1, Direction.NORTH)
        
        val result = useCase.execute("b")
        
        assertEquals(Position(0, 1), result.rover.position)
        assertTrue(result.obstacleEncountered)
    }
}
