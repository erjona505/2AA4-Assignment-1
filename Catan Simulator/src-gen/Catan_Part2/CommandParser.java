package Catan_Part2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandParser {

    private int toNodeId;
    private int fromNodeId;
    private int nodeId;
    private CommandType  commandType;


    public CommandParser(){
        commandType=CommandType.INVALID;
        toNodeId = -1;
        fromNodeId = -1;
        nodeId = -1;
    }


    public CommandType parser(String input) {

        commandType=CommandType.INVALID;
        toNodeId = -1;
        fromNodeId = -1;
        nodeId = -1;

        if(input == null){
            return CommandType.INVALID;
        }

        input=input.trim();

        Pattern rollPattern = Pattern.compile("^roll$",  Pattern.CASE_INSENSITIVE);
        Pattern listPattern = Pattern.compile("^list$",  Pattern.CASE_INSENSITIVE);
        Pattern goPattern = Pattern.compile("^go$",  Pattern.CASE_INSENSITIVE);

        Pattern cityPattern = Pattern.compile("^build\\s+city\\s+(\\d+)$",  Pattern.CASE_INSENSITIVE);
        Pattern settlementPattern = Pattern.compile("^build\\s+settlement\\s+(\\d+)$",  Pattern.CASE_INSENSITIVE);
        Pattern roadPattern = Pattern.compile("^build\\s+road\\s+\\[(\\d+)\\s*,\\s*(\\d+)\\]$",  Pattern.CASE_INSENSITIVE);


        if (rollPattern.matcher(input).matches()) {
            commandType = CommandType.ROLL;
            return commandType;
        }

        if (goPattern.matcher(input).matches()) {
            commandType = CommandType.GO;
            return commandType;
        }

        if (listPattern.matcher(input).matches()) {
            commandType = CommandType.LIST;
            return commandType;
        }

        Matcher settlementMatcher = settlementPattern.matcher(input);
        if (settlementMatcher.matches()) {
            commandType = CommandType.BUILD_SETTLEMENT;
            nodeId = Integer.parseInt(settlementMatcher.group(1));
            return commandType;
        }

        Matcher cityMatcher = cityPattern.matcher(input);
        if (cityMatcher.matches()) {
            commandType = CommandType.BUILD_CITY;
            nodeId = Integer.parseInt(cityMatcher.group(1));
            return commandType;
        }

        Matcher roadMatcher = roadPattern.matcher(input);
        if (roadMatcher.matches()) {
            commandType = CommandType.BUILD_ROAD;
            fromNodeId = Integer.parseInt(roadMatcher.group(1));
            toNodeId = Integer.parseInt(roadMatcher.group(2));
            return commandType;
        }

        return CommandType.INVALID;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public int getNodeId() {
        return nodeId;
    }

    public int getFromNodeId() {
        return fromNodeId;
    }

    public int getToNodeId() {
        return toNodeId;
    }

}
