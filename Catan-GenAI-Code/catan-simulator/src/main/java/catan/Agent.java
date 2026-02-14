package catan;

import java.util.*;

/**
 * Represents a player (agent) in the game
 */
public class Agent {
    private int id;
    private Resources resources;
    private int points;

    public Agent(int id) {
        this.id = id;
        this.resources = new Resources();
        this.points = 0;
    }

    public int getId() {
        return id;
    }

    public Resources getResources() {
        return resources;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    /**
     * Check if agent has more than 7 cards (triggers mandatory building)
     */
    public boolean isSevenCards() {
        return resources.totalCards() > 7;
    }

    /**
     * Take a turn for this agent
     */
    public void takeTurn(Map map, int round, Random random) {
        // This method will be called by the game loop
        // The actual logic is delegated to build methods
    }

    /**
     * Attempt to build a road at the specified edge
     */
    public boolean buildRoad(int edgeId, Map map) {
        // Cost: 1 wood, 1 brick
        java.util.Map<ResourceType, Integer> cost = new HashMap<>();
        cost.put(ResourceType.WOOD, 1);
        cost.put(ResourceType.BRICK, 1);

        Edge edge = map.getEdge(edgeId);
        if (edge == null || edge.isOccupied()) {
            return false;
        }

        // Check if connected to existing roads or settlements
        if (!map.isConnectedToAgent(this, edgeId)) {
            return false;
        }

        if (resources.hasResources(cost)) {
            resources.removeMultiple(cost);
            map.placeRoad(this, edgeId);
            return true;
        }
        return false;
    }

    /**
     * Attempt to build a settlement at the specified node
     */
    public boolean buildSettlement(int nodeId, Map map) {
        // Cost: 1 wood, 1 brick, 1 wheat, 1 sheep
        java.util.Map<ResourceType, Integer> cost = new HashMap<>();
        cost.put(ResourceType.WOOD, 1);
        cost.put(ResourceType.BRICK, 1);
        cost.put(ResourceType.WHEAT, 1);
        cost.put(ResourceType.SHEEP, 1);

        if (!map.isValidSettlementPosition(nodeId)) {
            return false;
        }

        if (resources.hasResources(cost)) {
            resources.removeMultiple(cost);
            map.placeSettlement(this, nodeId);
            addPoints(1); // Settlement gives 1 VP
            return true;
        }
        return false;
    }

    /**
     * Attempt to upgrade a settlement to a city at the specified node
     */
    public boolean buildCity(int nodeId, Map map) {
        // Cost: 3 ore, 2 wheat
        java.util.Map<ResourceType, Integer> cost = new HashMap<>();
        cost.put(ResourceType.ORE, 3);
        cost.put(ResourceType.WHEAT, 2);

        Node node = map.getNode(nodeId);
        if (node == null || !(node.getBuilding() instanceof Settlement)) {
            return false;
        }

        if (!node.getBuilding().getOwner().equals(this)) {
            return false;
        }

        if (resources.hasResources(cost)) {
            resources.removeMultiple(cost);
            map.placeCity(this, nodeId);
            addPoints(1); // City upgrade adds 1 VP (2 total - 1 from settlement)
            return true;
        }
        return false;
    }

    /**
     * Try to spend resources if holding more than 7 cards
     * Attempts buildings in random order until successful or no more attempts
     */
    public String trySpendExcessCards(Map map, Random random) {
        if (!isSevenCards()) {
            return null;
        }

        // Create list of possible actions
        List<String> actions = new ArrayList<>();
        actions.add("road");
        actions.add("settlement");
        actions.add("city");

        // Shuffle actions for randomness
        Collections.shuffle(actions, random);

        for (String action : actions) {
            switch (action) {
                case "road":
                    // Try random edges
                    for (int attempt = 0; attempt < 10; attempt++) {
                        int edgeId = random.nextInt(map.getEdges().size());
                        if (buildRoad(edgeId, map)) {
                            return "built road at edge " + edgeId + " (excess cards)";
                        }
                    }
                    break;
                case "settlement":
                    // Try random nodes
                    for (int attempt = 0; attempt < 10; attempt++) {
                        int nodeId = random.nextInt(map.getNodes().size());
                        if (buildSettlement(nodeId, map)) {
                            return "built settlement at node " + nodeId + " (excess cards)";
                        }
                    }
                    break;
                case "city":
                    // Try upgrading existing settlements
                    for (Node node : map.getNodes()) {
                        if (node.getBuilding() instanceof Settlement &&
                            node.getBuilding().getOwner().equals(this)) {
                            if (buildCity(node.getId(), map)) {
                                return "upgraded to city at node " + node.getId() + " (excess cards)";
                            }
                        }
                    }
                    break;
            }
        }

        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Agent agent = (Agent) obj;
        return id == agent.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Agent{id=" + id + ", points=" + points + ", resources=" + resources + "}";
    }
}
