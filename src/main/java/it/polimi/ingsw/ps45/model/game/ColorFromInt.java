package it.polimi.ingsw.ps45.model.game;

/**
 * Adapter class that gives the player a "color" based on how many players there are in the game.
 */
public class ColorFromInt {
	
	/**
	 *@param players the Nth position of the player in the game.
	 */
	public static String getColor(int players){
		switch(players){
			case 0: return "red";
			case 1: return "blue";
			case 2: return "green";
			case 3: return "yellow";
			default: return "red";
		}

	}

}
