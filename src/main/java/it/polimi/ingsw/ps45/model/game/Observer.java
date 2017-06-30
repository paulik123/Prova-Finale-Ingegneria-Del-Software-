package it.polimi.ingsw.ps45.model.game;

import java.io.IOException;

public interface Observer {
	public void notify(String json) throws IOException;
}
