package it.polimi.ingsw.ps45.view.gui.windowb;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.polimi.ingsw.ps45.gson.GsonWithInterface;
import it.polimi.ingsw.ps45.model.cards.Card;
import it.polimi.ingsw.ps45.model.cards.Era;
import it.polimi.ingsw.ps45.model.cards.Territory;
import it.polimi.ingsw.ps45.model.game.Game;

import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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

public class GameBoard extends JFrame {
	
	private Game g;

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
	
	private JLabel excomI;
	private JLabel excomII;
	private JLabel excomIII;
	
	private JLabel productionSmall;
	private JLabel productionBig1;
	private JLabel productionBig2;
	private JLabel productionBig3;
	private JLabel productionBig4;
	
	private JLabel harvestSmall;
	private JLabel harvestBig1;
	private JLabel harvestBig2;
	private JLabel harvestBig3;
	private JLabel harvestBig4;
	
	private JLabel coinsMarket;
	private JLabel servantsMarket;
	private JLabel militaryCoinsMarket;
	private JLabel councilPrivilegeMarket;
	
	private JLabel councilPalace1;
	private JLabel councilPalace2;
	private JLabel councilPalace3;
	private JLabel councilPalace4;
	
	private JLabel diceBlack;
	private JLabel diceWhite;
	private JLabel diceOrange;
	
	private JLabel turnMarker1;
	private JLabel turnMarker2;
	private JLabel turnMarker3;
	private JLabel turnMarker4;
	
	
	
	private static final String imagePath = "images/gameboard.jpeg";
	
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
	
	private static final int prodHarvestSmallX = 62;
	private static final int prodHarvestBigX = 110;
	private static final int productionY = 578;
	private static final int harvestY = 633;
	
	
	private static final int coinsMarketX = 286;
	private static final int coinsMarketY = 570;
	private static final int servantsMarketX = 330;
	private static final int servantsMarketY = 570;
	private static final int militaryCoinsMarketX = 370;
	private static final int militaryCoinsMarketY = 580;
	private static final int councilPrivilegeMarketX = 400;
	private static final int councilPrivilegeMarketY = 612;
	
	
	private static final int diceBlackX = 275;
	private static final int diceWhiteX = 320;
	private static final int diceOrangeX = 365;
	private static final int diceY = 640;
	
	private static final int turnMarkerX = 393;
	private static final int turnMarker1Y = 380;
	private static final int turnMarker2Y = 405;
	private static final int turnMarker3Y = 430;
	private static final int turnMarker4Y = 455;
	
	private static final int councilPalaceX = 275;
	private static final int councilPalaceY = 410;
	
	
	private static final int excomIX = 115;
	private static final int excomIY = 433;
	
	private static final int excomIIX = 150;
	private static final int excomIIY = 441;
	
	private static final int excomIIIX = 185;
	private static final int excomIIIY = 433;
	
	
	private static final int excomWidth = 35;
	private static final int excomHeight = 65;
	
	
	
	private static final int pawnAreaSize = 20;
	private static final int cardPawnHorizontalGap = 57;
	private static final int cardPawnVerticalGap = 27;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameBoard frame = new GameBoard();
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
	public GameBoard() {
		setResizable(false);
		setTitle("Lorenzo il Magnifico - GameBoard");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, width, height);
		contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		Gson gson = GsonWithInterface.getGson(); 
		try {
			g = gson.fromJson(new FileReader("game.json"), Game.class);
		} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		setBackground();
		setFrontPanel();
		updateTerritories();
		updateCharacters();
		updateBuildings();
		updateVentures();
		updateExcom();
		updatePawnAreaLabels();
		

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
		ImageIcon imageIcon = new ImageIcon("images\\gameboard.jpeg"); // load the image to a imageIcon
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
	
