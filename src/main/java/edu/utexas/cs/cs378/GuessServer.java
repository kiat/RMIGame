package edu.utexas.cs.cs378;



import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * RMI server for the Guess-the-Number game.
 *
 */
public class GuessServer {

    public static int PORT = 1099;
    public static String serverIP = "127.0.0.1";


    public static void main(String[] args) {


        if (args.length >= 1) {
            System.err.println("Usage: GuessServer serverIP <port number> ");
            PORT = Integer.parseInt(args[1]);
            serverIP = args[0];
        }

        try {

            System.out.println("=================================");
            System.out.println("   RMI GUESSING GAME SERVER");
            System.out.println("=================================");

            // Tell RMI what address clients should use
            System.setProperty("java.rmi.server.hostname", serverIP);


            // Create the remote game object
            GuessGameImpl game = new GuessGameImpl();

            // Start RMI registry
            Registry registry = LocateRegistry.createRegistry(PORT);

            // Register the remote object
            registry.rebind("GuessGame", game);

            System.out.println("RMI Registry started on port " + PORT);
            System.out.println("Game server is ready!");
            System.out.println("Waiting for players...");
            System.out.println();


            // Keep the server running
            synchronized (GuessServer.class) {
                GuessServer.class.wait();
            }

        } catch (Exception e) {

            System.err.println("Server exception:");
            e.printStackTrace();
        }
    }
}