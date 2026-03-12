package part1;

import Catan_Part1.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EdgeTest {
    @Test
    void testRoadIsPlaced() {
        Agent agent = new Agent(1, new Resources(), 0);
        Edge edge = new Edge(4);
        assertFalse(edge.isOccupied());
    }

}
