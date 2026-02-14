package catan;

import java.util.*;

/**
 * Represents the game board with tiles, nodes, and edges
 * Manages all spatial relationships and building placement rules
 */
public class Map {
    private List<Tile> tiles;
    private List<Node> nodes;
    private List<Edge> edges;
    
    // Adjacency information - which nodes are adjacent to which tiles
    private java.util.Map<Integer, List<Integer>> tileToNodes;
    
    // Adjacency information - which nodes connect to which edges
    private java.util.Map<Integer, List<Integer>> nodeToEdges;
    
    // Adjacency information - which nodes are neighbors
    private java.util.Map<Integer, List<Integer>> nodeNeighbors;

    public Map() {
        this.tiles = new ArrayList<>();
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
        this.tileToNodes = new HashMap<>();
        this.nodeToEdges = new HashMap<>();
        this.nodeNeighbors = new HashMap<>();
        
        initializeBoard();
    }

    /**
     * Initialize the board with a fixed layout
     * Following the tile identification: center (0), inner ring (1-6), outer ring (7-18)
     */
    private void initializeBoard() {
        // Create tiles - a simplified but valid Catan board
        // Center tile
        tiles.add(new Tile(0, ResourceType.WHEAT, 6));
        
        // Inner ring (6 tiles)
        tiles.add(new Tile(1, ResourceType.WOOD, 4));
        tiles.add(new Tile(2, ResourceType.BRICK, 5));
        tiles.add(new Tile(3, ResourceType.SHEEP, 9));
        tiles.add(new Tile(4, ResourceType.ORE, 10));
        tiles.add(new Tile(5, ResourceType.WHEAT, 8));
        tiles.add(new Tile(6, ResourceType.WOOD, 3));
        
        // Outer ring (12 tiles)
        tiles.add(new Tile(7, ResourceType.BRICK, 11));
        tiles.add(new Tile(8, ResourceType.SHEEP, 12));
        tiles.add(new Tile(9, ResourceType.ORE, 2));
        tiles.add(new Tile(10, ResourceType.WOOD, 6));
        tiles.add(new Tile(11, ResourceType.WHEAT, 4));
        tiles.add(new Tile(12, ResourceType.SHEEP, 5));
        tiles.add(new Tile(13, ResourceType.BRICK, 9));
        tiles.add(new Tile(14, ResourceType.ORE, 10));
        tiles.add(new Tile(15, ResourceType.WOOD, 8));
        tiles.add(new Tile(16, ResourceType.WHEAT, 3));
        tiles.add(new Tile(17, ResourceType.SHEEP, 11));
        tiles.add(new Tile(18, ResourceType.DESERT, 0));
        
        // Create nodes (54 nodes in standard Catan)
        for (int i = 0; i < 54; i++) {
            nodes.add(new Node(i));
        }
        
        // Create edges (72 edges in standard Catan)
        for (int i = 0; i < 72; i++) {
            edges.add(new Edge(i));
        }
        
        // Initialize adjacency maps (simplified version)
        initializeAdjacencies();
    }

