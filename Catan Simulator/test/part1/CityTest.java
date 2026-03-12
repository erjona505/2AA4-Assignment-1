package part1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Catan_Part1.*;

public class CityTest {


    @Test
    public void testCityResourceAmount() {

        Agent agent = new Agent(1, new Resources(), 0);
        City city = new City(agent, 4);

        assertEquals(2, city.getResourceAmount());
    }
}
