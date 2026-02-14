package catan;

/**
 * Demonstrator class for the Catan Simulator
 * 
 * This class demonstrates the functionality of the Catan simulator by running
 * one or more demonstration games. The simulator implements the core mechanics
 * of Settlers of Catan including:
 * 
 * - Board setup with tiles, nodes, and edges following the specified ID scheme
 * - 4 randomly acting agents
 * - Resource production based on dice rolls
 * - Building roads, settlements, and cities
 * - Victory point tracking
 * - Mandatory building when holding >7 cards (R1.8)
 * - Game termination on reaching 10 VP or max rounds
 * 
 * KEY INVARIANTS IMPLEMENTED (R1.6):
 * 1. Distance rule: Settlements cannot be placed on adjacent nodes
 * 2. Road connectivity: New roads must connect to existing roads or settlements
 * 3. City upgrades: Cities must replace existing settlements owned by the same agent
 * 4. Resource costs: All buildings require proper resource expenditure
 * 5. Node uniqueness: Only one building per node
 * 6. Edge uniqueness: Only one road per edge
 * 
 * DESIGN DECISIONS:
 * 
 * 1. Object-Oriented Design:
 *    - Building hierarchy: Abstract Building class with Settlement and City subclasses
 *      demonstrates inheritance and polymorphism
 *    - Agent encapsulation: Each agent manages its own resources and points
 *    - Map responsibility: The Map class handles all spatial relationships and
 *      building placement validation
 * 
 * 2. SOLID Principles Applied:
 *    - Single Responsibility: Each class has one clear purpose (Agent manages player
 *      state, Map manages board state, Game manages game flow)
 *    - Open/Closed: Building hierarchy allows extension without modifying base code
 *    - Liskov Substitution: Settlement and City can be used interchangeably where
 *      Building type is expected
 *    - Interface Segregation: Classes only expose methods relevant to their clients
 *    - Dependency Inversion: High-level Game class depends on abstractions (Map, Agent)
 *      not concrete implementations
 * 
 * 3. Simplified Adjacency Model:
 *    - The full Catan board adjacency is complex; we use a simplified but valid model
 *    - In a production system, this would be loaded from data files
 * 
 * 4. Random Agent Behavior:
 *    - Agents make random decisions as specified in R1.2
 *    - When holding >7 cards, agents try actions in random order until successful
 * 
 * 5. Configuration:
 *    - Max rounds configurable via config file (default: 100)
 *    - Format: "turns: <number>" where number is 1-8192
 */
public class Demonstrator {
    
    /**
     * Main entry point for the Catan simulator demonstration
     * 
     * This method runs a demonstration game that shows:
     * - Initial setup phase where players place starting settlements
     * - Resource distribution based on dice rolls
     * - Random building actions by agents
     * - Mandatory building when agents have >7 cards
     * - Victory point progression
     * - Game termination conditions
     * 
     * The output follows the specified format:
     * [RoundNumber] / [PlayerID]: [Action]
     * 
     * Victory points are printed at the end of each round.
     */
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║   SETTLERS OF CATAN - SIMULATOR DEMONSTRATION     ║");
        System.out.println("║   McMaster University - SFWRENG 2AA4              ║");
        System.out.println("║   Assignment 1 - 2026W                            ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
        System.out.println();
        
        // Create a default configuration file if it doesn't exist
        createDefaultConfig();
        
        // Load configuration
        // The config file should be in the current directory as "config.txt"
        Config config = new Config("config.txt");
        
        // Create and run the game
        // This demonstrates all core functionality:
        // - Map initialization with specified tile/node/edge IDs
        // - 4 agents playing randomly
        // - Dice rolling and resource distribution
        // - Building placement with validation
        // - Victory point tracking
        // - Game termination
        Game game = new Game(config);
        game.runGame();
        
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║   DEMONSTRATION COMPLETE                          ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
    }
    
    /**
     * Creates a default configuration file if one doesn't exist
     * This ensures the demonstration can run without manual setup
     */
    private static void createDefaultConfig() {
        try {
            java.io.File configFile = new java.io.File("config.txt");
            if (!configFile.exists()) {
                java.io.PrintWriter writer = new java.io.PrintWriter(configFile);
                writer.println("# Catan Simulator Configuration");
                writer.println("# Maximum number of rounds (1-8192)");
                writer.println("turns: 50");
                writer.close();
                System.out.println("Created default config.txt with 50 rounds");
                System.out.println();
            }
        } catch (java.io.IOException e) {
            System.err.println("Warning: Could not create config file");
        }
    }
}
