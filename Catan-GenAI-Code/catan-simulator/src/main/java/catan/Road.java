package catan;

/**
 * Represents a road on an edge
 */
public class Road {
    private Agent owner;

    public Road(Agent owner) {
        this.owner = owner;
    }

    public Agent getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "Road(Player " + owner.getId() + ")";
    }
}
