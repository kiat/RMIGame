package edu.utexas.cs.cs378;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 * Client for the RMI Guess-the-Number game.
 * <p>
 * Students should only modify to lines marked with TODO:
 */
public class GuessClient {

    public static int PORT = 1099;
    static public String serverHost = "127.0.0.1";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // -------------------------------------------------
            // Connect to RMI registry
            // -------------------------------------------------

            if (args.length >= 1) {
                System.err.println("Usage: GuessClient <serverIP> <port number> ");
                serverHost = args[0];
                PORT = Integer.parseInt(args[1]);
            }

            System.out.println("Connecting to server: " + serverHost);

            Registry registry = LocateRegistry.getRegistry(serverHost, PORT);

            // Look up the remote object
            GuessGame game = (GuessGame) registry.lookup("GuessGame");

            System.out.println("Connected to RMI server!");
            System.out.println();

            // -------------------------------------------------
            // Join the game
            // -------------------------------------------------

            System.out.print("Enter your player name: ");
            String playerName = scanner.nextLine();

            String response = game.joinGame(playerName);

            System.out.println();
            System.out.println("Server: " + response);
            System.out.println();

            // -------------------------------------------------
            // Game loop
            // -------------------------------------------------

            boolean playing = true;

            while (playing) {

                System.out.println("---------------------------------");
                System.out.println("1. Make a guess");
                System.out.println("2. Game status");
                System.out.println("3. Quit");
                System.out.println("---------------------------------");

                System.out.print("Choose an option: ");

                String option = scanner.nextLine();

                if (option.equals("1")) {

                    System.out.print("Enter your guess (1-100): ");

                    int guess = Integer.parseInt(scanner.nextLine());

                    // send the player name and guess to the server and make a guess
                    // get the new response from the server
                    // TODO:
                    // response = ???

                    System.out.println();
                    System.out.println("Server: " + response);

                } else if (option.equals("2")) {

                    // check the game status
                    // get the new response from the server
                    // TODO:
                    // response = ???

                    System.out.println();
                    System.out.println("Server:");
                    System.out.println(response);

                } else if (option.equals("3")) {
                    System.out.println("Goodbye, " + playerName + "!");
                    playing = false;

                } else {

                    System.out.println("Invalid option.");

                }
            }

        } catch (Exception e) {

            System.err.println();
            System.err.println("Client exception:");
            e.printStackTrace();

        } finally {
            scanner.close();
        }
    }
}