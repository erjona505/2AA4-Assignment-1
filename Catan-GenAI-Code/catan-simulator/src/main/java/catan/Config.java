package catan;

import java.io.*;
import java.util.Properties;

/**
 * Manages game configuration loaded from a file
 */
public class Config {
    private int maxRounds;

    public Config(String configPath) {
        loadConfig(configPath);
    }

    /**
     * Load configuration from file
     * Expected format: turns: <number>
     */
    private void loadConfig(String configPath) {
        this.maxRounds = 100; // Default value
        
        try (BufferedReader reader = new BufferedReader(new FileReader(configPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }
                
                if (line.startsWith("turns:")) {
                    String value = line.substring(6).trim();
                    int turns = Integer.parseInt(value);
                    if (turns >= 1 && turns <= 8192) {
                        this.maxRounds = turns;
                    } else {
                        System.err.println("Warning: turns must be between 1 and 8192. Using default: 100");
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Warning: Could not read config file. Using defaults.");
        } catch (NumberFormatException e) {
            System.err.println("Warning: Invalid number format in config. Using defaults.");
        }
    }

    public int getMaxRounds() {
        return maxRounds;
    }
}
