package catan;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages a collection of resources with operations to add and remove them
 */
public class Resources {
    private Map<ResourceType, Integer> resources;

    public Resources() {
        this.resources = new HashMap<>();
        // Initialize all resource types to 0
        for (ResourceType type : ResourceType.values()) {
            if (type != ResourceType.DESERT) {
                resources.put(type, 0);
            }
        }
    }

    /**
     * Gets the total number of resource cards
     */
    public int totalCards() {
        return resources.values().stream().mapToInt(Integer::intValue).sum();
    }

    /**
     * Adds resources of a specific type
     */
    public void add(ResourceType type, int amount) {
        if (type != ResourceType.DESERT && amount > 0) {
            resources.put(type, resources.getOrDefault(type, 0) + amount);
        }
    }

    /**
     * Removes resources of a specific type
     * Returns true if successful, false if insufficient resources
     */
    public boolean remove(ResourceType type, int amount) {
        if (type == ResourceType.DESERT || amount <= 0) {
            return false;
        }
        int current = resources.getOrDefault(type, 0);
        if (current >= amount) {
            resources.put(type, current - amount);
            return true;
        }
        return false;
    }

    /**
     * Gets the amount of a specific resource type
     */
    public int getAmount(ResourceType type) {
        return resources.getOrDefault(type, 0);
    }

    /**
     * Checks if we have at least the specified amounts of resources
     */
    public boolean hasResources(Map<ResourceType, Integer> required) {
        for (Map.Entry<ResourceType, Integer> entry : required.entrySet()) {
            if (getAmount(entry.getKey()) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Removes multiple resource types at once
     */
    public boolean removeMultiple(Map<ResourceType, Integer> toRemove) {
        // First check if we have enough
        if (!hasResources(toRemove)) {
            return false;
        }
        // Then remove
        for (Map.Entry<ResourceType, Integer> entry : toRemove.entrySet()) {
            remove(entry.getKey(), entry.getValue());
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        boolean first = true;
        for (ResourceType type : ResourceType.values()) {
            if (type != ResourceType.DESERT) {
                int amount = getAmount(type);
                if (amount > 0) {
                    if (!first) sb.append(", ");
                    sb.append(type).append(":").append(amount);
                    first = false;
                }
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
