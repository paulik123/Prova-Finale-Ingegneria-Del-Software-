package it.polimi.ingsw.ps45.view.gui;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.swing.JFrame;


@SuppressWarnings("serial")
public class GUIBoard extends JFrame{
	
	private GamePanel myGamePanel;
	private static final String imagePath = "images/gameboard_f_c.jpeg";
	
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
			background.setBackground(ImageIO.read(new File(imagePath)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        setContentPane(background);
		pack();
		setVisible(true);
	}
	
	


}
