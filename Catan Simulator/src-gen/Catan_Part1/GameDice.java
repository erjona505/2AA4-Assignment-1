package Catan_Part1;
/**
 * 
 * This class implements the Dice interface, which defines a method for rolling the dice.
 * The roll() method simulates rolling two six-sided dice and returns their sum.
 * 
 * @author Erjona Kalari
 */
import java.util.Random;

public class GameDice implements Dice{

    //random number generator used to simulate dice rolls
    private Random random;

    public GameDice(Random random){
        this.random = random;
    }

     /**
     * Simulates rolling two six-sided dice.
     *
     * @return the sum of the two dice rolls
     */
    @Override
    public int roll(){
        int die1 = random.nextInt(6) + 1; 
        int die2 = random.nextInt(6) + 1; 
        return die1 + die2; 
    }

}
