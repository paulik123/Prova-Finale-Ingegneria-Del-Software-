package it.polimi.ingsw.ps45.model.player;

public enum PawnType {
	WHITE("white"), BLACK("black"), ORANGE("orange"), NEUTRAL("neutral");
	
	private String type;
	PawnType(String type){
		this.type = type;
	}
	
	public String toString(){
		return type;
	}
}
