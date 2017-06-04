package it.polimi.ingsw.ps45.view.gui;

import javax.swing.SwingUtilities;

import it.polimi.ingsw.ps45.view.View;

public class GUIView extends View{
	
	public GUIView(){
		SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	        	GUIBoard board = new GUIBoard();
	        }
	    });
		
		SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	        	GUIPlayerBoard playerBoard = new GUIPlayerBoard();
	        }
	    });
		
	}
	

	@Override
	public String getCommand() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateView(String gameJSON) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
