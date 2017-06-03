package it.polimi.ingsw.ps45.model.game;

public class ServerResponseWrapper {
	 private ServerResponse response;
	
	public ServerResponseWrapper(ServerResponse response){
		this.response = response;
	}

	public ServerResponse getResponse() {
		return response;
	}

	public void setResponse(ServerResponse response) {
		this.response = response;
	}
	
	
}
