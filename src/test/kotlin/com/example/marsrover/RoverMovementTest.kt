package com.example.marsrover

import com.example.marsrover.domain.Direction
import com.example.marsrover.domain.Position
import com.example.marsrover.domain.Rover
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RoverMovementTest {

    @Test
    fun `should move forward North`() {
        val rover = Rover(Position(0, 0), Direction.NORTH)
        val updatedRover = rover.move("f")
        assertEquals(Position(0, 1), updatedRover.position)
        assertEquals(Direction.NORTH, updatedRover.direction)
    }

    @Test
    fun `should move backward North`() {
        val rover = Rover(Position(0, 1), Direction.NORTH)
        val updatedRover = rover.move("b")
        assertEquals(Position(0, 0), updatedRover.position)
        assertEquals(Direction.NORTH, updatedRover.direction)
    }

    @Test
    fun `should move forward East`() {
        val rover = Rover(Position(0, 0), Direction.EAST)
        val updatedRover = rover.move("f")
        assertEquals(Position(1, 0), updatedRover.position)
        assertEquals(Direction.EAST, updatedRover.direction)
    }

    @Test
    fun `should move backward East`() {
        val rover = Rover(Position(1, 0), Direction.EAST)
        val updatedRover = rover.move("b")
        assertEquals(Position(0, 0), updatedRover.position)
        assertEquals(Direction.EAST, updatedRover.direction)
    }
    
    @Test
    fun `should move forward South`() {
        val rover = Rover(Position(0, 1), Direction.SOUTH)
        val updatedRover = rover.move("f")
        assertEquals(Position(0, 0), updatedRover.position)
        assertEquals(Direction.SOUTH, updatedRover.direction)
    }

    @Test
    fun `should move backward South`() {
        val rover = Rover(Position(0, 0), Direction.SOUTH)
        val updatedRover = rover.move("b")
        assertEquals(Position(0, 1), updatedRover.position)
        assertEquals(Direction.SOUTH, updatedRover.direction)
    }

    @Test
    fun `should move forward West`() {
        val rover = Rover(Position(1, 0), Direction.WEST)
        val updatedRover = rover.move("f")
        assertEquals(Position(0, 0), updatedRover.position)
        assertEquals(Direction.WEST, updatedRover.direction)
    }

    @Test
    fun `should move backward West`() {
        val rover = Rover(Position(0, 0), Direction.WEST)
        val updatedRover = rover.move("b")
        assertEquals(Position(1, 0), updatedRover.position)
        assertEquals(Direction.WEST, updatedRover.direction)
    }

    @Test
    fun `should execute multiple commands`() {
        val rover = Rover(Position(0, 0), Direction.NORTH)
        val updatedRover = rover.move("ff")
        assertEquals(Position(0, 2), updatedRover.position)
    }
}