	public void updatePawnAreaLabels(){
		
		productionSmall = initializePawnLabel(prodHarvestSmallX, productionY);
		productionBig1 = initializePawnLabel(prodHarvestBigX, productionY);
		productionBig2 = initializePawnLabel(prodHarvestBigX + pawnAreaSize, productionY);
		productionBig3 = initializePawnLabel(prodHarvestBigX + pawnAreaSize*2, productionY);
		productionBig4 = initializePawnLabel(prodHarvestBigX + pawnAreaSize*3, productionY);
		
		harvestSmall = initializePawnLabel(prodHarvestSmallX, harvestY);
		harvestBig1 = initializePawnLabel(prodHarvestBigX, harvestY);
		harvestBig2 = initializePawnLabel(prodHarvestBigX + pawnAreaSize, harvestY);
		harvestBig3 = initializePawnLabel(prodHarvestBigX + pawnAreaSize*2, harvestY);
		harvestBig4 = initializePawnLabel(prodHarvestBigX + + pawnAreaSize*3, harvestY);
		
		coinsMarket = initializePawnLabel(coinsMarketX, coinsMarketY);
		servantsMarket = initializePawnLabel(servantsMarketX, servantsMarketY);
		militaryCoinsMarket = initializePawnLabel(militaryCoinsMarketX, militaryCoinsMarketY);
		councilPrivilegeMarket = initializePawnLabel(councilPrivilegeMarketX, councilPrivilegeMarketY);
		
		councilPalace1 = initializePawnLabel(councilPalaceX, councilPalaceY);
		councilPalace2 = initializePawnLabel(councilPalaceX + pawnAreaSize, councilPalaceY);
		councilPalace3 = initializePawnLabel(councilPalaceX + pawnAreaSize*2, councilPalaceY);
		councilPalace4 = initializePawnLabel(councilPalaceX + pawnAreaSize*3, councilPalaceY);
		
		diceBlack = initializePawnLabel(diceBlackX, diceY);
		diceWhite = initializePawnLabel(diceWhiteX, diceY);
		diceOrange = initializePawnLabel(diceOrangeX, diceY);
		
		turnMarker1 = initializePawnLabel(turnMarkerX, turnMarker1Y);
		turnMarker2 = initializePawnLabel(turnMarkerX, turnMarker2Y);
		turnMarker3 = initializePawnLabel(turnMarkerX, turnMarker3Y);
		turnMarker4 = initializePawnLabel(turnMarkerX, turnMarker4Y);
		
	}
	
	public void updateTerritories(){
		int territoryXWithGap = territoryX + cardPawnHorizontalGap;
		
		
		
		territory_third = initializeCardLabel(territoryX, thirdFloorY, g.getBoard().getTerritoryTower().getThirdFloor().getTerritory());
		territory_third_pawn = initializePawnLabel(territoryXWithGap, thirdFloorY + cardPawnVerticalGap);
		
		territory_second = initializeCardLabel(territoryX, secondFloorY, g.getBoard().getTerritoryTower().getSecondFloor().getTerritory());
		territory_second_pawn = initializePawnLabel(territoryXWithGap, secondFloorY + cardPawnVerticalGap);

		territory_first = initializeCardLabel(territoryX, firstFloorY, g.getBoard().getTerritoryTower().getFirstFloor().getTerritory());
		territory_first_pawn = initializePawnLabel(territoryXWithGap, firstFloorY + cardPawnVerticalGap);

		territory_ground = initializeCardLabel(territoryX, groundFloorY, g.getBoard().getTerritoryTower().getGroundFloor().getTerritory());
		territory_ground_pawn = initializePawnLabel(territoryXWithGap, groundFloorY + cardPawnVerticalGap);
	}
	
	public void updateCharacters(){
		int characterXWithGap = characterX + cardPawnHorizontalGap;
		
		character_third = initializeCardLabel(characterX, thirdFloorY, g.getBoard().getCharacterTower().getThirdFloor().getCharacter());
		character_third_pawn = initializePawnLabel(characterXWithGap, thirdFloorY + cardPawnVerticalGap);
		
		character_second = initializeCardLabel(characterX, secondFloorY, g.getBoard().getCharacterTower().getSecondFloor().getCharacter());
		character_second_pawn = initializePawnLabel(characterXWithGap, secondFloorY + cardPawnVerticalGap);
		
		character_first = initializeCardLabel(characterX, firstFloorY, g.getBoard().getCharacterTower().getFirstFloor().getCharacter());
		character_first_pawn = initializePawnLabel(characterXWithGap, firstFloorY + cardPawnVerticalGap);
		
		character_ground = initializeCardLabel(characterX, groundFloorY, g.getBoard().getCharacterTower().getGroundFloor().getCharacter());
		character_ground_pawn = initializePawnLabel(characterXWithGap, groundFloorY + cardPawnVerticalGap);
	}
	
