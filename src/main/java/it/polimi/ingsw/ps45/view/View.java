package it.polimi.ingsw.ps45.view;

/**
 * Interface that defines what methods a view of the game should have. 
 */
public abstract class View extends Thread{
	/**
	 * Update the view when the game state changes.
	 * @param gameJSON the game serialized as a JSON string.
	 */
	public abstract void updateView(String gameJSON);
	
	/**
	 * Show an error message from the server.
	 * @param error the error message.
	 */
	public abstract void showError(String error);
	
	/**
	 * Show the end game results received from the server.
	 * @param results a string containing the results.
	 */
	public abstract void showResults(String results);
}
