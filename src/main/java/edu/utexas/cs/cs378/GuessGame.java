package edu.utexas.cs.cs378;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Remote interface for the RMI Guess-the-Number game.
 *
 * Students should NOT modify this file.
 */
public interface GuessGame extends Remote {

    /**
     * Join the game.
     *
     * @param playerName name of the player
     * @return welcome message
     */
    String joinGame(String playerName) throws RemoteException;

    /**
     * Submit a guess.
     *
     * @param playerName name of the player
     * @param guess player's guess
     * @return result of the guess
     */
    String makeGuess(String playerName, int guess) throws RemoteException;

    /**
     * Get the current game status.
     *
     * @return status message
     */
    String getGameStatus() throws RemoteException;
}