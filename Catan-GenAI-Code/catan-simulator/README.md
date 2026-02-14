# Settlers of Catan Simulator
## SFWRENG 2AA4 - Assignment 1 (2026W)

This is a Java implementation of a Settlers of Catan simulator based on the UML design provided.

## Project Structure

```
catan-simulator/
├── src/main/java/catan/          # Source code
│   ├── Demonstrator.java         # Main entry point (Task 1.9)
│   ├── Game.java                 # Game controller
│   ├── Map.java                  # Board representation
│   ├── Agent.java                # Player agent
│   ├── Resources.java            # Resource management
│   ├── ResourceType.java         # Resource enumeration
│   ├── Building.java             # Abstract building class
│   ├── Settlement.java           # Settlement implementation
│   ├── City.java                 # City implementation
│   ├── Road.java                 # Road implementation
│   ├── Tile.java                 # Hexagonal tile
│   ├── Node.java                 # Vertex/node
│   ├── Edge.java                 # Edge
│   └── Config.java               # Configuration loader
├── config.txt                     # Configuration file
├── compile.sh                     # Compilation script
├── run.sh                         # Execution script
└── README.md                      # This file
```

## Requirements Implemented

### R1.1 - Board Setup
✅ Map initialized with valid tile/node/edge identification scheme
✅ Tiles: Center (0), Inner ring (1-6), Outer ring (7-18)
✅ Nodes: Following the same identification pattern
✅ Board configuration is hardcoded in Map.java

### R1.2 - Random Agents
✅ 4 agents play randomly on the generated map
✅ Agents make random decisions for building placement

### R1.3 - Game Rules
✅ Basic Catan rules implemented (excluding ignored features)
✅ Dice rolling and resource distribution
✅ Building costs and placement rules

### R1.4 - Configurable Rounds
✅ Game runs for user-defined number of rounds (max 8192)
✅ Configuration loaded from config.txt
✅ Game terminates when any agent reaches 10 VPs

### R1.5 - Termination
✅ Game halts on reaching max rounds OR 10 VPs

### R1.6 - Key Invariants
✅ Roads must connect to existing roads or settlements
✅ Cities must replace existing settlements
✅ Distance rule: settlements must be ≥2 nodes apart
✅ Node uniqueness: one building per node
✅ Edge uniqueness: one road per edge
✅ Proper resource costs enforced

### R1.7 - Output Format
✅ Actions printed as: [RoundNumber] / [PlayerID]: [Action]
✅ Victory points printed at end of each round

### R1.8 - Excess Cards
✅ Agents with >7 cards attempt to build
✅ Linear check of possible actions, random selection

### R1.9 - Demonstrator
✅ Demonstrator.java with static void main
✅ Comprehensive comments explaining functionality

## Ignored Features (as specified)
- Harbour tiles
- Trading (domestic and maritime)
- Development cards
- Robber mechanics (rolling 7 just skips resource production)

## How to Compile

```bash
# From the project root directory
chmod +x compile.sh
./compile.sh
```

Or manually:
```bash
javac -d bin src/main/java/catan/*.java
```

## How to Run

```bash
# From the project root directory
chmod +x run.sh
./run.sh
```

Or manually:
```bash
java -cp bin catan.Demonstrator
```

## Configuration

Edit `config.txt` to change the maximum number of rounds:

```
# Catan Simulator Configuration
turns: 50
```

Valid range: 1-8192 rounds

## Design Decisions

### Object-Oriented Principles

1. **Inheritance**: Building hierarchy (Building → Settlement/City)
2. **Encapsulation**: Each class manages its own state
3. **Polymorphism**: Buildings can be used interchangeably
4. **Abstraction**: Map handles all spatial logic

### SOLID Principles

1. **Single Responsibility**: 
   - Agent: manages player state
   - Map: manages board state
   - Game: manages game flow
   - Resources: manages resource collections

2. **Open/Closed**: 
   - Building hierarchy extensible without modification
   - New building types can be added by extending Building

3. **Liskov Substitution**: 
   - Settlement and City interchangeable where Building is expected

4. **Interface Segregation**: 
   - Classes expose only relevant methods to clients

5. **Dependency Inversion**: 
   - Game depends on Map/Agent abstractions, not implementations

### Key Invariants Implemented

1. **Distance Rule**: `Map.isValidSettlementPosition()` checks adjacent nodes
2. **Road Connectivity**: `Map.isConnectedToAgent()` validates road placement
3. **City Upgrades**: `Agent.buildCity()` verifies settlement exists
4. **Resource Costs**: All build methods check and deduct resources
5. **Uniqueness**: Map prevents multiple buildings/roads at same location

## Sample Output

```
=== Setup Phase ===
Player 0: placed initial settlement at node 0
Player 0: placed initial road at edge 0
...

--- Round 1 ---
[1] / Player 0: rolled 8
[1] / Player 0: received resources, now has 7 cards
[1] / Player 1: rolled 6
...
Victory Points: P0=1, P1=1, P2=1, P3=1

--- Round 2 ---
[2] / Player 0: rolled 9
[2] / Player 0: has 8 cards (>7), attempting to build
[2] / Player 0: built road at edge 23 (excess cards)
...

=== Game Over ===
Final Scores:
Player 0: 4 VP
Player 1: 3 VP
Player 2: 5 VP
Player 3: 2 VP

Winner: Player 2 with 5 victory points!
```

## Implementation Notes

1. **Simplified Adjacency**: Full Catan board adjacency is complex; we use a simplified but valid model
2. **Random Behavior**: Agents make random decisions as specified in R1.2
3. **Resource Distribution**: Based on dice rolls and tile number tokens
4. **Initial Setup**: Each player starts with 2 resources of each type and 1 settlement

## Testing

The Demonstrator class runs a complete game simulation that exercises all features:
- Initial setup phase
- Dice rolling and resource distribution
- Random building by agents
- Mandatory building when holding >7 cards
- Victory point tracking
- Game termination

## Authors

Developed for McMaster University SFWRENG 2AA4
Assignment 1 (2026W)
