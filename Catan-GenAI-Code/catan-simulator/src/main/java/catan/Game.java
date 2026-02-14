package catan;

import java.util.*;

/**
 * Main game controller that manages the simulation
 */
public class Game {
    private Map map;
    private List<Agent> agents;
    private int round;
    private Config config;
    private Random random;

    public Game(Config config) {
        this.config = config;
        this.map = new Map();
        this.agents = new ArrayList<>();
        this.round = 0;
        this.random = new Random();
        
        // Initialize 4 agents
        for (int i = 0; i < 4; i++) {
            agents.add(new Agent(i));
        }
    }

    /**
     * Run the game simulation
     */
    public void runGame() {
        System.out.println("=== Catan Simulator Starting ===");
        System.out.println("Max rounds: " + config.getMaxRounds());
        System.out.println();

        // Initial setup phase - give each player some starting resources and structures
        setupPhase();

        // Main game loop
        while (!isDone()) {
            round++;
            runRound();
        }

        System.out.println();
        System.out.println("=== Game Over ===");
        printFinalScores();
    }

    /**
     * Setup phase - place initial settlements and roads
     */
    private void setupPhase() {
        System.out.println("=== Setup Phase ===");
        
        // Each player places 2 settlements and 2 roads
        for (int i = 0; i < agents.size(); i++) {
            Agent agent = agents.get(i);
            
            // Place first settlement and road
            int settlement1 = i * 10; // Spread out starting positions
            int road1 = i * 15;
            
            if (settlement1 < map.getNodes().size() && map.isValidSettlementPosition(settlement1)) {
                map.placeSettlement(agent, settlement1);
                agent.addPoints(1);
                System.out.println("Player " + i + ": placed initial settlement at node " + settlement1);
            }
            
            if (road1 < map.getEdges().size()) {
                map.placeRoad(agent, road1);
                System.out.println("Player " + i + ": placed initial road at edge " + road1);
            }
            
            // Give starting resources
            agent.getResources().add(ResourceType.WOOD, 2);
            agent.getResources().add(ResourceType.BRICK, 2);
            agent.getResources().add(ResourceType.WHEAT, 2);
            agent.getResources().add(ResourceType.SHEEP, 2);
            agent.getResources().add(ResourceType.ORE, 1);
        }
        
        System.out.println();
    }

    /**
     * Run a single round (all 4 players take turns)
     */
    private void runRound() {
        System.out.println("--- Round " + round + " ---");
        
        for (Agent agent : agents) {
            takeTurn(agent);
        }
        
        // Print victory points at end of round
        System.out.print("Victory Points: ");
        for (int i = 0; i < agents.size(); i++) {
            System.out.print("P" + i + "=" + agents.get(i).getPoints());
            if (i < agents.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
        System.out.println();
    }

    /**
     * Execute a single turn for an agent
     */
    private void takeTurn(Agent agent) {
        // Roll dice (2d6)
        int die1 = random.nextInt(6) + 1;
        int die2 = random.nextInt(6) + 1;
        int diceRoll = die1 + die2;
        
        System.out.println("[" + round + "] / Player " + agent.getId() + ": rolled " + diceRoll);
        
        // Handle rolling a 7 (no resources as per requirements)
        if (diceRoll == 7) {
            System.out.println("[" + round + "] / Player " + agent.getId() + ": rolled 7, no resources produced");
            return;
        }
        
        // Distribute resources
        map.distributeResources(diceRoll, agents);
        
        // Check if agent has resources after dice roll
        if (agent.getResources().totalCards() > 0) {
            System.out.println("[" + round + "] / Player " + agent.getId() + ": received resources, now has " + 
                             agent.getResources().totalCards() + " cards");
        }
        
        // R1.8: If agent has more than 7 cards, try to spend them
        if (agent.isSevenCards()) {
            System.out.println("[" + round + "] / Player " + agent.getId() + ": has " + 
                             agent.getResources().totalCards() + " cards (>7), attempting to build");
            String action = agent.trySpendExcessCards(map, random);
            if (action != null) {
                System.out.println("[" + round + "] / Player " + agent.getId() + ": " + action);
            } else {
                System.out.println("[" + round + "] / Player " + agent.getId() + ": unable to spend cards");
            }
        }
        
        // Random building actions
        performRandomAction(agent);
    }

    /**
     * Agent attempts random building actions
     */
    private void performRandomAction(Agent agent) {
        // Try random actions
        int actionType = random.nextInt(10);
        
        if (actionType < 4) {
            // Try to build a road
            int edgeId = random.nextInt(map.getEdges().size());
            if (agent.buildRoad(edgeId, map)) {
                System.out.println("[" + round + "] / Player " + agent.getId() + ": built road at edge " + edgeId);
            }
        } else if (actionType < 7) {
            // Try to build a settlement
            int nodeId = random.nextInt(map.getNodes().size());
            if (agent.buildSettlement(nodeId, map)) {
                System.out.println("[" + round + "] / Player " + agent.getId() + ": built settlement at node " + nodeId);
            }
        } else if (actionType < 9) {
            // Try to upgrade to city
            for (Node node : map.getNodes()) {
                if (node.getBuilding() instanceof Settlement && 
                    node.getBuilding().getOwner().equals(agent)) {
                    if (agent.buildCity(node.getId(), map)) {
                        System.out.println("[" + round + "] / Player " + agent.getId() + ": upgraded to city at node " + node.getId());
                        break;
                    }
                }
            }
        }
        // else: do nothing
    }

    /**
     * Check if game is done
     */
    public boolean isDone() {
        // Check if max rounds reached
        if (round >= config.getMaxRounds()) {
            return true;
        }
        
        // Check if any player has 10 or more victory points
        for (Agent agent : agents) {
            if (agent.getPoints() >= 10) {
                return true;
            }
        }
        
        return false;
    }

    /**
     * Print final scores
     */
    private void printFinalScores() {
        System.out.println("Final Scores:");
        for (Agent agent : agents) {
            System.out.println("Player " + agent.getId() + ": " + agent.getPoints() + " VP");
        }
        
        // Find winner
        Agent winner = agents.get(0);
        for (Agent agent : agents) {
            if (agent.getPoints() > winner.getPoints()) {
                winner = agent;
            }
        }
        
        System.out.println();
        System.out.println("Winner: Player " + winner.getId() + " with " + winner.getPoints() + " victory points!");
    }

    public Map getMap() {
        return map;
    }

    public List<Agent> getAgents() {
        return agents;
    }

    public int getRound() {
        return round;
    }
}
