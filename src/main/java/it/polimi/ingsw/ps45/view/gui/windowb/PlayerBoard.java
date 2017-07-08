package it.polimi.ingsw.ps45.view.gui.windowb;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import it.polimi.ingsw.ps45.model.cards.Building;
import it.polimi.ingsw.ps45.model.cards.Card;
import it.polimi.ingsw.ps45.model.cards.Territory;
import it.polimi.ingsw.ps45.model.game.Game;
import it.polimi.ingsw.ps45.model.player.Player;

/**
 * The frame of the GUI that show the players's own board containing territories, buildings, resources and the personal bonus tile. 
 * It uses absolute positioning because some the elements were very hard to arrange properly using other LayoutManagers.
 */
public class PlayerBoard extends JFrame implements ActionListener{
	
	private static final Logger LOGGER = Logger.getLogger( PlayerBoard.class.getName());
	private transient Game g;
	private transient Player p;

	private JPanel contentPane;
	private JLayeredPane layeredPane;
	private JPanel frontPanel;
	private JLabel backgroundLabel;
	
	private ArrayList<JLabel> buildings;
	private ArrayList<JLabel> territories;
	
	private JLabel stoneLabel;
	private JLabel woodLabel;
	private JLabel coinsLabel;
	private JLabel servantsLabel;
	private JLabel faithLabel;
	private JLabel militaryLabel;
	private JLabel victoryLabel;
	
	private JComboBox playerList;
	
	private String playerID;
	
	private static final String backgroundImagePath = "images/playerboard_1.png";
	
	private static final int width = 720;
	private static final int height = 454;
	private static final int cardWidth = 80;
	private static final int cardHeight = 128;
	
	private static final int cardsX = 60;
	private static final int cardsXGap = 110;
	private static final int buildingY = 20;
	private static final int territoryY = 190;
	
	private static final int areaSize = 50;
	
	private static final int coinsX = 80;
	private static final int coinsY = 400;
	private static final int stoneX = 310;
	private static final int stoneY = 400;
	private static final int woodX = 200;
	private static final int woodY = 400;
	private static final int servantsX = 420;
	private static final int servantsY = 400;
	private static final int faithX = 120;
	private static final int faithY = 315;
	private static final int militaryX = 230;
	private static final int militaryY = 315;
	private static final int victoryX = 340;
	private static final int victoryY = 315;
	
	private static final int playerBoxX = 600;
	private static final int playerBoxY = 325;
	private static final int playerBoxWidht = 100;
	private static final int playerBoxHeight = 20;
	
	/**
 	 * Constructor
 	 * Initializes the background and the front content panel on which all the elements are later added.
	 */
	public PlayerBoard(String playerID){
		setResizable(false);
		setTitle("Lorenzo il Magnifico - PlayerBoard");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, width, height + 25);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		this.playerID = playerID;
		

