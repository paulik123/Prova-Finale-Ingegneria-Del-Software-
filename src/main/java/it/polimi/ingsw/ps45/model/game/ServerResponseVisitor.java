package it.polimi.ingsw.ps45.model.game;


/**
 * Interface of a server response visitor. Used in visitor pattern.
 */
public interface ServerResponseVisitor {
	/**
	 * Visits a game update response.
	 * @param gameUpdateResponse the response containing the serialized version of the game.
	 */
    void visit(GameUpdateResponse gameUpdateResponse);
    
	/**
	 * Visits a error response.
	 * @param errorResponse the response containing the message of the error.
	 */
    void visit(ErrorResponse errorResponse);
    
	/**
	 * Visits a final results response.
	 * @param resultsResponse the response containing the final results of the game.
	 */
	void visit(ResultsResponse resultsResponse);
}
