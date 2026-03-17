package Catan_Part3;

import java.util.Scanner;

public class Command {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        CommandParser parser = new CommandParser();

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            CommandType type = parser.parser(input);

            System.out.println("Command type: " + type);

            if (type == CommandType.BUILD_SETTLEMENT || type == CommandType.BUILD_CITY) {
                System.out.println("Node id: " + parser.getNodeId());
            }

            if (type == CommandType.BUILD_ROAD) {
                System.out.println("From node: " + parser.getFromNodeId());
                System.out.println("To node: " + parser.getToNodeId());
            }

            if (type == CommandType.GO) {
                System.out.println("Go command detected. Exiting test.");
                break;
            }

            if (type == CommandType.INVALID) {
                System.out.println("Invalid command");
            }
        }

        scanner.close();
    }
}