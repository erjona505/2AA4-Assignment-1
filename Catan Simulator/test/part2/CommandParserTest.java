package part2;

import Catan_Part2.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommandParserTest {

    @Test
    void testParseRoll() {
        CommandParser parser = new CommandParser();

        CommandType result = parser.parser("roll");

        assertEquals(CommandType.ROLL, result);
    }

    @Test
    void testParseList() {
        CommandParser parser = new CommandParser();

        CommandType result = parser.parser("list");

        assertEquals(CommandType.LIST, result);
    }

    @Test
    void testParseGo() {
        CommandParser parser = new CommandParser();

        CommandType result = parser.parser("go");

        assertEquals(CommandType.GO, result);
    }

    @Test
    void testParseBuildSettlement() {
        CommandParser parser = new CommandParser();

        CommandType result = parser.parser("build settlement 5");

        assertEquals(CommandType.BUILD_SETTLEMENT, result);
        assertEquals(5, parser.getNodeId());
    }

    @Test
    void testParseBuildCity() {
        CommandParser parser = new CommandParser();

        CommandType result = parser.parser("build city 8");

        assertEquals(CommandType.BUILD_CITY, result);
        assertEquals(8, parser.getNodeId());
    }

    @Test
    void testParseBuildRoad() {
        CommandParser parser = new CommandParser();

        CommandType result = parser.parser("build road 3, 7");

        assertEquals(CommandType.BUILD_ROAD, result);
        assertEquals(3, parser.getFromNodeId());
        assertEquals(7, parser.getToNodeId());
    }

    @Test
    void testInvalidCommand() {
        CommandParser parser = new CommandParser();

        CommandType result = parser.parser("hi");

        assertEquals(CommandType.INVALID, result);
    }

    @Test
    void testInvalidBuildRoadMissingSecondNode() {
        CommandParser parser = new CommandParser();

        CommandType result = parser.parser("build road 4");

        assertEquals(CommandType.INVALID, result);
    }

    @Test
    void testInvalidBuildCityNonNumericNode() {
        CommandParser parser = new CommandParser();

        CommandType result = parser.parser("build city y");

        assertEquals(CommandType.INVALID, result);
    }
}