package it.polimi.ingsw.ps45.model.game;

public interface ServerResponseVisitor {
    void visit(GameUpdateResponse gameUpdateResponse);
    void visit(ErrorResponse errorResponse);
	void visit(ResultsResponse resultsResponse);
}
