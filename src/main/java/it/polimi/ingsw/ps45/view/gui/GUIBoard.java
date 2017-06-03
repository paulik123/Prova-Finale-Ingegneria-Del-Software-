package it.polimi.ingsw.ps45.view.gui;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

@SuppressWarnings("serial")
public class GUIBoard extends JFrame{
	
	private GamePanel myGamePanel;
	
	public GUIBoard(){
		super("Lorenzo Il Magnifico");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myGamePanel = new GamePanel();
		start();
	}
	
	public void start(){
		//add(myGamePanel);
        BackgroundPane background = new BackgroundPane();
        try {
			background.setBackground(ImageIO.read(new File("images/gameboard_f_c.jpeg")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        setContentPane(background);
		pack();
		setVisible(true);
	}
	
	


}
