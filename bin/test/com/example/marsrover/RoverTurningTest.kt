package com.example.marsrover

import com.example.marsrover.domain.Direction
import com.example.marsrover.domain.Grid
import com.example.marsrover.domain.Position
import com.example.marsrover.domain.Rover
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RoverTurningTest {

    private val grid = Grid(10, 10)

    @Test
    fun `should turn left from North`() {
        val rover = Rover(Position(0, 0), Direction.NORTH)
        val updatedRover = rover.move("l", grid)
        assertEquals(Position(0, 0), updatedRover.position)
        assertEquals(Direction.WEST, updatedRover.direction)
    }

    @Test
    fun `should turn right from North`() {
        val rover = Rover(Position(0, 0), Direction.NORTH)
        val updatedRover = rover.move("r", grid)
        assertEquals(Position(0, 0), updatedRover.position)
        assertEquals(Direction.EAST, updatedRover.direction)
    }

    @Test
    fun `should turn left from West`() {
        val rover = Rover(Position(0, 0), Direction.WEST)
        val updatedRover = rover.move("l", grid)
        assertEquals(Direction.SOUTH, updatedRover.direction)
    }

    @Test
    fun `should turn right from West`() {
        val rover = Rover(Position(0, 0), Direction.WEST)
        val updatedRover = rover.move("r", grid)
        assertEquals(Direction.NORTH, updatedRover.direction)
    }

    @Test
    fun `should turn left from South`() {
        val rover = Rover(Position(0, 0), Direction.SOUTH)
        val updatedRover = rover.move("l", grid)
        assertEquals(Direction.EAST, updatedRover.direction)
    }

    @Test
    fun `should turn right from South`() {
        val rover = Rover(Position(0, 0), Direction.SOUTH)
        val updatedRover = rover.move("r", grid)
        assertEquals(Direction.WEST, updatedRover.direction)
    }

    @Test
    fun `should turn left from East`() {
        val rover = Rover(Position(0, 0), Direction.EAST)
        val updatedRover = rover.move("l", grid)
        assertEquals(Direction.NORTH, updatedRover.direction)
    }

    @Test
    fun `should turn right from East`() {
        val rover = Rover(Position(0, 0), Direction.EAST)
        val updatedRover = rover.move("r", grid)
        assertEquals(Direction.SOUTH, updatedRover.direction)
    }

    @Test
    fun `should execute multiple turns`() {
        val rover = Rover(Position(0, 0), Direction.NORTH)
        val updatedRover = rover.move("rr", grid)
        assertEquals(Direction.SOUTH, updatedRover.direction)
    }
}
