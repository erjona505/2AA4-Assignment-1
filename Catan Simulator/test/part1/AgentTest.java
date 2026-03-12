package part1;

import Catan_Part1.ResourceType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Catan_Part1.Agent;
import Catan_Part1.Resources;

public class AgentTest {
    /**
     * Test 22: Agent with exactly 7 cards reports isSevenCards as false.
     * Boundary: > 7 is true, == 7 is false.
     */
    @Test
    void testIsSevenCardsBoundary() {
        Agent agent = new Agent(1, new Resources(), 0);
        agent.getResources().add(ResourceType.WOOD, 4);
        agent.getResources().add(ResourceType.BRICK, 3);
        assertFalse(agent.isSevenCards());
    }

    @Test

    void testAddPoint(){
        Agent agent = new Agent(1, new Resources(), 0);
        agent.addPoints(2);

        assertEquals(agent.getTotalPoints(), 2);
    }

    @Test

    void testCheckBuild(){

        Resources resources=new Resources();
        Agent agent = new Agent(1, resources, 0);

        resources.add(ResourceType.WOOD, 2);
        resources.add(ResourceType.BRICK, 2);

        assertTrue(agent.checkRoadCost()); //check if has cost

        agent.buyRoad(); //buy for build

        assertEquals(resources.totalCards(), 2); //check if buy

        assertFalse(agent.checkCityCost());

        assertFalse(agent.checkSettlementCost());

    }

}
