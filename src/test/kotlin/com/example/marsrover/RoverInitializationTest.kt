package com.example.marsrover

import com.example.marsrover.domain.Direction
import com.example.marsrover.domain.Position
import com.example.marsrover.domain.Rover
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RoverInitializationTest {

    @Test
    fun `should initialize rover at position 0,0 facing North`() {
        val rover = Rover(Position(0, 0), Direction.NORTH)

        assertEquals(Position(0, 0), rover.position)
        assertEquals(Direction.NORTH, rover.direction)
    }

    @Test
    fun `should initialize rover at position 3,5 facing East`() {
        val rover = Rover(Position(3, 5), Direction.EAST)

        assertEquals(Position(3, 5), rover.position)
        assertEquals(Direction.EAST, rover.direction)
    }
}
