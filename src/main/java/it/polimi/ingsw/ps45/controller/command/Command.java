package it.polimi.ingsw.ps45.controller.command;

import it.polimi.ingsw.ps45.controller.GameCreator;

public interface Command {
	public void run(GameCreator gameCreator);
}
