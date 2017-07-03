package it.polimi.ingsw.ps45.model.game;


/**
 * A server response wrapper so GSON knows to which class it should deserialize the response.
 */
public class ServerResponseWrapper {
	 private ServerResponse response;
	
		/**
	 	 * Constructor
	 	 * @param response the response the wrapper will contain.
		 */
	public ServerResponseWrapper(ServerResponse response){
		this.response = response;
	}

	/**
	 * @return the response contained in the wrapper.
	 */
	public ServerResponse getResponse() {
		return response;
	}

	/**
	 * @param the response that will be "placed" in the wrapper.
	 */
	public void setResponse(ServerResponse response) {
		this.response = response;
	}
	
	
}
