package it.polimi.ingsw.ps45.view.gui;

import java.awt.EventQueue;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

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
	
	private GUIWindowListener gameBoardListener;
	private GUIWindowListener playerBoardListener;
	private GUIWindowListener controlBoardListener;
	private GUIWindowListener leaderBoardListener;
	
	
	/**
 	 * Constructor
 	 * Initializes the required frames.
 	 * @param playerID the ID of the player controlling the view.
	 */
	public GUI(String playerID){
		
		gameBoardListener = new GUIWindowListener();
		playerBoardListener = new GUIWindowListener();
		controlBoardListener = new GUIWindowListener();
		leaderBoardListener = new GUIWindowListener();

		
		EventQueue.invokeLater(new Runnable(){

			@Override
			public void run() {
				gameBoard = new GameBoard();
				gameBoard.addWindowListener(gameBoardListener);
				gameBoard.setVisible(true);
				
				playerBoard = new PlayerBoard(playerID);
				playerBoard.addWindowListener(playerBoardListener);
				playerBoard.setVisible(true);
				
				controlBoard = new ControlBoard(playerID);
				controlBoard.addWindowListener(controlBoardListener);
				controlBoard.setVisible(true);
				
				leaderBoard = new LeaderBoard(playerID);
				leaderBoard.addWindowListener(leaderBoardListener);
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
		
		while(!isReady()){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
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
	public void addController(GUIController controller){
		while(!isReady()){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		controlBoard.getSendCommandButton().addActionListener(controller);
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
		leaderBoard.updateTextArea(error);
	}
	
	/**
	 * @return true if all the frames are initialized and ready.
	 */
	public boolean isReady(){
		return gameBoardListener.isReady() && playerBoardListener.isReady() && controlBoardListener.isReady() && leaderBoardListener.isReady();
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
