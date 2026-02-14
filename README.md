# Catan Simulator
SFWRENG 2AA4 - Assignment 1
Winter 2026

# Project Overview 📝
This project implements a simulator of Settlers of Catan 
##The simulator:
- Initializes a valid Catan map using the standard tile and node identification
- Simulates four randomly acting agents
- Enforces key game invariants ( road connectivity, settlement distance rule, city replacement rule, etc)
- Executes for a configurable number of rounds (max of 8192)
- Terminates when a player reaches 10 victory points or the round limit is reached
- Prints actions to the console
- Includes a Demonstrator class to showcase core functionality


# Team Members 👤
  - Zain Al-Sakaji
  - Harnoor Sagar
  - Erjona Kalari
  - Zain Khalbous


# Tech Stack ⚙️
   - Java 24
   - Eclipse-compatible project structure
   - SonarQube for code analysis
   - YAML for build


# Project Structure 📂
```
  Catan-Simulator
  │
  └───src-gen
      └───Catan_Part1
          │   Agent.java
          │   Building.java
          │   City.java
          │   Demonstrator.java
          │   Dice.java
          │   Edge.java
          │   Game.java
          │   GameMap.java
          │   Node.java
          │   Road.java
          │   Settlement.java
          │
          └───UML
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
- Robber (rolling a 7 does not trigger resource removal)

     
      
  
 








