package part2;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Catan_Part2.*;
public class RobberTest {

    @Test
    void testRobberlocation(){
        Robber r= new Robber();

        assertEquals(r.getTileId(), 16);

        r.setTileId(8);

        assertEquals(r.getTileId(), 8);


    }


}
