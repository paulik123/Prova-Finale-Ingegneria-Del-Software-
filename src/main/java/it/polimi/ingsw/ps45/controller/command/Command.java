package it.polimi.ingsw.ps45.controller.command;

import it.polimi.ingsw.ps45.controller.Connection;
import it.polimi.ingsw.ps45.controller.GameCreator;

public interface Command {
	public void run(Connection connection, String playerID);
}
