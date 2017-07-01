package it.polimi.ingsw.ps45.controller.command;

import it.polimi.ingsw.ps45.controller.Connection;
import it.polimi.ingsw.ps45.controller.SocketObserver;

/**
 * Command that tells the gameCreator to reconnect the player with playerID to the server.
 */
public class ReconnectCommand implements Command{

	/**
 	 * 
 	 * @param  connection the players Connection used to get a reference to the gameCreator
 	 * @param  playerID the id of the player that "executes the command"
	 */
	@Override
	public void run(Connection connection, String playerID) {
		try {
			connection.getGameCreator().reconnect(playerID, new SocketObserver(connection.getOutputStreamWriter()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
