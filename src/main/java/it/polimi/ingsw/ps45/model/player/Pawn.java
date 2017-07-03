package it.polimi.ingsw.ps45.model.player;


/**
 * All the logic need to represent a pawn.
 */
public class Pawn {
	private int value;
	private boolean available;
	private PawnType type;
	
	
	/**
 	 * Constructor
	 * @param value the value of the new pawn
	 * @param available true if the pawn can be used.
	 * @param type it's type(color).
	 */
	public Pawn(int value, boolean available, PawnType type){
		this.type = type;
		this.value = value;
		this.available = available;
	}
	
	/**
	 * @return the type of this pawn.
	 */
	public PawnType getType(){
		return type;
	}

	/**
	 * @return true the value of this pawn.
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Sets the new value of this pawn
	 * @param value the new value.
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * @return true if the pawn is available to be used.
	 */
	public boolean isAvailable() {
		return available;
	}

	/**
	 * Sets the availability of this pawn.
	 * @param available the new availability.
	 */
	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	
}
