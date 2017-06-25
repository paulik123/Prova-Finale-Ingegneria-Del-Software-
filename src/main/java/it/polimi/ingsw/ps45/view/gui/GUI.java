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

public class GUI extends View{
	
	private GameBoard gameBoard;
	
	private PlayerBoard playerBoard;
	
	private ControlBoard controlBoard;
	
	private LeaderBoard leaderBoard;
	
	private GUIWindowListener gameBoardListener;
	private GUIWindowListener playerBoardListener;
	private GUIWindowListener controlBoardListener;
	private GUIWindowListener leaderBoardListener;
	
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

	@Override
	public void updateView(String gameJSON) {
		Gson gson = GsonWithInterface.getGson();
		Game g = gson.fromJson(gameJSON, Game.class);
		
		while(!isReady()){
			try {
				System.out.println("Window not ready.. waiting");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
		gameBoard.update(g);
		playerBoard.update(g);
		controlBoard.update(g);
		leaderBoard.update(g);
	}
	
	public void addController(GUIController controller){
		while(!isReady()){
			try {
				System.out.println("Window not ready.. waiting");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		controlBoard.getSendCommandButton().addActionListener(controller);
	}

	public GameBoard getGameBoard() {
		return gameBoard;
	}

	public PlayerBoard getPlayerBoard() {
		return playerBoard;
	}

	public ControlBoard getControlBoard() {
		return controlBoard;
	}
	
	

	public LeaderBoard getLeaderBoard() {
		return leaderBoard;
	}

	@Override
	public void showError(String error) {
		leaderBoard.updateTextArea(error);
	}
	
	public boolean isReady(){
		return gameBoardListener.isReady() && playerBoardListener.isReady() && controlBoardListener.isReady() && leaderBoardListener.isReady();
	}

	@Override
	public void showResults(String results) {
		leaderBoard.updateTextArea(results);
		
	}

}
