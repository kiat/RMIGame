package edu.utexas.cs.cs378;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Implementation of the RMI Guess-the-Number game.
 * <p>
 * The game state is maintained on the server.
 * <p>
 * Multiple clients can connect to the same remote object.
 */
public class GuessGameImpl extends UnicastRemoteObject implements GuessGame {

    // -------------------------------------------------------
    // Game state
    // -------------------------------------------------------

    // Secret number between 1 and 100
    private int secretNumber;

    // Player name -> number of guesses
    private Map<String, Integer> players;

    // Indicates whether somebody has already won
    private boolean gameOver;

    // Name of the winner
    private String winner;

    // -------------------------------------------------------
    // Constructor
    // -------------------------------------------------------

    public GuessGameImpl() throws RemoteException {
        // Fixed RMI remote-object port
        super(5001);


        players = new HashMap<>();

        generateNewNumber();
        System.out.println("Secret number has been generated.");
        System.out.println("Game is ready for players.");
    }

    // -------------------------------------------------------
    // Generate a new secret number
    // -------------------------------------------------------

    private void generateNewNumber() {

        Random random = new Random();

        secretNumber = random.nextInt(100) + 1;

        gameOver = false;
        winner = null;
    }

    // -------------------------------------------------------
    // Join the game
    // -------------------------------------------------------

    @Override
    public synchronized String joinGame(String playerName)
            throws RemoteException {

        // Check for empty player name
        if (playerName == null ||
                playerName.trim().isEmpty()) {

            return "Invalid player name.";
        }

        playerName = playerName.trim();

        // Check whether the player already exists
        if (players.containsKey(playerName)) {

            return "Player " + playerName
                    + " is already in the game.";
        }

        // Add player with zero guesses
        players.put(playerName, 0);

        System.out.println("Player joined: " + playerName);

        return "Welcome, " + playerName
                + "! You have joined the game.";
    }

    // -------------------------------------------------------
    // Make a guess
    // -------------------------------------------------------

    @Override
    public synchronized String makeGuess(
            String playerName,
            int guess)
            throws RemoteException {

        // ---------------------------------------------------
        // Check whether player exists
        // ---------------------------------------------------

        if (playerName == null ||
                !players.containsKey(playerName)) {

            return "You must join the game first.";
        }

        // ---------------------------------------------------
        // Check whether game is already over
        // ---------------------------------------------------

        if (gameOver) {

            return "Game over! "
                    + winner
                    + " already won the game.";
        }

        // ---------------------------------------------------
        // Validate the guess
        // ---------------------------------------------------

        if (guess < 1 || guess > 100) {

            return "Invalid guess. "
                    + "Please enter a number between 1 and 100.";
        }

        // ---------------------------------------------------
        // Increment player's guess count
        // ---------------------------------------------------
        int numberOfGuesses = players.get(playerName) + 1;

        players.put(playerName, numberOfGuesses);

        // ---------------------------------------------------
        // Print information on the server
        // ---------------------------------------------------
        System.out.println(playerName + " guessed " + guess);

        // ---------------------------------------------------
        // Compare guess with secret number
        // ---------------------------------------------------
        if (guess < secretNumber) {
            return "Too low! " + "You have made " + numberOfGuesses + " guess(es).";
        } else if (guess > secretNumber) {
            return "Too high! " + "You have made " + numberOfGuesses + " guess(es).";
        } else {
            // ------------------------------------------------
            // Correct answer!
            // ------------------------------------------------
            gameOver = true;
            winner = playerName;

            System.out.println("WINNER: "+ playerName+ " guessed " + secretNumber + " correctly!");
            return "Correct! " + playerName   + " wins the game! " + "Number of guesses: " + numberOfGuesses;
        }
    }

    // -------------------------------------------------------
    // Get game status
    // -------------------------------------------------------
    @Override
    public synchronized String getGameStatus()  throws RemoteException {

        StringBuilder result = new StringBuilder();

        result.append("Players: ");

        if (players.isEmpty()) {
            result.append("No players");
        } else {
            result.append(String.join(", ", players.keySet()));
        }

        result.append("\n");

        // ---------------------------------------------------
        // Game status
        // ---------------------------------------------------
        if (gameOver) {
            result.append("Game status: " + winner+ " won!");
        } else {
            result.append("Game status: In progress");
        }

        // ---------------------------------------------------
        // Player statistics
        // ---------------------------------------------------

        result.append("\n\n");
        result.append("Player Statistics:\n");

        for (Map.Entry<String, Integer> entry: players.entrySet()) {
            result.append(entry.getKey()+ " - "  + entry.getValue() + " guess(es)\n");
        }

        return result.toString();
    }
}