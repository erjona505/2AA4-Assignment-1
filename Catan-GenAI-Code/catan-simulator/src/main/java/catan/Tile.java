package catan;

/**
 * Represents a hexagonal tile on the board
 */
public class Tile {
    private int id;
    private ResourceType resourceType;
    private int numberToken;

    public Tile(int id, ResourceType resourceType, int numberToken) {
        this.id = id;
        this.resourceType = resourceType;
        this.numberToken = numberToken;
    }

    public int getId() {
        return id;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public int getNumberToken() {
        return numberToken;
    }

    @Override
    public String toString() {
        return "Tile{id=" + id + ", type=" + resourceType + ", number=" + numberToken + "}";
    }
}
