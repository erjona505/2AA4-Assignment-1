package Catan_Part3;

/**
 * This class applies the Adapter design pattern that bridges the Visualizer interface.
 */

public class VisualizerAdapter implements Visualizer {
    private final GameState state;

    public VisualizerAdapter(String filePath) {
        this.state = new GameState(filePath);

    }

    @Override
    public void update(Game game) {
        state.export(game);
    }

    @Override
    public void close(){
        System.out.println("Visualizer closed.");
    }


}