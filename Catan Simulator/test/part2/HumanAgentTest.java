package part2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Catan_Part2.*;

public class HumanAgentTest {
    @Test
    public void testHumanAgent1() {
        CommandParser commandParser = new CommandParser();
        Resources resources = new Resources();
        GameMap map = new GameMap();
        GameState exporter = new GameState("state.json");

        Agent humanAgent = new HumanAgent(1, resources, 0, exporter);

        // Add resources
        resources.add(ResourceType.WOOD, 3);
        resources.add(ResourceType.BRICK, 3);

        // Try to build city and road with invalid node/edge (-1)
        assertFalse(((HumanAgent) humanAgent).tryBuildCity(map));
        assertFalse(((HumanAgent) humanAgent).tryBuildRoad(map));
    }

//    @Test
//    public void testHumanAgent() {
//
//        CommandParser commandParser = new CommandParser();
//        Resources resources = new Resources();
//        GameMap map = new GameMap();
//        GameState exporter = new GameState(exporter);
//        Agent humanAgent= new HumanAgent(1,resources, 0 , exporter);
//
//        resources.add(ResourceType.WOOD, 3);
//        resources.add(ResourceType.BRICK, 3);
//
//        assertFalse(((HumanAgent) humanAgent).tryBuildCity(map));
//
//        //even though we have the resources it doesn't because nod is -1
//        assertFalse(((HumanAgent) humanAgent).tryBuildRoad(map));
//
//
//    }

}
