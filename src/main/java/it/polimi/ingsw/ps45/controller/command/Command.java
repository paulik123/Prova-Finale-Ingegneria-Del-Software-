package it.polimi.ingsw.ps45.controller.command;

import it.polimi.ingsw.ps45.controller.Connection;

public interface Command {
	public void run(Connection connection, String playerID);
}
