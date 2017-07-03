package it.polimi.ingsw.ps45.model.game;

/**
 * Response that contains the serialized object of the current state of the game. Used for the Visitor design pattern. 
 */
public class GameUpdateResponse implements ServerResponse{
	
	private String data;
	
	/**
 	 * Constructor
 	 * @param data the gson serialized game.
	 */
	public GameUpdateResponse(String data){
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
	 * @return the serialized data as a gson string.
	 */
	@Override
	public String getData() {
		return data;
	}

}
