import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Catan_Part1.*;

public class BuildingTest {

    @Test
    void testOwnerAssignedCorrectly() {

        Agent agent = new Agent(1, new Resources(), 0);
        Settlement settlement = new Settlement(agent, 3);

        assertEquals(agent, settlement.getOwner());
    }
}
