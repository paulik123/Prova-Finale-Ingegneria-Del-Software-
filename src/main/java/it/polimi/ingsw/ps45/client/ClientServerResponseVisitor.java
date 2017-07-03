package it.polimi.ingsw.ps45.client;

import java.awt.EventQueue;

import it.polimi.ingsw.ps45.model.game.ErrorResponse;
import it.polimi.ingsw.ps45.model.game.GameUpdateResponse;
import it.polimi.ingsw.ps45.model.game.ResultsResponse;
import it.polimi.ingsw.ps45.view.View;

/**
 * Reads the responses from the server then notifies the view. Uses the Visitor design pattern.
 */
public class ClientServerResponseVisitor implements ServerResponseVisitor{
	
	private View view;
	
	/**
 	 * Constructor
 	 * @param the view that will be notified with the message.
	 */
	public ClientServerResponseVisitor(View view){
		this.view = view;
	}

	/**
 	 * Visits a GameUpdateResponse then makes the view update with the new game data.
	 */
	@Override
	public void visit(GameUpdateResponse gameUpdateResponse) {
		view.updateView(gameUpdateResponse.getData());
	}

	/**
 	 * Visits a ErrorResponse then makes the view show the error to the user.
	 */
	@Override
	public void visit(ErrorResponse errorResponse) {
		view.showError(errorResponse.getData());
		
	}

	/**
 	 * Visits a ResultsResponse then makes the view show the result to the user.
	 */
	@Override
	public void visit(ResultsResponse resultsResponse) {
		view.showResults(resultsResponse.getData());
		
	}

}
