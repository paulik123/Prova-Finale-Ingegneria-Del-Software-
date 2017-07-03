package it.polimi.ingsw.ps45.model.player;

/**
 * Enum that lists the existing pawn types.
 */
public enum PawnType {
	WHITE("white"), BLACK("black"), ORANGE("orange"), NEUTRAL("neutral");
	
	private String type;
	
	/**
 	 * Constructor
 	 * @param type the type as a string. Useful for commands.
	 */
	PawnType(String type){
		this.type = type;
	}
	
	/**
	 * @return the type(color) of the pawn type.
	 */
	public String getColor(){
		return type;
	}
}
