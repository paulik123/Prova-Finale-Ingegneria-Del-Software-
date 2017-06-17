package it.polimi.ingsw.ps45.client;

import java.awt.EventQueue;

import it.polimi.ingsw.ps45.model.game.ErrorResponse;
import it.polimi.ingsw.ps45.model.game.GameUpdateResponse;
import it.polimi.ingsw.ps45.model.game.ServerResponseVisitor;
import it.polimi.ingsw.ps45.view.View;

public class ClientServerResponseVisitor implements ServerResponseVisitor{
	
	View view;
	
	public ClientServerResponseVisitor(View view){
		this.view = view;
	}

	@Override
	public void visit(GameUpdateResponse gameUpdateResponse) {
		view.updateView(gameUpdateResponse.getData());
	}

	@Override
	public void visit(ErrorResponse errorResponse) {
		view.showError(errorResponse.getData());
		
	}

}
