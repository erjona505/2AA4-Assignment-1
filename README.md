# Catan Simulator
SFWRENG 2AA4 - Assignment 1
Winter 2026

# Project Overview 📝
This project implements a simulator for the board game Settlers of Catan, extended to support human gameplay and interactive simulation.
##The simulator:
- Initializes a valid Catan map using the standard tile and node identification
- Simulates three computer agents and one human player
- Allows the human player to interact through the command line
- Integrates with the official visualizer provided by the instructor team
- Maintains the game state in a JSON file for visualization
- Enforces key game invariants:
   - Road connectivity
   - Settlement distance rule
   - City replacement rule
- Implements the Robber mechanism
- Executes for a configurable number of rounds (maximum 8192)
- Terminates when:
   - A player reaches 10 victory points or
   - The round limit is reached
- Prints game actions to the console
- Includes a Demonstrator class to showcase key functionality

# Human Player Interaction 👤
When it is the human player's turn, the simulator reads commands from the command line
Supported commands:
- Roll (Rolls the dice and distributes resources) 
- List (Display all the resource cards currently in the player's hand
- Build settlement [nodeId] (Builds a settlement at the specified node)
- Build city [nodeId] (Upgrades a settlement to a city)
- Build road [fromNodeId,toNodeId] (Builds a road between two nodes)
- Go (Ends the player's turn and proceeds to the next agent)

# Important Information about UML ❗❗❗
The updated UML can be found here 
- It is under Catan_UMl/Catan_part1.di/

# Robber Mechanism 🃏
The Robber is activated when a 7 is rolled
The simulator performs the following actions:
1. Players with more than seven cards discard cards.
2. The Robber is placed on a random tile.
3. A random adjacent player with a settlement or city near the tile is selected
4. That player loses a random card, which is transferred to the player who rolled the 7

# Game State Visualization 📊
The simulator exports the current game state to a JSON file after each action
This file is used by the Catan Visualizer provided by the instructor team
Visualizer repository:
https://github.com/ssm-lab/2aa4-2026-base/tree/main/assignments/visualize
The JSON file contains information about:
- Tiles and resources
- Node structures
- Roads
- Settlements and cities
- Player resources
- Current turn information


# Team Members 👤
  - Zain Al-Sakaji
  - Harnoor Sagar
  - Erjona Kalari
  - Zain Khalbous


# Tech Stack ⚙️
   - Java 24
   - JUnit for testing 
   - Eclipse-compatible project structure
   - JSON for game state 
   - SonarQube for code analysis
   - YAML for build


# Project Structure 📂
```
 
    Catan-Simulator
│
├── src
│   │
│   ├── Catan_Part1
│   │   │ Agent.java
│   │   │ Building.java
│   │   │ City.java
│   │   │ Demonstrator.java
│   │   │ Edge.java
│   │   │ Game.java
│   │   │ GameDice.java
│   │   │ GameMap.java
│   │   │ Node.java
│   │   │ ResourceType.java
│   │   │ Resources.java
│   │   │ Road.java
│   │   │ Settlement.java
│   │   │ Tile.java
│   │
│   └── Catan_Part2
│       │ Agent.java
│       │ Building.java
│       │ City.java
│       │ Command.java
│       │ CommandParser.java
│       │ CommandType.java
│       │ ComputerAgent.java
│       │ config.txt
│       │ Demonstrator.java
│       │ Edge.java
│       │ ExportGameState.java
│       │ Game.java
│       │ GameDice.java
│       │ GameMap.java
│       │ GameState.java
│       │ HumanAgent.java
│       │ Node.java
│       │ ResourceType.java
│       │ Road.java
│       │ Robber.java
│       │ Settlement.java
│       │ Tile.java
 └── UML

│
├── Task1
│   └── tests
│       │ AgentTest.java
│       │ BuildingTest.java
│       │ CityTest.java
│       │ EdgeTest.java
│       │ GameMapTest.java
│       │ GameTest.java
│       │ NodeTest.java
│       │ ResourcesTest.java
│       │ RoadTest.java
│       │ SettlementTest.java
│       │ TileTest.java
│
└── Task2
    └── tests
        │ CommandParserTest.java
```
# How to Run 💻
  1. Clone the repository
  git clone https://github.com/erjona505/2AA4-Assignment-1
  2. Open the project in your preferred IDE (Eclipse, IntelliJ, VS Code, etc.)
   3. Navigate to: 
   Catan Simulator/src-gen/Catan_Part1/Demonstrator.java
   4. Run Demonstrator.java as a Java application.
     
# Code Quality 🔍
SonarQube Analysis 
[![SonarQube Cloud](https://sonarcloud.io/images/project_badges/sonarcloud-dark.svg)](https://sonarcloud.io/summary/new_code?id=erjona505_2AA4-Assignment-1)

# Project Management 🗂️
- Public GitHub repository
- Kanban board is maintained and publicly available
- Commits linked to work items
- Final deliverable tagged

# Scope Limitations

The following features were excluded and will be implemented in the next version

- Domestic and maritime trading
- Development cards
- Advanced AI decision-making for agents

     
      
  
 








