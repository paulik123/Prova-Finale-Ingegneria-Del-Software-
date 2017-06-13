package it.polimi.ingsw.ps45.view.gui.windowb;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import it.polimi.ingsw.ps45.model.game.Game;
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
	
	Game g;

	private JPanel contentPane;
	private JLayeredPane layeredPane;
	private JPanel frontPanel;
	
	
	private JLabel territory_third;
	private JLabel territory_second;
	private JLabel territory_first;
	private JLabel territory_ground;
	
	private JLabel character_third;
	private JLabel character_second;
	private JLabel character_first;
	private JLabel character_ground;
	
	private JLabel building_third;
	private JLabel building_second;
	private JLabel building_first;
	private JLabel building_ground;
	
	private JLabel venture_third;
	private JLabel venture_second;
	private JLabel venture_first;
	private JLabel venture_ground;
	
	private JLabel territory_third_pawn;
	private JLabel territory_second_pawn;
	private JLabel territory_first_pawn;
	private JLabel territory_ground_pawn;
	
	private JLabel character_third_pawn;
	private JLabel character_second_pawn;
	private JLabel character_first_pawn;
	private JLabel character_ground_pawn;
	
	private JLabel building_third_pawn;
	private JLabel building_second_pawn;
	private JLabel building_first_pawn;
	private JLabel building_ground_pawn;
	
	private JLabel venture_third_pawn;
	private JLabel venture_second_pawn;
	private JLabel venture_first_pawn;
	private JLabel venture_ground_pawn;
	
	
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
	
	
	
	private static final int pawnAreaSize = 40;
	private static final int cardPawnHorizontalGap = 47;
	private static final int cardPawnVerticalGap = 17;

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
		initializeCharacters();
		initializeBuildings();
		initializeVentures();
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
		int territoryXWithGap = territoryX + cardPawnHorizontalGap;
		
		
		territory_third = initializeCardLabel(territoryX, thirdFloorY, "Citta");
		territory_third_pawn = initializePawnLabel(territoryXWithGap, thirdFloorY + cardPawnVerticalGap);
		
		territory_second = initializeCardLabel(territoryX, secondFloorY, "Citta");
		territory_second_pawn = initializePawnLabel(territoryXWithGap, secondFloorY + cardPawnVerticalGap);
		
		territory_first = initializeCardLabel(territoryX, firstFloorY, "Citta");
		territory_first_pawn = initializePawnLabel(territoryXWithGap, firstFloorY + cardPawnVerticalGap);
		
		territory_ground = initializeCardLabel(territoryX, groundFloorY, "Citta");
		territory_ground_pawn = initializePawnLabel(territoryXWithGap, groundFloorY + cardPawnVerticalGap);
	}
	
	public void initializeCharacters(){
		int characterXWithGap = characterX + cardPawnHorizontalGap;
		
		character_third = initializeCardLabel(characterX, thirdFloorY, "Citta");
		character_third_pawn = initializePawnLabel(characterXWithGap, thirdFloorY + cardPawnVerticalGap);
		
		character_second = initializeCardLabel(characterX, secondFloorY, "Citta");
		character_second_pawn = initializePawnLabel(characterXWithGap, secondFloorY + cardPawnVerticalGap);
		
		character_first = initializeCardLabel(characterX, firstFloorY, "Citta");
		character_first_pawn = initializePawnLabel(characterXWithGap, firstFloorY + cardPawnVerticalGap);
		
		character_ground = initializeCardLabel(characterX, groundFloorY, "Citta");
		character_ground_pawn = initializePawnLabel(characterXWithGap, groundFloorY + cardPawnVerticalGap);
	}
	
	public void initializeBuildings(){
		int buildingXWithGap = buildingX + cardPawnHorizontalGap;
		
		building_third = initializeCardLabel(buildingX, thirdFloorY, "Citta");
		building_third_pawn = initializePawnLabel(buildingXWithGap, thirdFloorY + cardPawnVerticalGap);
		
		building_second = initializeCardLabel(buildingX, secondFloorY, "Citta");
		building_second_pawn = initializePawnLabel(buildingXWithGap, secondFloorY + cardPawnVerticalGap);
		
		building_first = initializeCardLabel(buildingX, firstFloorY, "Citta");
		building_first_pawn = initializePawnLabel(buildingXWithGap, firstFloorY + cardPawnVerticalGap);
		
		building_ground = initializeCardLabel(buildingX, groundFloorY, "Citta");
		building_ground_pawn = initializePawnLabel(buildingXWithGap, groundFloorY + cardPawnVerticalGap);
	}
	
	public void initializeVentures(){
		int ventureXWithGap = ventureX + cardPawnHorizontalGap;
		
		venture_third = initializeCardLabel(ventureX, thirdFloorY, "Citta");
		venture_third_pawn = initializePawnLabel(ventureXWithGap, thirdFloorY + cardPawnVerticalGap);
		
		venture_second = initializeCardLabel(ventureX, secondFloorY, "Citta");
		venture_second_pawn = initializePawnLabel(ventureXWithGap, secondFloorY + cardPawnVerticalGap);
		
		venture_first = initializeCardLabel(ventureX, firstFloorY, "Citta");
		venture_first_pawn = initializePawnLabel(ventureXWithGap, firstFloorY + cardPawnVerticalGap);
		
		venture_ground = initializeCardLabel(ventureX, groundFloorY, "Citta");
		venture_ground_pawn = initializePawnLabel(ventureXWithGap, groundFloorY + cardPawnVerticalGap);
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
	
	public JLabel initializePawnLabel(int x, int y){
		ImageIcon imageIcon = new ImageIcon("images\\cards\\Citta.png"); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(cardWidth, cardHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);
		
		
		JLabel newLabel = new JLabel("");
		newLabel.setBounds(x, y, pawnAreaSize, pawnAreaSize);
		newLabel.setBackground(new Color(255,255,0,255));
		//newLabel.setIcon(imageIcon);
		frontPanel.add(newLabel);
		newLabel.setOpaque(true);
		return newLabel;
	}
	
}
