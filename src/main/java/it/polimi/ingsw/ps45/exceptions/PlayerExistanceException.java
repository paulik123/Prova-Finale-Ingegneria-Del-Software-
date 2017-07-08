package it.polimi.ingsw.ps45.exceptions;

public class PlayerExistanceException extends Exception{

	/**
 	 * Constructor
	 * @param  s String explaining the error.
	 */
	public PlayerExistanceException(String s){
		super(s);
	}
}
