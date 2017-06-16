package it.polimi.ingsw.ps45.view.gui.windowb;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.polimi.ingsw.ps45.gson.GsonWithInterface;
import it.polimi.ingsw.ps45.model.area.PlayerPawnPair;
import it.polimi.ingsw.ps45.model.cards.Card;
import it.polimi.ingsw.ps45.model.cards.Era;
import it.polimi.ingsw.ps45.model.cards.Territory;
import it.polimi.ingsw.ps45.model.game.Game;
import it.polimi.ingsw.ps45.model.player.PawnType;

import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import javax.swing.JLayeredPane;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

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
	
	ArrayList<JLabel> turnMarkers;
	
	
	
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
	
	
	private static final int diceBlackX = 280;
	private static final int diceWhiteX = 325;
	private static final int diceOrangeX = 370;
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
		
		
		setBackground();
		setFrontPanel();
		initializeTurnMarkers();
		initializeTerritories();
		initializeCharacters();
		initializeBuildings();
		initializeVentures();
		initializeExcom();
		initializePawnAreaLabels();
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
	
	public void initializePawnAreaLabels(){
		
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
		diceBlack.setFont(new Font("Arial", Font.PLAIN, 25));
		diceBlack.setForeground(Color.RED);
		diceWhite = initializePawnLabel(diceWhiteX, diceY);
		diceWhite.setFont(new Font("Arial", Font.PLAIN, 25));
		diceWhite.setForeground(Color.RED);
		diceOrange = initializePawnLabel(diceOrangeX, diceY);
		diceOrange.setFont(new Font("Arial", Font.PLAIN, 25));
		diceOrange.setForeground(Color.RED);
		
	}
	
	private void initializeTurnMarkers(){
		turnMarkers = new ArrayList<JLabel>();
		turnMarkers.add(initializePawnLabel(turnMarkerX, turnMarker1Y));
		turnMarkers.add(initializePawnLabel(turnMarkerX, turnMarker2Y));
		turnMarkers.add(initializePawnLabel(turnMarkerX, turnMarker3Y));
		turnMarkers.add(initializePawnLabel(turnMarkerX, turnMarker4Y));
	}
	
	
	
	private void initializeTerritories(){
		int territoryXWithGap = territoryX + cardPawnHorizontalGap;
		
		
		
		territory_third = initializeCardLabel(territoryX, thirdFloorY);
		territory_third_pawn = initializePawnLabel(territoryXWithGap, thirdFloorY + cardPawnVerticalGap);
		
		territory_second = initializeCardLabel(territoryX, secondFloorY);
		territory_second_pawn = initializePawnLabel(territoryXWithGap, secondFloorY + cardPawnVerticalGap);

		territory_first = initializeCardLabel(territoryX, firstFloorY);
		territory_first_pawn = initializePawnLabel(territoryXWithGap, firstFloorY + cardPawnVerticalGap);

		territory_ground = initializeCardLabel(territoryX, groundFloorY);
		territory_ground_pawn = initializePawnLabel(territoryXWithGap, groundFloorY + cardPawnVerticalGap);
	}
	
	public void initializeCharacters(){
		int characterXWithGap = characterX + cardPawnHorizontalGap;
		
		character_third = initializeCardLabel(characterX, thirdFloorY);
		character_third_pawn = initializePawnLabel(characterXWithGap, thirdFloorY + cardPawnVerticalGap);
		
		character_second = initializeCardLabel(characterX, secondFloorY);
		character_second_pawn = initializePawnLabel(characterXWithGap, secondFloorY + cardPawnVerticalGap);
		
		character_first = initializeCardLabel(characterX, firstFloorY);
		character_first_pawn = initializePawnLabel(characterXWithGap, firstFloorY + cardPawnVerticalGap);
		
		character_ground = initializeCardLabel(characterX, groundFloorY);
		character_ground_pawn = initializePawnLabel(characterXWithGap, groundFloorY + cardPawnVerticalGap);
	}
	
	public void initializeBuildings(){
		int buildingXWithGap = buildingX + cardPawnHorizontalGap;
		
		building_third = initializeCardLabel(buildingX, thirdFloorY);
		building_third_pawn = initializePawnLabel(buildingXWithGap, thirdFloorY + cardPawnVerticalGap);
		
		building_second = initializeCardLabel(buildingX, secondFloorY);
		building_second_pawn = initializePawnLabel(buildingXWithGap, secondFloorY + cardPawnVerticalGap);
		
		building_first = initializeCardLabel(buildingX, firstFloorY);
		building_first_pawn = initializePawnLabel(buildingXWithGap, firstFloorY + cardPawnVerticalGap);
		
		building_ground = initializeCardLabel(buildingX, groundFloorY);
		building_ground_pawn = initializePawnLabel(buildingXWithGap, groundFloorY + cardPawnVerticalGap);
	}
	
	public void initializeVentures(){
		int ventureXWithGap = ventureX + cardPawnHorizontalGap;
		
		venture_third = initializeCardLabel(ventureX, thirdFloorY);
		venture_third_pawn = initializePawnLabel(ventureXWithGap, thirdFloorY + cardPawnVerticalGap);
		
		venture_second = initializeCardLabel(ventureX, secondFloorY);
		venture_second_pawn = initializePawnLabel(ventureXWithGap, secondFloorY + cardPawnVerticalGap);
		
		venture_first = initializeCardLabel(ventureX, firstFloorY);
		venture_first_pawn = initializePawnLabel(ventureXWithGap, firstFloorY + cardPawnVerticalGap);
		
		venture_ground = initializeCardLabel(ventureX, groundFloorY);
		venture_ground_pawn = initializePawnLabel(ventureXWithGap, groundFloorY + cardPawnVerticalGap);
	}
	
	public void initializeExcom(){
		excomI = initializeExcomLabel(excomIX, excomIY );
		excomII = initializeExcomLabel(excomIIX, excomIIY);
		excomIII = initializeExcomLabel(excomIIIX, excomIIIY);
	}
	
	public JLabel initializeCardLabel(int x, int y){
		JLabel newLabel = new JLabel("");
		newLabel.setBounds(x, y, cardWidth, cardHeight);
		frontPanel.add(newLabel);
		
		return newLabel;
	}
	
	public JLabel initializePawnLabel(int x, int y){
		JLabel newLabel = new JLabel("");
		newLabel.setBounds(x, y, pawnAreaSize, pawnAreaSize);
		//newLabel.setBackground(new Color(255,0,0,255));
		frontPanel.add(newLabel);
		//newLabel.setOpaque(true);

		return newLabel;
	}
	
	public JLabel initializeExcomLabel(int x, int y){
		JLabel newLabel = new JLabel("");
		newLabel.setBounds(x, y, excomWidth, excomHeight);

		frontPanel.add(newLabel);
		
		return newLabel;
	}
	
	public void updateExcomLabels(){
		setExcomLabelIcon(excomI,g.getVatican().getCard(Era.I).getName());
		setExcomLabelIcon(excomII,g.getVatican().getCard(Era.II).getName());
		setExcomLabelIcon(excomIII,g.getVatican().getCard(Era.III).getName());
	}
	
	public void setExcomLabelIcon(JLabel excomLabel, String name){
		ImageIcon imageIcon = new ImageIcon("images\\excom\\" + name + ".png"); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(excomWidth, excomHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);
		excomLabel.setIcon(imageIcon);
	}
	
	public void updateCardLabels(){
		setCardLabelIcon(territory_third, g.getBoard().getTerritoryTower().getThirdFloor().getTerritory());
		setCardLabelIcon(territory_second, g.getBoard().getTerritoryTower().getSecondFloor().getTerritory());
		setCardLabelIcon(territory_first, g.getBoard().getTerritoryTower().getFirstFloor().getTerritory());
		setCardLabelIcon(territory_ground, g.getBoard().getTerritoryTower().getGroundFloor().getTerritory());
		
		setCardLabelIcon(character_third, g.getBoard().getCharacterTower().getThirdFloor().getCharacter());
		setCardLabelIcon(character_second, g.getBoard().getCharacterTower().getSecondFloor().getCharacter());
		setCardLabelIcon(character_first, g.getBoard().getCharacterTower().getFirstFloor().getCharacter());
		setCardLabelIcon(character_ground, g.getBoard().getCharacterTower().getGroundFloor().getCharacter());
		
		setCardLabelIcon(building_third, g.getBoard().getBuildingTower().getThirdFloor().getBuilding());
		setCardLabelIcon(building_second, g.getBoard().getBuildingTower().getSecondFloor().getBuilding());
		setCardLabelIcon(building_first, g.getBoard().getBuildingTower().getFirstFloor().getBuilding());
		setCardLabelIcon(building_ground, g.getBoard().getBuildingTower().getGroundFloor().getBuilding());
		
		setCardLabelIcon(venture_third, g.getBoard().getVentureTower().getThirdFloor().getVenture());
		setCardLabelIcon(venture_second, g.getBoard().getVentureTower().getSecondFloor().getVenture());
		setCardLabelIcon(venture_first, g.getBoard().getVentureTower().getFirstFloor().getVenture());
		setCardLabelIcon(venture_ground, g.getBoard().getVentureTower().getGroundFloor().getVenture());
	}
	
	public void updatePawnLabels(){
		
		setPawnLabelIcon(productionSmall, g.getBoard().getProductionAreas().getSmall().getOccupants(), 0);
		setPawnLabelIcon(productionBig1,g.getBoard().getProductionAreas().getBig().getOccupants(),0);
		setPawnLabelIcon(productionBig2, g.getBoard().getProductionAreas().getBig().getOccupants(),1);
		setPawnLabelIcon(productionBig3, g.getBoard().getProductionAreas().getBig().getOccupants(),2);
		setPawnLabelIcon(productionBig4, g.getBoard().getProductionAreas().getBig().getOccupants(),3);
		
		setPawnLabelIcon(harvestSmall, g.getBoard().getHarvestAreas().getSmall().getOccupants(), 0);
		setPawnLabelIcon(harvestBig1, g.getBoard().getHarvestAreas().getBig().getOccupants(), 0);
		setPawnLabelIcon(harvestBig2, g.getBoard().getHarvestAreas().getBig().getOccupants(), 1);
		setPawnLabelIcon(harvestBig3, g.getBoard().getHarvestAreas().getBig().getOccupants(), 2);
		setPawnLabelIcon(harvestBig4, g.getBoard().getHarvestAreas().getBig().getOccupants(), 3);
		
		setPawnLabelIcon(coinsMarket, g.getBoard().getCoinsMarketArea().getOccupants(),0);
		setPawnLabelIcon(servantsMarket, g.getBoard().getServantsMarketArea().getOccupants(),0);
		setPawnLabelIcon(militaryCoinsMarket, g.getBoard().getMilitaryAndCoinArea().getOccupants(),0);
		setPawnLabelIcon(councilPrivilegeMarket, g.getBoard().getCouncilPrivilegeMarketArea().getOccupants(),0);
		
		setPawnLabelIcon(councilPalace1, g.getBoard().getCouncilPalaceArea().getOccupants(), 0);
		setPawnLabelIcon(councilPalace2, g.getBoard().getCouncilPalaceArea().getOccupants(), 1);
		setPawnLabelIcon(councilPalace3, g.getBoard().getCouncilPalaceArea().getOccupants(), 2);
		setPawnLabelIcon(councilPalace4, g.getBoard().getCouncilPalaceArea().getOccupants(), 3);
		
		setPawnLabelIcon(territory_third_pawn, g.getBoard().getTerritoryTower().getThirdFloor().getOccupants(), 0);
		setPawnLabelIcon(territory_second_pawn, g.getBoard().getTerritoryTower().getSecondFloor().getOccupants(), 0);
		setPawnLabelIcon(territory_first_pawn, g.getBoard().getTerritoryTower().getFirstFloor().getOccupants(), 0);
		setPawnLabelIcon(territory_ground_pawn, g.getBoard().getTerritoryTower().getGroundFloor().getOccupants(), 0);
		
		setPawnLabelIcon(character_third_pawn, g.getBoard().getCharacterTower().getThirdFloor().getOccupants(), 0);
		setPawnLabelIcon(character_second_pawn, g.getBoard().getCharacterTower().getSecondFloor().getOccupants(), 0);
		setPawnLabelIcon(character_first_pawn, g.getBoard().getCharacterTower().getFirstFloor().getOccupants(), 0);
		setPawnLabelIcon(character_ground_pawn, g.getBoard().getCharacterTower().getGroundFloor().getOccupants(), 0);
		
		setPawnLabelIcon(building_third_pawn, g.getBoard().getBuildingTower().getThirdFloor().getOccupants(), 0);
		setPawnLabelIcon(building_second_pawn, g.getBoard().getBuildingTower().getSecondFloor().getOccupants(), 0);
		setPawnLabelIcon(building_first_pawn, g.getBoard().getBuildingTower().getFirstFloor().getOccupants(), 0);
		setPawnLabelIcon(building_ground_pawn, g.getBoard().getBuildingTower().getGroundFloor().getOccupants(), 0);
		
		setPawnLabelIcon(venture_third_pawn, g.getBoard().getVentureTower().getThirdFloor().getOccupants(), 0);
		setPawnLabelIcon(venture_second_pawn, g.getBoard().getVentureTower().getSecondFloor().getOccupants(), 0);
		setPawnLabelIcon(venture_first_pawn, g.getBoard().getVentureTower().getFirstFloor().getOccupants(), 0);
		setPawnLabelIcon(venture_ground_pawn, g.getBoard().getVentureTower().getGroundFloor().getOccupants(), 0);
		
	}
	

	
	public void updateDiceLabels(){
		HashMap<PawnType, Integer> dices = g.getDices();
		diceBlack.setText(dices.get(PawnType.BLACK).toString());
		diceWhite.setText(dices.get(PawnType.WHITE).toString());
		diceOrange.setText(dices.get(PawnType.ORANGE).toString());
	}
	
	public void updateTurnMarkers(){
		for(int i=0;i<g.getNumberOfPlayers();i++){
			setTurnMarkerLabelIcon(turnMarkers.get(i), g.getColorTurns()[i]);
		}
	}
	
	public void setTurnMarkerLabelIcon(JLabel l, String color){
		ImageIcon imageIcon = new ImageIcon("images\\pawns\\" + color + ".png"); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(pawnAreaSize, pawnAreaSize,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);

		l.setIcon(imageIcon);
	}
	
	public void setCardLabelIcon(JLabel l, Card card){
		if(card != null){
			ImageIcon imageIcon = new ImageIcon("images\\cards\\" + card.getName() + ".png"); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(cardWidth, cardHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);

			l.setIcon(imageIcon);
		}else{
			l.setIcon(null);
		}
	}
	
	public void setPawnLabelIcon(JLabel l, ArrayList<PlayerPawnPair> list, int index){
		
		if(index <= list.size()-1){
			String playerColor = list.get(index).getPlayer().getColor();
			String diceColor = list.get(index).getType().getColor();
			
			
			ImageIcon imageIcon = new ImageIcon("images\\pawns\\" + playerColor+ "-" + diceColor + ".png"); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(pawnAreaSize, pawnAreaSize,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);

			l.setIcon(imageIcon);
		}else{
			l.setIcon(null);
		}
	}
	
	public void update(Game g){
		this.g = g;
		updateDiceLabels();
		updateTurnMarkers();
		updatePawnLabels();
		updateCardLabels();
		updateExcomLabels();
	}

}
