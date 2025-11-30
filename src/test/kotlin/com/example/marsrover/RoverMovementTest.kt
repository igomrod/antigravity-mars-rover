package com.example.marsrover

import com.example.marsrover.domain.Direction
import com.example.marsrover.domain.Grid
import com.example.marsrover.domain.Position
import com.example.marsrover.domain.Rover
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RoverMovementTest {

    private val grid = Grid(10, 10)
    private val noObstacles: (Position) -> Boolean = { false }

    @Test
    fun `should move forward North`() {
        val rover = Rover(Position(0, 0), Direction.NORTH)
        val result = rover.move("f", grid, noObstacles)
        assertEquals(Position(0, 1), result.rover.position)
        assertEquals(Direction.NORTH, result.rover.direction)
    }

    @Test
    fun `should move backward North`() {
        val rover = Rover(Position(0, 1), Direction.NORTH)
        val result = rover.move("b", grid, noObstacles)
        assertEquals(Position(0, 0), result.rover.position)
        assertEquals(Direction.NORTH, result.rover.direction)
    }

    @Test
    fun `should move forward East`() {
        val rover = Rover(Position(0, 0), Direction.EAST)
        val result = rover.move("f", grid, noObstacles)
        assertEquals(Position(1, 0), result.rover.position)
        assertEquals(Direction.EAST, result.rover.direction)
    }

    @Test
    fun `should move backward East`() {
        val rover = Rover(Position(1, 0), Direction.EAST)
        val result = rover.move("b", grid, noObstacles)
        assertEquals(Position(0, 0), result.rover.position)
        assertEquals(Direction.EAST, result.rover.direction)
    }
    
    @Test
    fun `should move forward South`() {
        val rover = Rover(Position(0, 1), Direction.SOUTH)
        val result = rover.move("f", grid, noObstacles)
        assertEquals(Position(0, 0), result.rover.position)
        assertEquals(Direction.SOUTH, result.rover.direction)
    }

    @Test
    fun `should move backward South`() {
        val rover = Rover(Position(0, 0), Direction.SOUTH)
        val result = rover.move("b", grid, noObstacles)
        assertEquals(Position(0, 1), result.rover.position)
        assertEquals(Direction.SOUTH, result.rover.direction)
    }

    @Test
    fun `should move forward West`() {
        val rover = Rover(Position(1, 0), Direction.WEST)
        val result = rover.move("f", grid, noObstacles)
        assertEquals(Position(0, 0), result.rover.position)
        assertEquals(Direction.WEST, result.rover.direction)
    }

    @Test
    fun `should move backward West`() {
        val rover = Rover(Position(0, 0), Direction.WEST)
        val result = rover.move("b", grid, noObstacles)
        assertEquals(Position(1, 0), result.rover.position)
        assertEquals(Direction.WEST, result.rover.direction)
    }

    @Test
    fun `should execute multiple commands`() {
        val rover = Rover(Position(0, 0), Direction.NORTH)
        val result = rover.move("ff", grid, noObstacles)
        assertEquals(Position(0, 2), result.rover.position)
    }
}
