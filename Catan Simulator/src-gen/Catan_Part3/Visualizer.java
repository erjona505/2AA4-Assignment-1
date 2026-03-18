package Catan_Part3;

/**
 * This is the Visualizer interface to apply the
 * Adapter design pattern.
 *
 * Game (the Client) depends only on
 * this interface. It never knows whether updates go.
 *
 *
 *
 */
public interface Visualizer {

    void update(Game game);

    void close();
}
