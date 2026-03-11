import Catan_Part1.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RoadTest {

    @Test
    void testRoadIsPlaced() {

        Agent agent = new Agent(1, new Resources(), 0);
        Road road = new Road(agent, 4);
        assertTrue(road.isPlaced());
    }

    @Test
    public void testRoadNegativeEdge() {

        Agent agent = new Agent(1, new Resources(), 0);
        assertThrows(IllegalArgumentException.class, () -> {
            new Road(agent, -2);
        });
    }
}