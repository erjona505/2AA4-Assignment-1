import Catan_Part1.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NodeTest {
    @Test
    void testNewNodeUnoccupied() {
        Node node = new Node(0);
        assertFalse(node.isOccupied());
        assertNull(node.getBuilding());
    }

}
