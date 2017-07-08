package it.polimi.ingsw.ps45.exceptions;

public class ResourceNotFoundException extends Exception{

	/**
 	 * Constructor
	 * @param  s String explaining the error.
	 */
	public ResourceNotFoundException(String s){
		super(s);
	}
}
