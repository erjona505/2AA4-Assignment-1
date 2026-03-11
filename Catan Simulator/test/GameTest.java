import Catan_Part1.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class GameTest {

    @Test
    void simpleTest() {
        assertEquals(4, 2 + 2);
    }

    //DICE TEST
    @Test
    void rollsBetween2And12(){
        GameDice dice= new GameDice();
        int result= dice.roll();
        assertTrue(result>=2 && result<=12);
    }

    
}
