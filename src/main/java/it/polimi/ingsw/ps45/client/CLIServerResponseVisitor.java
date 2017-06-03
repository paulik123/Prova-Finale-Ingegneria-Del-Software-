package it.polimi.ingsw.ps45.client;

import it.polimi.ingsw.ps45.model.game.ErrorResponse;
import it.polimi.ingsw.ps45.model.game.GameUpdateResponse;
import it.polimi.ingsw.ps45.model.game.ServerResponseVisitor;
import it.polimi.ingsw.ps45.view.View;

public class CLIServerResponseVisitor implements ServerResponseVisitor{
	
	View view;
	
	public CLIServerResponseVisitor(View view){
		this.view = view;
	}

	@Override
	public void visit(GameUpdateResponse gameUpdateResponse) {
		view.updateView(gameUpdateResponse.getData());
		
	}

	@Override
	public void visit(ErrorResponse errorResponse) {
		System.out.println(errorResponse.getData());
		
	}

}
