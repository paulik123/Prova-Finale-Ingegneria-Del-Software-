package it.polimi.ingsw.ps45.exceptions;

public class ActionNotAllowedException extends Exception{

	/**
 	 * Constructor
	 * @param  s String explaining the error.
	 */
	public ActionNotAllowedException(String s){
		super(s);
	}
}
