package it.polimi.ingsw.ps45.view.gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class GamePanel extends JPanel{
	
	private final static int DEFAULT_WIDTH = 720;
	private final static int DEFAULT_HEIGHT = 987;
	
	public GamePanel(){
		super();
		this.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		this.setVisible(true);
		this.setBackground(Color.BLACK);
	}

}
