package Catan_Part1;

/**
 * This classepresents a City in the Catan game.
 * 
 * A City is an upgraded form of a Settlement & is a type of Building that 
 * awards 2 victory points & produces 2 resources when activated.
 * Each City is owned by an Agent and is associated with a valid node ID.
 * 
 * @author Erjona Kalari
 * 
 */

public class City extends Building {

    private int nodeId; //ID of the board node where this city is placed

    /**
     * Constructs a City with the specified owner and node location.
     *
     * @param owner  the Agent who owns the city
     * @param nodeId the ID of the board node where the city is placed
     * @throws IllegalArgumentException if nodeId is negative
     */
    public City(Agent owner, int nodeId) {
        super(owner);
        if (nodeId < 0) {
            throw new IllegalArgumentException("Node ID cannot be negative");
        }
        this.nodeId = nodeId;
    }

    public int getNodeId() {
        return nodeId;
    }


    @Override
    public int getPoints() {
        return 2; //cities give 2 victory points
    }

    @Override
    public int getResourceAmount() {
        return 2; //cities generate 2 resources
    }

    /**
     * Returns a string representation of this City.
     *
     * @return formatted city description
     */
    @Override
    public String toString() {
        return "City(owner=" + getOwner() + ", nodeId=" + nodeId + ")";
    }
}

