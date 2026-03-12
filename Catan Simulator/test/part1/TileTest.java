package part1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Catan_Part1.*;

public class TileTest {
    /**
     * Test 1: Verify a normal resource tile stores its properties correctly.
     */
    @Test
    void testNormalTile() {
        Tile tile = new Tile(0, ResourceType.WOOD, 10);
        assertEquals(0, tile.getId());
        assertEquals(ResourceType.WOOD, tile.getResourceType());
        assertEquals(10, tile.getNumberToken());
    }

}
