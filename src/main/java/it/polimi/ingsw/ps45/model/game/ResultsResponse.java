package it.polimi.ingsw.ps45.model.game;

import it.polimi.ingsw.ps45.client.ServerResponseVisitor;

/**
 * Response that contains the final results of the game as a String. Used for the Visitor design pattern. 
 */
public class ResultsResponse implements ServerResponse{
	
	private String data;
	
	/**
 	 * Constructor
 	 * @param data the results of the game as a string.
	 */
	public ResultsResponse(String data){
		this.data = data;
	}

	/**
 	 * Accept method used in the visitor design pattern. 
 	 * @param visitor the visitor that reads the update.
	 */
	@Override
	public void accept(ServerResponseVisitor visitor) {
		visitor.visit(this);
	}

	/**
	 * @return the serialized data as astring.
	 */
	@Override
	public String getData() {
		return data;
	}

}
