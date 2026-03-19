package Catan_Part3;
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * The GameState class exports the current state of the game to a JSON file.
 * It provides methods to convert the game state to JSON format and write it to a specified file.
 *
 * @author Erjona Kalari
 *
 */
public class GameState {

    private String filename;

    public GameState(String filename) {
        this.filename = filename;
    }

    //converts the game state to JSON and writes it to the file
    public void export(Game game) {
        String json = toJson(game);
        writeToFile(json);
    }

    //builds the full JSON string by combining the roads & buildings sections
    private String toJson(Game game) {
        GameMap map = game.getMap();

        String json = "{\n";
        json += "\"roads\": " + roadsToJson(map) + ",\n";
        json += "\"buildings\": " + buildingsToJson(map) + "\n";
        json += "}";

        return json;
    }

    //builds the roads section of the JSON
    private String roadsToJson(GameMap map) {
        String json = "[";

        for (int i = 0; i < 72; i++) {
            Edge edge = map.getEdge(i);

            // skip edges that don't have a road on them
            if (edge == null || edge.getRoad() == null) continue;

            List<Integer> nodes = map.getEdgeNodes(i);
            int a = Math.min(nodes.get(0), nodes.get(1));
            int b = Math.max(nodes.get(0), nodes.get(1));
            String owner = colorFromId(edge.getRoad().getOwner().getId());

            //add a comma before every entry except the first
            if (!json.equals("[")) json += ",";

            json += "{\"a\": " + a + ", \"b\": " + b + ", \"owner\": \"" + owner + "\"}";
        }

        json += "]";
        return json;
    }

    //builds the buildings section of the JSON
    private String buildingsToJson(GameMap map) {
        String json = "[";

        for (int i = 0; i < 54; i++) {
            Node node = map.getNode(i);

            //skip nodes that don't have a building on them
            if (node == null || !node.isOccupied()) continue;

            Building building = node.getBuilding();
            String type;
            if (building instanceof City) {
                type = "CITY";
            } else {
                type = "SETTLEMENT";
            }
            String owner = colorFromId(building.getOwner().getId());

            //add a comma before every entry except the first
            if (!json.equals("[")) json += ",";

            json += "{\"node\": " + i + ", \"owner\": \"" + owner + "\", \"type\": \"" + type + "\"}";
        }

        json += "]";
        return json;
    }

    //converts a numeric player id to a colour string
    private String colorFromId(int id) {
        switch (id) {
            case 1: return "RED";
            case 2: return "BLUE";
            case 3: return "WHITE";
            case 4: return "ORANGE";
            default: return "RED";
        }
    }

    //writes the JSON string to the output file
    private void writeToFile(String json) {
        try {
            //FileWriter writer = new FileWriter(filename);
            File file = new File(filename);
            file.getParentFile().mkdirs(); // creates parent directories if they don't exist
            FileWriter writer = new FileWriter(file);

            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}