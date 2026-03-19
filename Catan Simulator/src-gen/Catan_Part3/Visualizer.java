package Catan_Part3;

/**
 * The Visualizer interface represents the Target in the Adapter Pattern.
 * The Game class (Client) depends only on this interface.
 *
 */
public interface Visualizer {

    //updates the visualizer with the current state of the game
    void update(Game game);

    //closes the visualizer
    void close();
}
