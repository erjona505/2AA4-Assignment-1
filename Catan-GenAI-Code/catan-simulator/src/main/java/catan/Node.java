package catan;

/**
 * Represents a vertex/node on the board where settlements and cities can be built
 */
public class Node {
    private int id;
    private Building building;

    public Node(int id) {
        this.id = id;
        this.building = null;
    }

    public int getId() {
        return id;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public boolean isOccupied() {
        return building != null;
    }

    @Override
    public String toString() {
        return "Node{id=" + id + ", building=" + (building != null ? building : "none") + "}";
    }
}
