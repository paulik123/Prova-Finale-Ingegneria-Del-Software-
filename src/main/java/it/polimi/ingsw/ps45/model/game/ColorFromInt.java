package it.polimi.ingsw.ps45.model.game;

public class ColorFromInt {
	
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
