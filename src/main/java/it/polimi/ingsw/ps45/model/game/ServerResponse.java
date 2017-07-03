package it.polimi.ingsw.ps45.model.game;
/**
 * Interface of a server response message. Used in visitor pattern.
 */
public interface ServerResponse {
	
	/**
	 * @return the serialized data as astring.
	 */
	public String getData();
	
	/**
 	 * Accept method used in the visitor design pattern. 
 	 * @param visitor the visitor that reads the data.
	 */
	void accept(ServerResponseVisitor visitor);
}
