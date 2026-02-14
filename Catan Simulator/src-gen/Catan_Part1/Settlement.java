package Catan_Part1;
/**
 * This class represents a Settlement in the Catan game.
 * 
 * A Settlement is a type of Building that awards 1 victory point
 * & produces 1 resource. Each Settlement is owned by an Agent and 
 * is associated with a valid node ID.
 * 
 * @author Erjona Kalari
 */
public class Settlement extends Building {

    private int nodeId; //ID of the board node where settlement is placed


     /**
     * Constructs a Settlement with the specified owner and node location.
     *
     * @param owner  the Agent who owns the settlement
     * @param nodeId the ID of the board node where the settlement is placed
     * @throws IllegalArgumentException if nodeId is negative
     */
    public Settlement(Agent owner, int nodeId) {
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
        return 1; //settlements give 1 victory point
    }

    @Override
    public int getResourceAmount() {
        return 1; //settlements generate 1 resource
    }

     /**
     * Returns a string representation of this Settlement.
     *
     * @return formatted settlement description
     */
    @Override
    public String toString() {
        return "Settlement(owner=" + getOwner() + ", nodeId=" + nodeId + ")";
    }
}
