package it.polimi.ingsw.ps45.view.gui.windowb;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import it.polimi.ingsw.ps45.view.gui.BackgroundPane;

import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import javax.swing.JLayeredPane;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;
import javax.swing.SwingConstants;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JLayeredPane layeredPane;
	private JPanel frontPanel;
	
	
	private JLabel territory_third;
	private JLabel territory_second;
	private JLabel territory_first;
	private JLabel territory_ground;
	
	
	private static final String imagePath = "images/gameboard_f_c.jpeg";
	
	private static final int width = 525;
	private static final int height = 720;
	private static final int cardWidth = 50;
	private static final int cardHeight = 75;
	
	private static final int territoryX = 56;
	private static final int characterX = 148;
	private static final int buildingX = 240;
	private static final int ventureX = 332;
	
	private static final int thirdFloorY = 60;
	private static final int secondFloorY = 145;
	private static final int firstFloorY = 230;
	private static final int groundFloorY = 315;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setResizable(false);
		setTitle("Lorenzo il Magnifico");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, width, height);
		contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		setBackground();
		setFrontPanel();
		initializeTerritories();
	}
	
	public void setBackground(){
		
		layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);
		JPanel background = new JPanel();
		background.setBounds(0, 0, width, height);
		layeredPane.add(background);
		background.setLayout(new BorderLayout(0, 0));
		
		JLabel backgroundLabel = new JLabel("");

		
		background.add(backgroundLabel, BorderLayout.CENTER);
		ImageIcon imageIcon = new ImageIcon("images\\gameboard_f_c.jpeg"); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);  // transform it back
		backgroundLabel.setIcon(imageIcon);
		

		

		

	}
	public void setFrontPanel(){
		frontPanel = new JPanel();
		frontPanel.setOpaque(false);
		layeredPane.setLayer(frontPanel, 1);
		frontPanel.setBounds(0, 0, width, height);
		layeredPane.add(frontPanel);
		frontPanel.setLayout(null);
	}
	
	public void initializeTerritories(){
		territory_third = initializeCardLabel(ventureX, groundFloorY, "devcards_f_en_c_1");
	}
	
	public JLabel initializeCardLabel(int x, int y, String name){
		ImageIcon imageIcon = new ImageIcon("images\\cards\\" + name + ".png"); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(cardWidth, cardHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);
		
		
		JLabel newLabel = new JLabel("");
		newLabel.setBounds(x, y, cardWidth, cardHeight);
		newLabel.setIcon(imageIcon);
		frontPanel.add(newLabel);
		return newLabel;
	}
}
