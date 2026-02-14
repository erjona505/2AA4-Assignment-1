package catan;

/**
 * Represents an edge on the board where roads can be built
 */
public class Edge {
    private int id;
    private Road road;

    public Edge(int id) {
        this.id = id;
        this.road = null;
    }

    public int getId() {
        return id;
    }

    public Road getRoad() {
        return road;
    }

    public void setRoad(Road road) {
        this.road = road;
    }

    public boolean isOccupied() {
        return road != null;
    }

    @Override
    public String toString() {
        return "Edge{id=" + id + ", road=" + (road != null ? road : "none") + "}";
    }
}
