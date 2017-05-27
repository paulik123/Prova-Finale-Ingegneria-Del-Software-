package it.polimi.ingsw.ps45.controller.command;

import it.polimi.ingsw.ps45.controller.GameCreator;

public class CommandHolder {
	private Command c;
	public CommandHolder(Command c){
		this.c = c;
	}
	
	public void runCommand(GameCreator gc){
		c.run(gc);
	}
	
}
