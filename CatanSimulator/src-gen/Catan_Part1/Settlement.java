package Catan_Part1;

public class Settlement extends Building {

    private int nodeId; //node where settlement is placed

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

    @Override
    public String toString() {
        return "Settlement(owner=" + getOwner() + ", nodeId=" + nodeId + ")";
    }
}
