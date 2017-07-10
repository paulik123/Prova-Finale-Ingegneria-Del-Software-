package it.polimi.ingsw.ps45.view.gui.windowb;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import it.polimi.ingsw.ps45.exceptions.AreaNotAvailableException;
import it.polimi.ingsw.ps45.model.area.PlayerPawnPair;
import it.polimi.ingsw.ps45.model.cards.Card;
import it.polimi.ingsw.ps45.model.cards.Era;
import it.polimi.ingsw.ps45.model.game.Game;
import it.polimi.ingsw.ps45.model.player.PawnType;
import it.polimi.ingsw.ps45.model.player.Player;

/**
 * The frame of the GUI that show the game's big board. It's covering the big production and harvest areas if there are only two players.
 * Also covers two of the market areas if there are only 3 players.
 * It uses absolute positioning because some the elements were very hard to arrange properly using other LayoutManagers.
 */
public class GameBoard extends JFrame {
	
	private transient Game g;

	private JPanel contentPane;
	private JLayeredPane layeredPane;
	private JPanel frontPanel;
	private JPanel excomMarkerPanel;
	
	
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
	
	private JLabel productionBigCover;
	private JLabel harvestBigCover;
	
	private JLabel militaryCoinsCover;
	private JLabel councilPrivilegeCover;
	
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
	
	private ArrayList<JLabel> turnMarkers;
	private ArrayList<JLabel> excomIMarkers;
	private ArrayList<JLabel> excomIIMarkers;
	private ArrayList<JLabel> excomIIIMarkers;
	
	
	
	private static final String imagePath = "images/gameboard.jpeg";
	
	private boolean updatedCovers;
	
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
	private static final int productionY = 585;
	private static final int harvestY = 640;
	
	private static final int prodHarvestBigCoverX = 95;
	private static final int productionCoverY = 561;
	private static final int harvestCoverY = 616;
	private static final int coverBigWidth = 112;
	private static final int coverBigHeight = 47;
	
	
	private static final int coinsMarketX = 286;
	private static final int coinsMarketY = 570;
	private static final int servantsMarketX = 330;
	private static final int servantsMarketY = 570;
	private static final int militaryCoinsMarketX = 370;
	private static final int militaryCoinsMarketY = 580;
	private static final int councilPrivilegeMarketX = 400;
	private static final int councilPrivilegeMarketY = 612;
	private static final int militaryCoinsMarketCoverX = 360;
	private static final int militaryCoinsMarketCoverY = 570;
	private static final int councilPrivilegeMarketCoverX = 390;
	private static final int councilPrivilegeMarketCoverY = 597;
	private static final int marketCoverSize = 40;
	
	
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
	private static final int excomMarkerWidth = 5;
	private static final int excomMarkerHeight = 5;
	private static final int excomMarkerXGap1 = 10;
	private static final int excomMarkerXGap2 = 20;
	private static final int excomMarkerYGap1 = -17;
	private static final int excomMarkerYGap2 = -7;
	private static final int excomMarkerTHIRDYGap = 2;
	
	
	
	private static final int pawnAreaSize = 20;
	private static final int cardPawnHorizontalGap = 57;
	private static final int cardPawnVerticalGap = 27;
	


