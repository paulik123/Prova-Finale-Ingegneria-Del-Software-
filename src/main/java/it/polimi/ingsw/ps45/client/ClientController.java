package it.polimi.ingsw.ps45.client;

import java.io.IOException;

/**
 * Interface that specifies what methods a client controller should have.
 */
public interface ClientController {
	
	/**
	 * Method that sends serialized command to the server. 
	 * @param message the message that will be sent.
	 */
	public void send(String message) throws IOException;
	
	/**
	 * Send a AddPlayerCommand to the server without waiting for the user's input.
	 * @param the bonusTile that the player chose.
	 */
	public void sendJoinCommand(String bonusTile);
	
	/**
	 * Writes a ReconnectCommand to the server without waiting for the user's input.
	 */
	public void sendReconnectCommand();

}
