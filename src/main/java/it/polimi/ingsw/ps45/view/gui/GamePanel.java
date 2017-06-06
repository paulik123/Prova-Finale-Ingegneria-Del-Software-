package it.polimi.ingsw.ps45.view.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel{
	
	private final static int DEFAULT_WIDTH = 720;
	private final static int DEFAULT_HEIGHT = 990;
	
	public GamePanel(){
		super();
		this.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		this.setVisible(true);
		setBackground(new Color(255,0,0,10));
		

	}
	
	

}
