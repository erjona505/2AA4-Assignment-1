import Catan_Part1.Road;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RoadTest {

    @Test
    public void testRoadIsPlaced() {

        Agent agent = new Agent(1, new Resources(), 0);
        Road road = new Road(agent, 4);
        assertTrue(road.isPlaced());
    }

    @Test   
    public void testRoadResourceAmount() {

        Agent agent = new Agent(1, new Resources(), 0);
        Road road = new Road(agent, 5);
        assertEquals(0, road.getResourceAmount());
    }

    @Test
    public void testRoadNegativeEdge() {

        Agent agent = new Agent(1, new Resources(), 0);
        assertThrows(IllegalArgumentException.class, () -> {
            new Road(agent, -2);
        });
    }
}