	public void updateBuildings(){
		int buildingXWithGap = buildingX + cardPawnHorizontalGap;
		
		building_third = initializeCardLabel(buildingX, thirdFloorY, g.getBoard().getBuildingTower().getThirdFloor().getBuilding());
		building_third_pawn = initializePawnLabel(buildingXWithGap, thirdFloorY + cardPawnVerticalGap);
		
		building_second = initializeCardLabel(buildingX, secondFloorY, g.getBoard().getBuildingTower().getSecondFloor().getBuilding());
		building_second_pawn = initializePawnLabel(buildingXWithGap, secondFloorY + cardPawnVerticalGap);
		
		building_first = initializeCardLabel(buildingX, firstFloorY, g.getBoard().getBuildingTower().getFirstFloor().getBuilding());
		building_first_pawn = initializePawnLabel(buildingXWithGap, firstFloorY + cardPawnVerticalGap);
		
		building_ground = initializeCardLabel(buildingX, groundFloorY, g.getBoard().getBuildingTower().getGroundFloor().getBuilding());
		building_ground_pawn = initializePawnLabel(buildingXWithGap, groundFloorY + cardPawnVerticalGap);
	}
	
	public void updateVentures(){
		int ventureXWithGap = ventureX + cardPawnHorizontalGap;
		
		venture_third = initializeCardLabel(ventureX, thirdFloorY, g.getBoard().getVentureTower().getThirdFloor().getVenture());
		venture_third_pawn = initializePawnLabel(ventureXWithGap, thirdFloorY + cardPawnVerticalGap);
		
		venture_second = initializeCardLabel(ventureX, secondFloorY, g.getBoard().getVentureTower().getSecondFloor().getVenture());
		venture_second_pawn = initializePawnLabel(ventureXWithGap, secondFloorY + cardPawnVerticalGap);
		
		venture_first = initializeCardLabel(ventureX, firstFloorY, g.getBoard().getVentureTower().getFirstFloor().getVenture());
		venture_first_pawn = initializePawnLabel(ventureXWithGap, firstFloorY + cardPawnVerticalGap);
		
		venture_ground = initializeCardLabel(ventureX, groundFloorY, g.getBoard().getVentureTower().getGroundFloor().getVenture());
		venture_ground_pawn = initializePawnLabel(ventureXWithGap, groundFloorY + cardPawnVerticalGap);
	}
	
	public void updateExcom(){
		excomI = initializeExcomLabel(excomIX, excomIY, g.getVatican().getCard(Era.I).getName());
		excomII = initializeExcomLabel(excomIIX, excomIIY, g.getVatican().getCard(Era.II).getName());
		excomIII = initializeExcomLabel(excomIIIX, excomIIIY, g.getVatican().getCard(Era.III).getName());
	}
	
	public JLabel initializeCardLabel(int x, int y, Card card){
		
		JLabel newLabel = new JLabel("");
		newLabel.setBounds(x, y, cardWidth, cardHeight);
		
		if(card != null){
			ImageIcon imageIcon = new ImageIcon("images\\cards\\" + card.getName() + ".png"); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(cardWidth, cardHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);

			newLabel.setIcon(imageIcon);
		}

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
		newLabel.setBackground(new Color(255,0,0,255));
		//newLabel.setIcon(imageIcon);
		frontPanel.add(newLabel);
		newLabel.setOpaque(true);
		return newLabel;
	}
	
	public JLabel initializeExcomLabel(int x, int y, String name){
		ImageIcon imageIcon = new ImageIcon("images\\excom\\" + name + ".png"); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(excomWidth, excomHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);
		
		
		JLabel newLabel = new JLabel("");
		newLabel.setBounds(x, y, excomWidth, excomHeight);
		newLabel.setIcon(imageIcon);
		frontPanel.add(newLabel);
		
		return newLabel;
	}
	
}
