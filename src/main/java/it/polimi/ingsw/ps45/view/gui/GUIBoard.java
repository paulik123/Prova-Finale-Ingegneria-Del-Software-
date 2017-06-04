package it.polimi.ingsw.ps45.view.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;


@SuppressWarnings("serial")
public class GUIBoard extends JFrame{
	
	JPanel myGamePanel;
	JPanel mainPanel;
	
	JLabel card1;
	JLabel card2;
	JLabel card3;
	JLabel card4;
	
	private static final String imagePath = "images/gameboard_f_c.jpeg";
	
	public GUIBoard(){
		super("Lorenzo Il Magnifico");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(0,0,0,0));
		myGamePanel = new JPanel(new GridBagLayout());
		myGamePanel.setBackground(new Color(0,0,0,0));
		
		initializeLabels();
		
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
		getContentPane().add(mainPanel);
		mainPanel.add(myGamePanel);
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		myGamePanel.add(card1, c);
		c.gridy++;
		JLabel horizontalSpace = new JLabel();
		horizontalSpace.setBackground(new Color(0,0,0,0));
		horizontalSpace.setPreferredSize(new Dimension(70,50));
		myGamePanel.add(horizontalSpace, c);
		c.gridy++;
		myGamePanel.add(card2, c);
		pack();
		setVisible(true);
		
		
	}
	
	private void initializeLabels(){
		card1 = new JLabel();
		card1.setPreferredSize(new Dimension(70,112));
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("images/cards/devcards_f_en_c_1.png").getImage().getScaledInstance(70, 112, Image.SCALE_SMOOTH));
		card1.setIcon(imageIcon);
		
		card2 = new JLabel();
		card2.setPreferredSize(new Dimension(70,112));
		imageIcon = new ImageIcon(new ImageIcon("images/cards/devcards_f_en_c_1.png").getImage().getScaledInstance(70, 112, Image.SCALE_SMOOTH));
		card2.setIcon(imageIcon);
		
	}
	
	


}
