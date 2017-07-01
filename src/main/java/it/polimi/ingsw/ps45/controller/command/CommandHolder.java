package it.polimi.ingsw.ps45.controller.command;

import it.polimi.ingsw.ps45.controller.Connection;

/**
 * Wrapper class so GSON knows in what class it should deserialize the commands.
 */

public class CommandHolder {
	private String playerID;
	
	private Command c;
	
	/**
 	 * Constructor
	 * @param  c The actual command the CommandHolder holds.
	 * @param  playerID the id of the playerd that "executes the command".
	 */
	public CommandHolder(Command c, String playerID){
		this.c = c;
		this.playerID = playerID;
	}
	
	/**
 	 * The method which executes the command
	 * @param  connection the connection of the server with the player so it can get the gameCreator.
	 */
	public void runCommand(Connection connection){
		c.run(connection, playerID);
	}
	
}
