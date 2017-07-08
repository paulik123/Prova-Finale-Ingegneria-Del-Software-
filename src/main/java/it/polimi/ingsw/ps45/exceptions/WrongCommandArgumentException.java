package it.polimi.ingsw.ps45.exceptions;

public class WrongCommandArgumentException extends Exception{
	
	/**
 	 * Constructor
	 * @param  s String explaining the error.
	 */
	public WrongCommandArgumentException(String s){
		super(s);
	}

}