	/**
 	 * Constructor
 	 * Initializes the background and the front content panel on which all the elements are later added.
	 */
	public GameBoard() {
		
		
		setResizable(false);
		setTitle("Lorenzo il Magnifico - GameBoard");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, width, height);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
		setBackground();
		setFrontPanel();
		initializeTerritories();
		initializeCharacters();
		initializeBuildings();
		initializeVentures();
		initializeExcom();
		initializeTurnMarkers();
		initializePawnAreaLabels();
		initializeDiceLabels();
		
	}
	
	/**
	 * Creates a layered pane in which it adds a new background panel. It also adds an image with the board read from file.
	 */
	public void setBackground(){
		
		layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);
		JPanel background = new JPanel();
		background.setBounds(0, 0, width, height);
		layeredPane.add(background);
		background.setLayout(new BorderLayout(0, 0));
		
		JLabel backgroundLabel = new JLabel("");

		
		background.add(backgroundLabel, BorderLayout.CENTER);
		ImageIcon imageIcon = new ImageIcon(imagePath); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);  // transform it back
		backgroundLabel.setIcon(imageIcon);
	}
	
	/**
	 * Initializes the front panel on which all the elements are added. 
	 */
	public void setFrontPanel(){
		frontPanel = new JPanel();
		frontPanel.setOpaque(false);
		layeredPane.setLayer(frontPanel, 1);
		frontPanel.setBounds(0, 0, width, height);
		layeredPane.add(frontPanel);
		frontPanel.setLayout(null);
		
		excomMarkerPanel = new JPanel();
		excomMarkerPanel.setOpaque(false);
		layeredPane.setLayer(excomMarkerPanel, 2);
		excomMarkerPanel.setBounds(0, 0, width, height);
		layeredPane.add(excomMarkerPanel);
		excomMarkerPanel.setLayout(null);
		

	}
	
	/**
	 * Initializes the labels that represent areas in which pawns can stay in.
	 */
	public void initializePawnAreaLabels(){
		
		productionSmall = initializeLabel(prodHarvestSmallX, productionY, pawnAreaSize, pawnAreaSize);
		productionBig1 = initializeLabel(prodHarvestBigX, productionY, pawnAreaSize, pawnAreaSize);
		productionBig2 = initializeLabel(prodHarvestBigX + pawnAreaSize, productionY, pawnAreaSize, pawnAreaSize);
		productionBig3 = initializeLabel(prodHarvestBigX + pawnAreaSize*2, productionY, pawnAreaSize, pawnAreaSize);
		productionBig4 = initializeLabel(prodHarvestBigX + pawnAreaSize*3, productionY, pawnAreaSize, pawnAreaSize);
		
		
		harvestSmall = initializeLabel(prodHarvestSmallX, harvestY, pawnAreaSize, pawnAreaSize);
		harvestBig1 = initializeLabel(prodHarvestBigX, harvestY, pawnAreaSize, pawnAreaSize);
		harvestBig2 = initializeLabel(prodHarvestBigX + pawnAreaSize, harvestY, pawnAreaSize, pawnAreaSize);
		harvestBig3 = initializeLabel(prodHarvestBigX + pawnAreaSize*2, harvestY, pawnAreaSize, pawnAreaSize);
		harvestBig4 = initializeLabel(prodHarvestBigX + pawnAreaSize*3, harvestY, pawnAreaSize, pawnAreaSize);
		
		coinsMarket = initializeLabel(coinsMarketX, coinsMarketY, pawnAreaSize, pawnAreaSize);
		servantsMarket = initializeLabel(servantsMarketX, servantsMarketY, pawnAreaSize, pawnAreaSize);
		militaryCoinsMarket = initializeLabel(militaryCoinsMarketX, militaryCoinsMarketY, pawnAreaSize, pawnAreaSize);
		councilPrivilegeMarket = initializeLabel(councilPrivilegeMarketX, councilPrivilegeMarketY, pawnAreaSize, pawnAreaSize);
		
		councilPalace1 = initializeLabel(councilPalaceX, councilPalaceY, pawnAreaSize, pawnAreaSize);
		councilPalace2 = initializeLabel(councilPalaceX + pawnAreaSize, councilPalaceY, pawnAreaSize, pawnAreaSize);
		councilPalace3 = initializeLabel(councilPalaceX + pawnAreaSize*2, councilPalaceY, pawnAreaSize, pawnAreaSize);
		councilPalace4 = initializeLabel(councilPalaceX + pawnAreaSize*3, councilPalaceY, pawnAreaSize, pawnAreaSize);
		
		
	}
	
	/**
	 * Initializes the labels which show the value of the dices.
	 */
	private void initializeDiceLabels(){
		diceBlack = initializeLabel(diceBlackX, diceY, pawnAreaSize, pawnAreaSize);
		diceBlack.setFont(new Font("Arial", Font.PLAIN, 25));
		diceBlack.setForeground(Color.RED);
		diceWhite = initializeLabel(diceWhiteX, diceY, pawnAreaSize, pawnAreaSize);
		diceWhite.setFont(new Font("Arial", Font.PLAIN, 25));
		diceWhite.setForeground(Color.RED);
		diceOrange = initializeLabel(diceOrangeX, diceY, pawnAreaSize, pawnAreaSize);
		diceOrange.setFont(new Font("Arial", Font.PLAIN, 25));
		diceOrange.setForeground(Color.RED);
	}
	
	/**
	 * Initializes the labels which show the turn order of the players.
	 */
	private void initializeTurnMarkers(){
		turnMarkers = new ArrayList<JLabel>();
		turnMarkers.add(initializeLabel(turnMarkerX, turnMarker1Y, pawnAreaSize, pawnAreaSize));
		turnMarkers.add(initializeLabel(turnMarkerX, turnMarker2Y, pawnAreaSize, pawnAreaSize));
		turnMarkers.add(initializeLabel(turnMarkerX, turnMarker3Y, pawnAreaSize, pawnAreaSize));
		turnMarkers.add(initializeLabel(turnMarkerX, turnMarker4Y, pawnAreaSize, pawnAreaSize));
	}
	
	
	
	/**
	 * Initializes the labels which show territories on the board.
	 */
	private void initializeTerritories(){
		int territoryXWithGap = territoryX + cardPawnHorizontalGap;
		
		
		
		territory_third = initializeLabel(territoryX, thirdFloorY, cardWidth, cardHeight);
		territory_third_pawn = initializeLabel(territoryXWithGap, thirdFloorY + cardPawnVerticalGap, pawnAreaSize, pawnAreaSize);
		
		territory_second = initializeLabel(territoryX, secondFloorY, cardWidth, cardHeight);
		territory_second_pawn = initializeLabel(territoryXWithGap, secondFloorY + cardPawnVerticalGap, pawnAreaSize, pawnAreaSize);

		territory_first = initializeLabel(territoryX, firstFloorY, cardWidth, cardHeight);
		territory_first_pawn = initializeLabel(territoryXWithGap, firstFloorY + cardPawnVerticalGap, pawnAreaSize, pawnAreaSize);

		territory_ground = initializeLabel(territoryX, groundFloorY, cardWidth, cardHeight);
		territory_ground_pawn = initializeLabel(territoryXWithGap, groundFloorY + cardPawnVerticalGap, pawnAreaSize, pawnAreaSize);
	}
	
	/**
	 * Initializes the labels which show characters on the board.
	 */
	public void initializeCharacters(){
		int characterXWithGap = characterX + cardPawnHorizontalGap;
		
		character_third = initializeLabel(characterX, thirdFloorY, cardWidth, cardHeight);
		character_third_pawn = initializeLabel(characterXWithGap, thirdFloorY + cardPawnVerticalGap, pawnAreaSize, pawnAreaSize);
		
		character_second = initializeLabel(characterX, secondFloorY, cardWidth, cardHeight);
		character_second_pawn = initializeLabel(characterXWithGap, secondFloorY + cardPawnVerticalGap, pawnAreaSize, pawnAreaSize);
		
		character_first = initializeLabel(characterX, firstFloorY, cardWidth, cardHeight);
		character_first_pawn = initializeLabel(characterXWithGap, firstFloorY + cardPawnVerticalGap, pawnAreaSize, pawnAreaSize);
		
		character_ground = initializeLabel(characterX, groundFloorY, cardWidth, cardHeight);
		character_ground_pawn = initializeLabel(characterXWithGap, groundFloorY + cardPawnVerticalGap, pawnAreaSize, pawnAreaSize);
	}
	
	/**
	 * Initializes the labels which show buildings on the board.
	 */
	public void initializeBuildings(){
		int buildingXWithGap = buildingX + cardPawnHorizontalGap;
		
		building_third = initializeLabel(buildingX, thirdFloorY, cardWidth, cardHeight);
		building_third_pawn = initializeLabel(buildingXWithGap, thirdFloorY + cardPawnVerticalGap, pawnAreaSize, pawnAreaSize);
		
		building_second = initializeLabel(buildingX, secondFloorY, cardWidth, cardHeight);
		building_second_pawn = initializeLabel(buildingXWithGap, secondFloorY + cardPawnVerticalGap, pawnAreaSize, pawnAreaSize);
		
		building_first = initializeLabel(buildingX, firstFloorY, cardWidth, cardHeight);
		building_first_pawn = initializeLabel(buildingXWithGap, firstFloorY + cardPawnVerticalGap, pawnAreaSize, pawnAreaSize);
		
		building_ground = initializeLabel(buildingX, groundFloorY, cardWidth, cardHeight);
		building_ground_pawn = initializeLabel(buildingXWithGap, groundFloorY + cardPawnVerticalGap, pawnAreaSize, pawnAreaSize);
	}
	
	/**
	 * Initializes the labels which show ventures on the board.
	 */
	public void initializeVentures(){
		int ventureXWithGap = ventureX + cardPawnHorizontalGap;
		
		venture_third = initializeLabel(ventureX, thirdFloorY, cardWidth, cardHeight);
		venture_third_pawn = initializeLabel(ventureXWithGap, thirdFloorY + cardPawnVerticalGap, pawnAreaSize, pawnAreaSize);
		
		venture_second = initializeLabel(ventureX, secondFloorY, cardWidth, cardHeight);
		venture_second_pawn = initializeLabel(ventureXWithGap, secondFloorY + cardPawnVerticalGap, pawnAreaSize, pawnAreaSize);
		
		venture_first = initializeLabel(ventureX, firstFloorY, cardWidth, cardHeight);
		venture_first_pawn = initializeLabel(ventureXWithGap, firstFloorY + cardPawnVerticalGap, pawnAreaSize, pawnAreaSize);
		
		venture_ground = initializeLabel(ventureX, groundFloorY, cardWidth, cardHeight);
		venture_ground_pawn = initializeLabel(ventureXWithGap, groundFloorY + cardPawnVerticalGap, pawnAreaSize, pawnAreaSize);
	}
	
	/**
	 * Initializes the labels which show the excommunication cards on the board.
	 */
	public void initializeExcom(){
		excomI = initializeLabel(excomIX, excomIY, excomWidth, excomHeight);
		excomIMarkers = new ArrayList<JLabel>();
		excomIMarkers.add(initializeExcomMarkerLabel(excomIX + excomMarkerXGap1, excomIY + excomMarkerYGap1, excomWidth, excomHeight));
		excomIMarkers.add(initializeExcomMarkerLabel(excomIX + excomMarkerXGap2, excomIY + excomMarkerYGap1, excomWidth, excomHeight));
		excomIMarkers.add(initializeExcomMarkerLabel(excomIX + excomMarkerXGap1, excomIY + excomMarkerYGap2, excomWidth, excomHeight));
		excomIMarkers.add(initializeExcomMarkerLabel(excomIX + excomMarkerXGap2, excomIY + excomMarkerYGap2, excomWidth, excomHeight));
		
		excomII = initializeLabel(excomIIX, excomIIY, excomWidth, excomHeight);
		excomIIMarkers = new ArrayList<JLabel>();
		excomIIMarkers.add(initializeExcomMarkerLabel(excomIIX + excomMarkerXGap1, excomIIY + excomMarkerYGap1, excomWidth, excomHeight));
		excomIIMarkers.add(initializeExcomMarkerLabel(excomIIX + excomMarkerXGap2, excomIIY + excomMarkerYGap1, excomWidth, excomHeight));
		excomIIMarkers.add(initializeExcomMarkerLabel(excomIIX + excomMarkerXGap1, excomIIY + excomMarkerYGap2, excomWidth, excomHeight));
		excomIIMarkers.add(initializeExcomMarkerLabel(excomIIX + excomMarkerXGap2, excomIIY + excomMarkerYGap2, excomWidth, excomHeight));
		
		excomIII = initializeLabel(excomIIIX, excomIIIY, excomWidth, excomHeight);
		excomIIIMarkers = new ArrayList<JLabel>();
		excomIIIMarkers.add(initializeExcomMarkerLabel(excomIIIX + excomMarkerXGap1, excomIIIY + excomMarkerTHIRDYGap + excomMarkerYGap1, excomWidth, excomHeight));
		excomIIIMarkers.add(initializeExcomMarkerLabel(excomIIIX + excomMarkerXGap2, excomIIIY + excomMarkerTHIRDYGap + excomMarkerYGap1, excomWidth, excomHeight));
		excomIIIMarkers.add(initializeExcomMarkerLabel(excomIIIX + excomMarkerXGap1, excomIIIY + excomMarkerTHIRDYGap + excomMarkerYGap2, excomWidth, excomHeight));
		excomIIIMarkers.add(initializeExcomMarkerLabel(excomIIIX + excomMarkerXGap2, excomIIIY + excomMarkerTHIRDYGap + excomMarkerYGap2, excomWidth, excomHeight));
	}
	
	/**
	 * Creates a new label then adds it to the front panel.
	 * @param x the x position of the label.
	 * @param y the y position of the label.
	 * @param width the width of the label.
	 * @param height the height of the label.
	 * @return the newly created label.
	 */
	public JLabel initializeLabel(int x, int y,int width,int height){
		JLabel newLabel = new JLabel("");
		newLabel.setBounds(x, y, width, height);
		frontPanel.add(newLabel);
		
		return newLabel;
	}
	
	
	/**
	 * Creates a new excom Marker label then adds it to the excom marker panel.
	 * @param x the x position of the label.
	 * @param y the y position of the label.
	 * @param width the width of the label.
	 * @param height the height of the label.
	 * @return the newly created label.
	 */
	public JLabel initializeExcomMarkerLabel(int x, int y,int width,int height){
		JLabel newLabel = new JLabel("");
		newLabel.setBounds(x, y, width, height);
		excomMarkerPanel.add(newLabel);
		
		return newLabel;
	}
	
	/**
	 * Updates the Icons of the Excom Labels with the icons corresponding with the Excom cards in the game model.
	 */
	public void updateExcomLabels(){
		setExcomLabelIcon(excomI,g.getVatican().getCard(Era.I).getName());
		setExcomLabelIcon(excomII,g.getVatican().getCard(Era.II).getName());
		setExcomLabelIcon(excomIII,g.getVatican().getCard(Era.III).getName());
	}
	
	
	/**
	 * Updates the Excom markers to show which players suffered vatican penalties.
	 */
	public void updateExcomMarkerLabesl(){
		ArrayList<Player> players = g.getPlayers();
		
		for(int i=0; i<players.size();i++){
			Player player = players.get(i);
			
			if(player.hasVaticanPenalty(Era.I))setExcomMarkerLabelIcon(excomIMarkers.get(i), players.get(i).getColor());
			if(player.hasVaticanPenalty(Era.II))setExcomMarkerLabelIcon(excomIIMarkers.get(i), players.get(i).getColor());
			if(player.hasVaticanPenalty(Era.III))setExcomMarkerLabelIcon(excomIIIMarkers.get(i), players.get(i).getColor());
		}
	}
	
	/**
	 * Sets the label of an Excom card label with the corresponding icon read from file.
	 * @param excomLabel the label that will be updated.
	 * @param name the name of the Excom card so it can get it's icon from file.
	 */
	public void setExcomLabelIcon(JLabel excomLabel, String name){
		ImageIcon imageIcon = new ImageIcon("images/excom/" + name + ".png"); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(excomWidth, excomHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);
		excomLabel.setIcon(imageIcon);
	}
	
	/**
	 * Sets the label of an Excom card label with the corresponding icon read from file.
	 * @param excom marker label the label that will be updated.
	 * @param color the color  of the Excom marker so it can get it's icon from file.
	 */
	public void setExcomMarkerLabelIcon(JLabel excomLabel, String color){
		ImageIcon imageIcon = new ImageIcon("images/pawns/" + color + ".png"); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(excomMarkerWidth, excomMarkerHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);
		excomLabel.setIcon(imageIcon);
	}
	
	/**
	 * Updates the Icons of the Card Labels with the images corresponding to the cards on the game's board.
	 */
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
	
	/**
	 * Updates the Icons of the Pawn Labels with the images corresponding to the Pawns occupying the areas on the game's board.
	 */
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
		try {
			if(g.getNumberOfPlayers() >= 4){
				setPawnLabelIcon(councilPrivilegeMarket, g.getBoard().getCouncilPrivilegeMarketArea().getOccupants(),0);
				setPawnLabelIcon(militaryCoinsMarket, g.getBoard().getMilitaryAndCoinArea().getOccupants(),0);
			}
		} catch (AreaNotAvailableException e) {
			System.out.println("Some areas are not available - not updatating it");
		}
		
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
	

	/**
	 * Updates the text of the dice labels with the new values taken from the game.
	 */
	public void updateDiceLabels(){
		HashMap<PawnType, Integer> dices = g.getDices();
		diceBlack.setText(dices.get(PawnType.BLACK).toString());
		diceWhite.setText(dices.get(PawnType.WHITE).toString());
		diceOrange.setText(dices.get(PawnType.ORANGE).toString());
	}
	
	/**
	 * Updates the turn markers with the new turn data taken from the game.
	 */
	public void updateTurnMarkers(){
		for(int i=0;i<g.getNumberOfPlayers();i++){
			setTurnMarkerLabelIcon(turnMarkers.get(i), g.getColorTurns()[i]);
		}
	}
	
	/**
	 * Sets the icon of turn marker label with a new image corresponding to the given color.
	 * @param l the label whose icon will be updated.
	 * @param color the color of the turn marker.
	 */
	public void setTurnMarkerLabelIcon(JLabel l, String color){
		ImageIcon imageIcon = new ImageIcon("images/pawns/" + color + ".png"); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(pawnAreaSize, pawnAreaSize,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);

		l.setIcon(imageIcon);
	}
	
	/**
	 * Sets the icon of a card label with the corresponding icon read from file.
	 * @param l the label whose icon will be updated.
	 * @param card the card whose icon will be read from file.
	 */
	public void setCardLabelIcon(JLabel l, Card card){
		if(card != null){
			ImageIcon imageIcon = new ImageIcon("images/cards/" + card.getName() + ".png"); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(cardWidth, cardHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);

			l.setIcon(imageIcon);
		}else{
			l.setIcon(null);
		}
	}
	
	/**
	 * Sets the icon of a pawn label with the corresponding icon read from file.
	 * @param l the label whose icon will be updated.
	 * @param list a list with the occupants of an area.
	 * @param index the index in the list of the occupant whose icon will be added.
	 */
	public void setPawnLabelIcon(JLabel l, ArrayList<PlayerPawnPair> list, int index){
		
		if(index <= list.size()-1){
			String playerColor = list.get(index).getPlayer().getColor();
			String diceColor = list.get(index).getType().getColor();
			
			
			ImageIcon imageIcon = new ImageIcon("images/pawns/" + playerColor+ "-" + diceColor + ".png"); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(pawnAreaSize, pawnAreaSize,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);

			l.setIcon(imageIcon);
		}else{
			l.setIcon(null);
		}
	}
	
	/**
	 * It covers the big production and harvest areas if there are only two players.
	 * Also covers two of the market areas if there are only 3 players.
	 */
	private void updateCovers(){
		int players = g.getNumberOfPlayers();
		
		if(players < 3){
			productionBigCover = new JLabel("");
			productionBigCover.setBounds(prodHarvestBigCoverX, productionCoverY, coverBigWidth, coverBigHeight);
			frontPanel.add(productionBigCover);
			ImageIcon imageIcon = new ImageIcon("images/production-big-cover.png"); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(coverBigWidth, coverBigHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);
			productionBigCover.setIcon(imageIcon);
			
			harvestBigCover = new JLabel("");
			harvestBigCover.setBounds(prodHarvestBigCoverX, harvestCoverY, coverBigWidth, coverBigHeight);
			frontPanel.add(harvestBigCover);
			imageIcon = new ImageIcon("images/harvest-big-cover.png"); // load the image to a imageIcon
			image = imageIcon.getImage(); // transform it 
			newimg = image.getScaledInstance(coverBigWidth, coverBigHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);
			harvestBigCover.setIcon(imageIcon);
		}
		if(players < 4){
			militaryCoinsCover = new JLabel("");
			militaryCoinsCover.setBounds(militaryCoinsMarketCoverX, militaryCoinsMarketCoverY, marketCoverSize, marketCoverSize);
			frontPanel.add(militaryCoinsCover);
			ImageIcon imageIcon = new ImageIcon("images/market-cover1.png"); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(marketCoverSize, marketCoverSize,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);
			militaryCoinsCover.setIcon(imageIcon);
			
			councilPrivilegeCover = new JLabel("");
			councilPrivilegeCover.setBounds(councilPrivilegeMarketCoverX, councilPrivilegeMarketCoverY, marketCoverSize, marketCoverSize);
			frontPanel.add(councilPrivilegeCover);
			imageIcon = new ImageIcon("images/market-cover2.png"); // load the image to a imageIcon
			image = imageIcon.getImage(); // transform it 
			newimg = image.getScaledInstance(marketCoverSize, marketCoverSize,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);
			councilPrivilegeCover.setIcon(imageIcon);
		}

		updatedCovers = true;
	}
	
	/**
	 * Sets a new game state that the frame will read from then updates all the elements of the frame.
	 * It only updates the covers the first time the frame updates.
	 * @param g the new game.
	 */
	public void update(Game g){
		this.g = g;
		updateExcomLabels();
		updateDiceLabels();
		updatePawnLabels();
		updateCardLabels();
		updateTurnMarkers();
		updateExcomMarkerLabesl();
		if(!updatedCovers) updateCovers();
	}
	

}
