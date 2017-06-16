package it.polimi.ingsw.ps45.view.gui;

import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.polimi.ingsw.ps45.gson.GsonWithInterface;
import it.polimi.ingsw.ps45.model.game.Game;
import it.polimi.ingsw.ps45.view.gui.windowb.ControlBoard;
import it.polimi.ingsw.ps45.view.gui.windowb.GameBoard;
import it.polimi.ingsw.ps45.view.gui.windowb.PlayerBoard;

public class GUImainTest {
	
	static Game g;
	public static void main(String[] args) {
		
		Gson gson = GsonWithInterface.getGson(); 
		try {
			g = gson.fromJson(new FileReader("game.json"), Game.class);
		} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameBoard gameBoard = new GameBoard(g);
					gameBoard.setVisible(true);
					
					PlayerBoard playerBoard = new PlayerBoard(g);
					playerBoard.setVisible(true);
					
					ControlBoard controlBoard = new ControlBoard(g);
					controlBoard.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
