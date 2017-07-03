package it.polimi.ingsw.ps45.model.game;


/**
 * Response that contains the message of an error. Used for the Visitor design pattern. 
 */
public class ErrorResponse implements ServerResponse{
	
	private String data;
	

	/**
 	 * Constructor
 	 * @param data the error message.
	 */
	public ErrorResponse(String data){
		this.data = data;
	}

	/**
 	 * Accept method used in the visitor design pattern. 
 	 * @param visitor the visitor that reads the message.
	 */
	@Override
	public void accept(ServerResponseVisitor visitor) {
		visitor.visit(this);
	}

	/**
	 * @return the error message as a string.
	 */
	@Override
	public String getData() {
		return data;
	}

}
