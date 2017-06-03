package it.polimi.ingsw.ps45.model.game;

public class GameUpdateResponse implements ServerResponse{
	
	private String data;
	
	public GameUpdateResponse(String data){
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
