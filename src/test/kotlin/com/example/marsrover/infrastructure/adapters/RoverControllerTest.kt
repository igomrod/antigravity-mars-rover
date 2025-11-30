package com.example.marsrover.infrastructure.adapters

import com.example.marsrover.domain.Direction
import com.example.marsrover.domain.Position
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest
@AutoConfigureMockMvc
class RoverControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var gridRepository: InMemoryGridRepository

    @BeforeEach
    fun setup() {
        // Clear obstacles before each test
        gridRepository.clearObstacles()
    }

    @Test
    fun `should initialize rover via API`() {
        val request = InitializeRequest(x = 0, y = 0, direction = Direction.NORTH)

        mockMvc.perform(
            post("/api/rover/initialize")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.x").value(0))
            .andExpect(jsonPath("$.y").value(0))
            .andExpect(jsonPath("$.direction").value("NORTH"))
    }

    @Test
    fun `should execute commands via API`() {
        // Initialize first
        val initRequest = InitializeRequest(x = 0, y = 0, direction = Direction.NORTH)
        mockMvc.perform(
            post("/api/rover/initialize")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(initRequest))
        )

        // Execute commands
        val commandRequest = CommandRequest(commands = "ffrff")
        mockMvc.perform(
            post("/api/rover/commands")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(commandRequest))
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.x").value(2))
            .andExpect(jsonPath("$.y").value(2))
            .andExpect(jsonPath("$.direction").value("EAST"))
            .andExpect(jsonPath("$.obstacleEncountered").value(false))
    }

    @Test
    fun `should report obstacle via API`() {
        // Add obstacle
        gridRepository.addObstacle(Position(0, 2))

        // Initialize
        val initRequest = InitializeRequest(x = 0, y = 0, direction = Direction.NORTH)
        mockMvc.perform(
            post("/api/rover/initialize")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(initRequest))
        )

        // Execute commands that hit obstacle
        val commandRequest = CommandRequest(commands = "fff")
        mockMvc.perform(
            post("/api/rover/commands")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(commandRequest))
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.x").value(0))
            .andExpect(jsonPath("$.y").value(1))
            .andExpect(jsonPath("$.obstacleEncountered").value(true))
            .andExpect(jsonPath("$.obstacleX").value(0))
            .andExpect(jsonPath("$.obstacleY").value(2))
    }
}
