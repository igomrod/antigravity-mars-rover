# Mars Rover API

A REST API implementation of the Mars Rover Kata following **Hexagonal Architecture** and **Test-Driven Development (TDD)** principles.

## 📋 Overview

This project simulates a Mars Rover navigating a spherical grid. The rover can:
- Move forward and backward
- Turn left and right
- Wrap around grid edges (spherical topology)
- Detect and avoid obstacles

## 🏗️ Architecture

The project follows **Hexagonal Architecture** (Ports and Adapters):

```
src/
├── main/kotlin/com/example/marsrover/
│   ├── domain/              # Business logic (entities, value objects)
│   ├── application/         # Use cases and ports (interfaces)
│   └── infrastructure/      # Adapters (repositories, controllers)
└── test/kotlin/com/example/marsrover/
    └── *Test.kt            # Unit tests
```

### Layers
- **Domain**: Pure business logic with no dependencies
- **Application**: Orchestrates domain logic, defines ports
- **Infrastructure**: Implements ports, handles external concerns

## 🚀 Getting Started

### Prerequisites
- **Java 21** (LTS) or higher
- **Gradle** (wrapper included)
- **SDKMAN** (recommended for Java version management)

### Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd mars-anitgravity-rover
   ```

2. **Install Java 21 using SDKMAN** (recommended)
   ```bash
   # Install SDKMAN if not already installed
   curl -s "https://get.sdkman.io" | bash
   source "$HOME/.sdkman/bin/sdkman-init.sh"
   
   # Install Java 21
   sdk install java 21-tem
   ```

3. **Verify Java installation**
   ```bash
   java -version
   # Should show Java 21 or higher
   ```

## 🧪 Running Tests

### Run all tests
```bash
./gradlew test
```

**Note**: If tests show as "UP-TO-DATE" (cached), you won't see individual test output. To see all tests execute:

### Force test execution and see detailed output
```bash
./gradlew clean test
# or
./gradlew test --rerun-tasks
```

You'll see output like:
```
RoverInitializationTest > should initialize rover at position 0,0 facing North() PASSED
RoverMovementTest > should move forward North() PASSED
RoverTurningTest > should turn left from North() PASSED
...
========================================
Test Results
========================================
Tests run: 31
Passed: 31
Failed: 0
Skipped: 0
========================================
```

### Run tests with detailed output
```bash
./gradlew test --info
```

### Run tests and force re-execution (ignore cache)
```bash
./gradlew clean test --rerun-tasks
```

### View test reports
After running tests, open the HTML report:
```bash
open build/reports/tests/test/index.html
```

## 📊 Test Coverage

**Total: 28 tests** across 5 test suites:

| Test Suite | Tests | Description |
|------------|-------|-------------|
| `RoverInitializationTest` | 2 | Rover initialization with position and direction |
| `RoverMovementTest` | 9 | Forward/backward movement in all directions |
| `RoverTurningTest` | 9 | Left/right turns from all orientations |
| `RoverWrappingTest` | 4 | Spherical grid wrapping (horizontal & vertical) |
| `RoverObstacleTest` | 4 | Obstacle detection and command abortion |

## 🏃 Running the Application

### Start the application
```bash
./gradlew bootRun
```

The application will start on `http://localhost:8080`

### Build the JAR
```bash
./gradlew build
```

The JAR will be created at `build/libs/mars-rover-0.0.1-SNAPSHOT.jar`

### Run the JAR
```bash
java -jar build/libs/mars-rover-0.0.1-SNAPSHOT.jar
```

## 📖 User Stories

All user stories are documented in `docs/user-stories/`:

- [US-01: System Initialization](docs/user-stories/US-01.md)
- [US-02: Rover Movement](docs/user-stories/US-02.md)
- [US-03: Rover Turning](docs/user-stories/US-03.md)
- [US-04: Spherical Wrapping](docs/user-stories/US-04.md)
- [US-05: Obstacle Detection](docs/user-stories/US-05.md)

Each user story includes:
- User story description
- Gherkin acceptance criteria
- Links to corresponding tests

## 🛠️ Development

### Project Structure
```
.
├── docs/user-stories/       # User story documentation
├── src/
│   ├── main/kotlin/         # Application source code
│   └── test/kotlin/         # Test source code
├── build.gradle.kts         # Gradle build configuration
└── README.md                # This file
```

### Key Domain Concepts

- **Rover**: The main entity with position and direction
- **Position**: Value object representing (x, y) coordinates
- **Direction**: Enum (NORTH, SOUTH, EAST, WEST)
- **Grid**: Handles coordinate wrapping and obstacles
- **MoveResult**: Result object containing rover state and obstacle info

### Commands

| Command | Description |
|---------|-------------|
| `f` | Move forward |
| `b` | Move backward |
| `l` | Turn left |
| `r` | Turn right |

### Update Gradle wrapper
```bash
./gradlew wrapper --gradle-version=8.5
```

## 📝 License

This project is for educational purposes.

## 🤝 Contributing

This is a kata implementation following strict TDD and Clean Architecture principles. Each feature was developed:
1. Write failing test (Red)
2. Implement minimum code to pass (Green)
3. Refactor while keeping tests green

---

**Built with ❤️ using Kotlin, Spring Boot, and TDD**
