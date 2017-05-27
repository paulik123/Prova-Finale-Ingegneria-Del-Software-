package it.polimi.ingsw.ps45.controller.command;

import it.polimi.ingsw.ps45.controller.Connection;
import it.polimi.ingsw.ps45.controller.GameCreator;

public class CommandHolder {
	private String playerID;
	
	private Command c;
	public CommandHolder(Command c, String playerID){
		this.c = c;
		this.playerID = playerID;
	}
	
	public void runCommand(Connection connection){
		c.run(connection);
	}
	
}
