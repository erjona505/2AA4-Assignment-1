package Catan_Part1;

public class City extends Building {

    private int nodeId; // node where city is placed

    public City(Agent owner, int nodeId) {
        super(owner);
        if (nodeId == null) {
            throw new IllegalArgumentException("Node ID cannot be null");
        }
        this.nodeId = nodeId;
    }

    public int getNodeId() {
        return nodeId;
    }

    @Override
    public int getPoints() {
        return 2; // Cities give 2 victory points
    }

    @Override
    public int getResourceAmount() {
        return 2; // Cities generate 2 resources
    }

    @Override
    public String toString() {
        return "City(owner=" + getOwner() + ", nodeId=" + nodeId + ")";
    }
}