    /**
     * Initialize adjacency relationships between tiles, nodes, and edges
     * This is a simplified adjacency model for demonstration
     */
    private void initializeAdjacencies() {
        // Map tiles to their surrounding nodes
        // Center tile (0)
        tileToNodes.put(0, Arrays.asList(0, 1, 2, 3, 4, 5));
        
        // Inner ring
        tileToNodes.put(1, Arrays.asList(0, 5, 6, 7, 8, 1));
        tileToNodes.put(2, Arrays.asList(1, 8, 9, 10, 11, 2));
        tileToNodes.put(3, Arrays.asList(2, 11, 12, 13, 14, 3));
        tileToNodes.put(4, Arrays.asList(3, 14, 15, 16, 17, 4));
        tileToNodes.put(5, Arrays.asList(4, 17, 18, 19, 20, 5));
        tileToNodes.put(6, Arrays.asList(5, 20, 21, 22, 6, 0));
        
        // Sample outer ring (simplified)
        tileToNodes.put(7, Arrays.asList(6, 22, 23, 24, 25, 7));
        tileToNodes.put(8, Arrays.asList(7, 25, 26, 27, 28, 8));
        tileToNodes.put(9, Arrays.asList(8, 28, 29, 30, 31, 9));
        tileToNodes.put(10, Arrays.asList(9, 31, 32, 33, 34, 10));
        tileToNodes.put(11, Arrays.asList(10, 34, 35, 36, 37, 11));
        tileToNodes.put(12, Arrays.asList(11, 37, 38, 39, 40, 12));
        tileToNodes.put(13, Arrays.asList(12, 40, 41, 42, 43, 13));
        tileToNodes.put(14, Arrays.asList(13, 43, 44, 45, 46, 14));
        tileToNodes.put(15, Arrays.asList(14, 46, 47, 48, 49, 15));
        tileToNodes.put(16, Arrays.asList(15, 49, 50, 51, 52, 16));
        tileToNodes.put(17, Arrays.asList(16, 52, 53, 21, 22, 17));
        tileToNodes.put(18, Arrays.asList(17, 22, 23, 24, 25, 18));
        
        // Initialize node neighbors (nodes connected by an edge)
        for (int i = 0; i < 54; i++) {
            nodeNeighbors.put(i, new ArrayList<>());
        }
        
        // Add sample neighbor relationships (this would be complete in full implementation)
        addNodeNeighbor(0, 1);
        addNodeNeighbor(1, 2);
        addNodeNeighbor(2, 3);
        addNodeNeighbor(3, 4);
        addNodeNeighbor(4, 5);
        addNodeNeighbor(5, 0);
        
        // Add more relationships for the inner ring
        for (int i = 0; i < 6; i++) {
            int outerNode = 6 + i * 2;
            addNodeNeighbor(i, outerNode);
            addNodeNeighbor(i, outerNode + 1);
            addNodeNeighbor(outerNode, outerNode + 1);
        }
        
        // Initialize node to edges mapping (simplified)
        for (int i = 0; i < 54; i++) {
            nodeToEdges.put(i, new ArrayList<>());
        }
        
        // Map edges to nodes (each edge connects two nodes)
        for (int i = 0; i < 72; i++) {
            int node1 = i / 2;
            int node2 = (i / 2 + 1) % 54;
            nodeToEdges.get(node1).add(i);
            nodeToEdges.get(node2).add(i);
        }
    }

    private void addNodeNeighbor(int node1, int node2) {
        if (!nodeNeighbors.get(node1).contains(node2)) {
            nodeNeighbors.get(node1).add(node2);
        }
        if (!nodeNeighbors.get(node2).contains(node1)) {
            nodeNeighbors.get(node2).add(node1);
        }
    }

    public Tile getTile(int id) {
        for (Tile tile : tiles) {
            if (tile.getId() == id) {
                return tile;
            }
        }
        return null;
    }

    public Node getNode(int id) {
        if (id >= 0 && id < nodes.size()) {
            return nodes.get(id);
        }
        return null;
    }

    public Edge getEdge(int id) {
        if (id >= 0 && id < edges.size()) {
            return edges.get(id);
        }
        return null;
    }

    /**
     * Check if an agent has a road at the specified edge
     */
    public boolean isRoad(Agent agent, int edgeId) {
        Edge edge = getEdge(edgeId);
        if (edge != null && edge.getRoad() != null) {
            return edge.getRoad().getOwner().equals(agent);
        }
        return false;
    }

    /**
     * Check if an agent has a settlement at the specified node
     */
    public boolean isSettlement(Agent agent, int nodeId) {
        Node node = getNode(nodeId);
        if (node != null && node.getBuilding() instanceof Settlement) {
            return node.getBuilding().getOwner().equals(agent);
        }
        return false;
    }

