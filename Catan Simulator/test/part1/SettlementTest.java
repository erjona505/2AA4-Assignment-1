package part1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Catan_Part1.*;

public class SettlementTest {

    @Test
    public void testSettlementResourceAmount() {

        Agent agent = new Agent(1, new Resources(), 0);
        Settlement settlement = new Settlement(agent, 6);
        assertEquals(1, settlement.getResourceAmount());
    }

    @Test
    public void testSettlementInvalidNode() {

        Agent agent = new Agent(1, new Resources(), 0);
        assertThrows(IllegalArgumentException.class, () -> {
            new Settlement(agent, -1);
        });
    }
}

    