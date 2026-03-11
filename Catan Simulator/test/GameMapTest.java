import Catan_Part1.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameMapTest {
    GameMap map = new GameMap();;

    @Test
    public void TestValidSettlementPosition(){
        assertFalse(map.isValidSettlementPosition(0));


    }

}
