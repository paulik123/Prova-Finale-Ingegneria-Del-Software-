package it.polimi.ingsw.ps45.model.game;

public class ErrorResponse implements ServerResponse{
	
	private String data;
	
	public ErrorResponse(String data){
		this.data = data;
	}

	@Override
	public void accept(ServerResponseVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public String getData() {
		return data;
	}

}
