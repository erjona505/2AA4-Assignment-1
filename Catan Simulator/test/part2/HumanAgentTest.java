package part2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Catan_Part2.*;

public class HumanAgentTest {

    @Test
    public void testHumanAgent() {

        CommandParser commandParser = new CommandParser();
        Resources resources = new Resources();
        GameMap map = new GameMap();
        Agent humanAgent= new HumanAgent(1,resources, 0 );

        resources.add(ResourceType.WOOD, 3);
        resources.add(ResourceType.BRICK, 3);

        assertFalse(((HumanAgent) humanAgent).tryBuildCity(map));

        //even though we have the resources it doesn't because nod is -1
        assertFalse(((HumanAgent) humanAgent).tryBuildRoad(map));


    }

}
