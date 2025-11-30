package com.example.marsrover.application.ports

import com.example.marsrover.domain.Direction
import com.example.marsrover.domain.Rover

interface RoverCommandPort {
    fun initialize(x: Int, y: Int, direction: Direction): Rover
    fun execute(commands: String): Rover
}
