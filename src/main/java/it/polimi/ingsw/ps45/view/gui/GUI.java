package it.polimi.ingsw.ps45.view.gui;

import java.awt.EventQueue;
import java.awt.event.ActionListener;

import com.google.gson.Gson;

import it.polimi.ingsw.ps45.gson.GsonWithInterface;
import it.polimi.ingsw.ps45.model.game.Game;
import it.polimi.ingsw.ps45.view.View;
import it.polimi.ingsw.ps45.view.gui.windowb.ControlBoard;
import it.polimi.ingsw.ps45.view.gui.windowb.GameBoard;
import it.polimi.ingsw.ps45.view.gui.windowb.LeaderBoard;
import it.polimi.ingsw.ps45.view.gui.windowb.PlayerBoard;

/**
 * Main class of the GUI view. It is currently composed of 4 frames because a lot of screen space is needed to show all the information of the game.
 */
public class GUI extends View{
	
	private GameBoard gameBoard;
	
	private PlayerBoard playerBoard;
	
	private ControlBoard controlBoard;
	
	private LeaderBoard leaderBoard;

	/**
 	 * Constructor
 	 * Initializes the required frames.
 	 * @param playerID the ID of the player controlling the view.
	 */
	public GUI(String playerID){

		EventQueue.invokeLater(new Runnable(){

			@Override
			public void run() {
				gameBoard = new GameBoard();
				gameBoard.setVisible(true);
				
				playerBoard = new PlayerBoard(playerID);
				playerBoard.setVisible(true);
				
				controlBoard = new ControlBoard(playerID);
				controlBoard.setVisible(true);
				
				leaderBoard = new LeaderBoard(playerID);
				leaderBoard.setVisible(true);
			}
			
		});

	}

	/**
	 * Update the view when the game state changes.
	 * It waits for all the frames to be initialized and ready.
	 * @param gameJSON the game serialized as a JSON string.
	 */
	@Override
	public void updateView(String gameJSON) {
		Gson gson = GsonWithInterface.getGson();
		Game g = gson.fromJson(gameJSON, Game.class);
		
		
		EventQueue.invokeLater(new Runnable(){

			@Override
			public void run() {
				gameBoard.update(g);
				playerBoard.update(g);
				controlBoard.update(g);
				leaderBoard.update(g);
			}
			
		});
	}
	
	/**
	 * Adds a controller to the view so that the user can send commands to the server.
	 * @param controller the controller that will send commands to the server.
	 */
	public void addListener(ActionListener controller){
		
		EventQueue.invokeLater(new Runnable(){
			@Override
			public void run() {
				controlBoard.getSendCommandButton().addActionListener(controller);
			}
			
		});
		
	}

	/**
	 * @return the frame showing the game board.
	 */
	public GameBoard getGameBoard() {
		return gameBoard;
	}

	/**
	 * @return the frame showing the player board.
	 */
	public PlayerBoard getPlayerBoard() {
		return playerBoard;
	}

	/**
	 * @return the frame showing the control board.
	 */
	public ControlBoard getControlBoard() {
		return controlBoard;
	}
	
	

	/**
	 * @return the frame showing the leader board.
	 */
	public LeaderBoard getLeaderBoard() {
		return leaderBoard;
	}

	/**
	 * Shows an error message from the server in the leader board.
	 * @param error the error message.
	 */
	@Override
	public void showError(String error) {
		
		EventQueue.invokeLater(new Runnable(){
			@Override
			public void run() {
				leaderBoard.updateTextArea(error);
			}
			
		});
	}
	

	/**
	 * Show the end game results received from the server in the leader board.
	 * @param results a string containing the results.
	 */
	@Override
	public void showResults(String results) {
		leaderBoard.updateTextArea(results);
		
	}

}
