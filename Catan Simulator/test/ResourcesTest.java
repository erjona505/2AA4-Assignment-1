import Catan_Part1.ResourceType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Catan_Part1.Resources;
public class ResourcesTest {

    @Test
    public void testTotalCards() {
        Resources r = new Resources();

        assertEquals(r.totalCards(), 0);

    }

    @Test
    public void testAdd() {
        Resources r = new Resources();

        r.add(ResourceType.WOOD, 2);

        assertEquals(r.totalCards(), 2);

    }

    @Test
    public void testRemove() {
        Resources r = new Resources();

        assertFalse(r.remove(ResourceType.WOOD, 2));

        r.add(ResourceType.WOOD, 2);
        assertTrue(r.remove(ResourceType.WOOD, 1));

        assertEquals(r.totalCards(), 1);
    }

    @Test
    public void testHasResourse() {
        Resources r = new Resources();

        r.add(ResourceType.ORE, 2);

        assertTrue(r.hasResource(ResourceType.ORE, 2));
        assertFalse(r.hasResource(ResourceType.WOOD, 2));

    }


}
