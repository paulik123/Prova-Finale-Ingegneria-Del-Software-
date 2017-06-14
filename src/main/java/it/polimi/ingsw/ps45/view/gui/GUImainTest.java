package it.polimi.ingsw.ps45.view.gui;

import java.awt.EventQueue;

import it.polimi.ingsw.ps45.view.gui.windowb.ControlBoard;
import it.polimi.ingsw.ps45.view.gui.windowb.GameBoard;
import it.polimi.ingsw.ps45.view.gui.windowb.PlayerBoard;

public class GUImainTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameBoard gameBoard = new GameBoard();
					gameBoard.setVisible(true);
					
					PlayerBoard playerBoard = new PlayerBoard();
					playerBoard.setVisible(true);
					
					ControlBoard controlBoard = new ControlBoard();
					controlBoard.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
