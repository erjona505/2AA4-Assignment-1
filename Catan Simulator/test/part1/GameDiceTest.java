package part1;

import Catan_Part1.GameDice;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameDiceTest {
        //DICE TEST
        @Test
        void rollsBetween2And12() {
            GameDice dice = new GameDice();
            int result = dice.roll();
            assertTrue(result >= 2 && result <= 12);
        }
}