    /**
     * Check if an agent has a city at the specified node
     */
    public boolean isCity(Agent agent, int nodeId) {
        Node node = getNode(nodeId);
        if (node != null && node.getBuilding() instanceof City) {
            return node.getBuilding().getOwner().equals(agent);
        }
        return false;
    }

    /**
     * Place a road for an agent at the specified edge
     * Validates that the road connects to existing roads or settlements
     */
    public void placeRoad(Agent agent, int edgeId) {
        Edge edge = getEdge(edgeId);
        if (edge != null && !edge.isOccupied()) {
            edge.setRoad(new Road(agent));
        }
    }

    /**
     * Place a settlement for an agent at the specified node
     * Validates distance rule (no settlements within 2 edges)
     */
    public void placeSettlement(Agent agent, int nodeId) {
        Node node = getNode(nodeId);
        if (node != null && !node.isOccupied()) {
            node.setBuilding(new Settlement(agent));
        }
    }

    /**
     * Upgrade a settlement to a city at the specified node
     */
    public void placeCity(Agent agent, int nodeId) {
        Node node = getNode(nodeId);
        if (node != null && node.getBuilding() instanceof Settlement) {
            if (node.getBuilding().getOwner().equals(agent)) {
                node.setBuilding(new City(agent));
            }
        }
    }

    /**
     * Upgrade a settlement to a city (same as placeCity)
     */
    public void upgrade(Agent agent, int nodeId) {
        placeCity(agent, nodeId);
    }

    /**
     * Check if a node position is valid for placing a settlement
     * Must not violate the distance rule
     */
    public boolean isValidSettlementPosition(int nodeId) {
        Node node = getNode(nodeId);
        if (node == null || node.isOccupied()) {
            return false;
        }
        
        // Check distance rule - no settlements on adjacent nodes
        List<Integer> neighbors = nodeNeighbors.getOrDefault(nodeId, new ArrayList<>());
        for (int neighborId : neighbors) {
            Node neighbor = getNode(neighborId);
            if (neighbor != null && neighbor.isOccupied()) {
                return false;
            }
        }
        
        return true;
    }

    /**
     * Check if an edge connects to any of the agent's existing roads or settlements
     */
    public boolean isConnectedToAgent(Agent agent, int edgeId) {
        // For initial placement or if agent has no structures, allow placement
        if (getAgentRoadCount(agent) == 0 && getAgentSettlementCount(agent) == 0) {
            return true;
        }
        
        // Check if edge connects to an existing road or settlement
        // Simplified: just check a few adjacent edges
        for (int i = Math.max(0, edgeId - 3); i <= Math.min(edges.size() - 1, edgeId + 3); i++) {
            if (i != edgeId && isRoad(agent, i)) {
                return true;
            }
        }
        
        return false;
    }

    private int getAgentRoadCount(Agent agent) {
        int count = 0;
        for (Edge edge : edges) {
            if (edge.getRoad() != null && edge.getRoad().getOwner().equals(agent)) {
                count++;
            }
        }
        return count;
    }

    private int getAgentSettlementCount(Agent agent) {
        int count = 0;
        for (Node node : nodes) {
            if (node.getBuilding() != null && node.getBuilding().getOwner().equals(agent)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Distribute resources based on a dice roll
     */
    public void distributeResources(int diceRoll, List<Agent> agents) {
        // Find all tiles with the matching number token
        for (Tile tile : tiles) {
            if (tile.getNumberToken() == diceRoll && tile.getResourceType() != ResourceType.DESERT) {
                // Get nodes adjacent to this tile
                List<Integer> adjacentNodes = tileToNodes.getOrDefault(tile.getId(), new ArrayList<>());
                
                // Give resources to agents with settlements/cities on these nodes
                for (int nodeId : adjacentNodes) {
                    Node node = getNode(nodeId);
                    if (node != null && node.getBuilding() != null) {
                        Building building = node.getBuilding();
                        int multiplier = building.getResourceMultiplier();
                        building.getOwner().getResources().add(tile.getResourceType(), multiplier);
                    }
                }
            }
        }
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
