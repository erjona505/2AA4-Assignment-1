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
//game should not be over after creation
    @Test
    void testGameNotOverAtStart() {
        GameMap map = new GameMap();
        Agent[] agents = {
                new Agent(1, new Resources(), 0),
                new Agent(2, new Resources(), 0)
        };

        Game game = new Game(map, agents, 10);

        assertFalse(game.gameOver());
    }
//game end when max rounds is zero
    @Test
    void testGameOverWhenMaxRoundsReached() {
        GameMap map = new GameMap();
        Agent[] agents = {
                new Agent(1, new Resources(), 0),
                new Agent(2, new Resources(), 0)
        };

        Game game = new Game(map, agents, 0);

        assertTrue(game.gameOver());
    }

    @Test
    void testGameOverWhenPointsAboveTen() {
        GameMap map = new GameMap();

        Agent a1 = new Agent(1, new Resources(), 0);
        Agent a2 = new Agent(2, new Resources(), 0);

        a1.addPoints(11);

        Agent[] agents = {a1, a2};

        Game game = new Game(map, agents, 20);

        assertTrue(game.gameOver());
    }

    
}
