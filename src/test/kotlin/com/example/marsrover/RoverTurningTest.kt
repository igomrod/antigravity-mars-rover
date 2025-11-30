package com.example.marsrover

import com.example.marsrover.domain.Direction
import com.example.marsrover.domain.Grid
import com.example.marsrover.domain.Position
import com.example.marsrover.domain.Rover
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RoverTurningTest {

    private val grid = Grid(10, 10)
    private val noObstacles: (Position) -> Boolean = { false }

    @Test
    fun `should turn left from North`() {
        val rover = Rover(Position(0, 0), Direction.NORTH)
        val result = rover.move("l", grid, noObstacles)
        assertEquals(Position(0, 0), result.rover.position)
        assertEquals(Direction.WEST, result.rover.direction)
    }

    @Test
    fun `should turn right from North`() {
        val rover = Rover(Position(0, 0), Direction.NORTH)
        val result = rover.move("r", grid, noObstacles)
        assertEquals(Position(0, 0), result.rover.position)
        assertEquals(Direction.EAST, result.rover.direction)
    }

    @Test
    fun `should turn left from West`() {
        val rover = Rover(Position(0, 0), Direction.WEST)
        val result = rover.move("l", grid, noObstacles)
        assertEquals(Direction.SOUTH, result.rover.direction)
    }

    @Test
    fun `should turn right from West`() {
        val rover = Rover(Position(0, 0), Direction.WEST)
        val result = rover.move("r", grid, noObstacles)
        assertEquals(Direction.NORTH, result.rover.direction)
    }

    @Test
    fun `should turn left from South`() {
        val rover = Rover(Position(0, 0), Direction.SOUTH)
        val result = rover.move("l", grid, noObstacles)
        assertEquals(Direction.EAST, result.rover.direction)
    }

    @Test
    fun `should turn right from South`() {
        val rover = Rover(Position(0, 0), Direction.SOUTH)
        val result = rover.move("r", grid, noObstacles)
        assertEquals(Direction.WEST, result.rover.direction)
    }

    @Test
    fun `should turn left from East`() {
        val rover = Rover(Position(0, 0), Direction.EAST)
        val result = rover.move("l", grid, noObstacles)
        assertEquals(Direction.NORTH, result.rover.direction)
    }

    @Test
    fun `should turn right from East`() {
        val rover = Rover(Position(0, 0), Direction.EAST)
        val result = rover.move("r", grid, noObstacles)
        assertEquals(Direction.SOUTH, result.rover.direction)
    }

    @Test
    fun `should execute multiple turns`() {
        val rover = Rover(Position(0, 0), Direction.NORTH)
        val result = rover.move("rr", grid, noObstacles)
        assertEquals(Direction.SOUTH, result.rover.direction)
    }
}