		buildings = new ArrayList<JLabel>();
		territories = new ArrayList<JLabel>();

		
		setBackground();
		setFrontPanel();
		initializeResourceLabels();
		initializePlayerComboBox();
		
	}
	
	/**
	 * Updates the Icons of the Card Labels with the images corresponding to the current player's territories and building cards.
	 * It creates new labels and deletes the old ones.
	 */
	public void updateCards(){
		//Removing old labels
		for(JLabel l: buildings){
			frontPanel.remove(l);
		}
		for(JLabel l: territories){
			frontPanel.remove(l);
		}
		
		buildings = new ArrayList<JLabel>();
		territories = new ArrayList<JLabel>();
		

		
		updateBuildings();
		updateTerritories();
		
		frontPanel.revalidate(); 
		frontPanel.repaint();
	}
	
	/**
	 * Updates the Icons of the Card Labels with the images corresponding to the current selected player's building cards.
	 */
	public void updateBuildings(){
		int gap = 0;
		
		for(Building b:p.getResourceSet().getBuildingList()){
			buildings.add(initializeCardLabel(cardsX + gap, buildingY, b));
			gap = gap + cardsXGap;
		}
		

	}
	
	/**
	 * Updates the Icons of the Card Labels with the images corresponding to the current selected player's territories cards.
	 */
	public void updateTerritories(){
		int gap = 0;
		
		for(Territory t:p.getResourceSet().getTerritoryList()){
			territories.add(initializeCardLabel(cardsX + gap, territoryY, t));
			gap = gap + cardsX;
		}
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
		
		backgroundLabel = new JLabel("");
		
		

		
		background.add(backgroundLabel, BorderLayout.CENTER);
		ImageIcon imageIcon = new ImageIcon(backgroundImagePath); // load the image to a imageIcon
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
		frontPanel.setBounds(0, 0, width, height + 25);
		layeredPane.add(frontPanel);
		frontPanel.setLayout(null);

	}
	
	/**
	 * Initializes Labels that show how many resources the player has. 
	 */
	public void initializeResourceLabels(){
		coinsLabel = initializeLabel(coinsX, coinsY, Color.RED);
		stoneLabel = initializeLabel(stoneX, stoneY, Color.RED);		
		woodLabel = initializeLabel(woodX, woodY, Color.RED);
		servantsLabel = initializeLabel(servantsX, servantsY, Color.RED);
		faithLabel = initializeLabel(faithX, faithY, Color.RED);
		militaryLabel = initializeLabel(militaryX, militaryY, Color.RED);
		victoryLabel = initializeLabel(victoryX, victoryY, Color.RED);
	}
	
	/**
	 * Updates the background image depending on the currently selected player's personal bonus tile. 
	 */
	private void updateBackground(){
		ImageIcon imageIcon = new ImageIcon("images/playerboard_" + p.getResourceSet().getBonusTile().getId() + ".png"); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);  // transform it back
		backgroundLabel.setIcon(imageIcon);
	}
	
	/**
	 * Updates the resource labels depending on the currently selected player's resources. 
	 */
	public void updateResourceLabels(){
		coinsLabel.setText(String.valueOf(p.getResourceSet().getResources().getCoins()));
		stoneLabel.setText(String.valueOf(p.getResourceSet().getResources().getStone()));
		woodLabel.setText(String.valueOf(p.getResourceSet().getResources().getWood()));
		servantsLabel.setText(String.valueOf(p.getResourceSet().getResources().getServants()));
		faithLabel.setText(String.valueOf(p.getResourceSet().getResources().getFaithPoints()));
		militaryLabel.setText(String.valueOf(p.getResourceSet().getResources().getMilitaryPoins()));
		victoryLabel.setText(String.valueOf(p.getResourceSet().getResources().getVictoryPoints()));
		
	}
	
	/**
	 * Creates a new label then adds it to the front panel.
	 * Also adds it to the front panel.
	 * @param x the x position of the label.
	 * @param y the y position of the label.
	 * @param card the card the new label's icon will contain.
	 */
	public JLabel initializeCardLabel(int x, int y, Card card){
		
		JLabel newLabel = new JLabel("");
		newLabel.setBounds(x, y, cardWidth, cardHeight);
		
		if(card != null){
			ImageIcon imageIcon = new ImageIcon("images/cards/" + card.getName() + ".png"); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(cardWidth, cardHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);

			newLabel.setIcon(imageIcon);
		}
		
		frontPanel.add(newLabel);
		return newLabel;
	}
	
	/**
	 * Initializes the ComboBox with which the user can change the current player of the board(to see other player's cards).
	 */
	public void initializePlayerComboBox(){
		playerList = new JComboBox();
        playerList.addActionListener(this);
        playerList.setBounds(playerBoxX, playerBoxY, playerBoxWidht, playerBoxHeight);
        frontPanel.add(playerList);
	}
	
	/**
	 * Creates a new label then adds it to the front panel.
	 * @param x the x position of the label.
	 * @param y the y position of the label.
	 * @param c the color of the foreground of the label(color of the text).
	 */
	public JLabel initializeLabel(int x, int y, Color c){
		JLabel newLabel = new JLabel("");
		newLabel.setBounds(x, y, areaSize, areaSize);
		newLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		newLabel.setForeground(c);
		frontPanel.add(newLabel);


		return newLabel;
	}
	
	/**
	 * Updates the model of the ComboBox with the players that are currently in the game.
	 */
	public void updatePlayerComboBox(){
		ArrayList<String> players = new ArrayList<String>();
		for(Player p: g.getPlayers()){
			players.add(p.getPlayerID());
		}
		
		DefaultComboBoxModel model = new DefaultComboBoxModel(players.toArray(new String[players.size()]));
		playerList.setModel(model);
		playerList.setSelectedItem(playerID);
	}
	
	/**
	 * Sets a new game state that the frame will read from then updates all the elements of the frame.
	 * @param g the new game.
	 */
	public void update(Game g){
		this.g = g;
		
		updatePlayerComboBox();
		updateCards();
		updateResourceLabels();
	}

	/**
	 * This method is called when the user change's the boards current player.
	 * The player is changed with the newly selected one then the cards and other labels on the board are updated.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

	        JComboBox cb = (JComboBox)e.getSource();
	        String playerID = (String)cb.getSelectedItem();
	        try {
				p = g.getPlayerByID(playerID);
			} catch (Exception e1) {
				LOGGER.log(Level.SEVERE, "context", e);
			}
			updateCards();
			updateResourceLabels();
			updateBackground();
	}
	

}
