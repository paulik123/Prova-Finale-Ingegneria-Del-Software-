package it.polimi.ingsw.ps45.model.player;

public class Pawn {
	private int value;
	private boolean available;
	private PawnType type;
	
	public Pawn(int value, boolean available, PawnType type){
		this.type = type;
		this.value = value;
		this.available = available;
	}
	
	public PawnType getType(){
		return type;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	
}
