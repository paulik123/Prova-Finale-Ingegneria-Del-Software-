package it.polimi.ingsw.ps45.model.game;

public interface ServerResponse {
	public String getData();
	
	void accept(ServerResponseVisitor visitor);
}
