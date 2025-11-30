package com.example.marsrover.infrastructure.adapters

import com.example.marsrover.application.ports.RoverCommandPort
import com.example.marsrover.domain.Direction
import com.example.marsrover.domain.MoveResult
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/rover")
class RoverController(private val roverCommandPort: RoverCommandPort) {

    @PostMapping("/initialize")
    fun initialize(@RequestBody request: InitializeRequest): ResponseEntity<RoverResponse> {
        val rover = roverCommandPort.initialize(request.x, request.y, request.direction)
        return ResponseEntity.ok(RoverResponse.from(rover))
    }

    @PostMapping("/commands")
    fun executeCommands(@RequestBody request: CommandRequest): ResponseEntity<CommandResponse> {
        val result = roverCommandPort.execute(request.commands)
        return ResponseEntity.ok(CommandResponse.from(result))
    }
}

data class InitializeRequest(
    val x: Int,
    val y: Int,
    val direction: Direction
)

data class RoverResponse(
    val x: Int,
    val y: Int,
    val direction: String
) {
    companion object {
        fun from(rover: com.example.marsrover.domain.Rover) = RoverResponse(
            x = rover.position.x,
            y = rover.position.y,
            direction = rover.direction.name
        )
    }
}

data class CommandRequest(
    val commands: String
)

data class CommandResponse(
    val x: Int,
    val y: Int,
    val direction: String,
    val obstacleEncountered: Boolean,
    val obstacleX: Int?,
    val obstacleY: Int?
) {
    companion object {
        fun from(result: MoveResult) = CommandResponse(
            x = result.rover.position.x,
            y = result.rover.position.y,
            direction = result.rover.direction.name,
            obstacleEncountered = result.obstacleEncountered,
            obstacleX = result.obstaclePosition?.x,
            obstacleY = result.obstaclePosition?.y
        )
    }
}
