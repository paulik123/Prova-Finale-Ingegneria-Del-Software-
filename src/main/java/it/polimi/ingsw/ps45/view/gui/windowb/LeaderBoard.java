package it.polimi.ingsw.ps45.view.gui.windowb;

import java.awt.BorderLayout;
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
import javax.swing.JTextArea;

import it.polimi.ingsw.ps45.model.cards.LeaderCard;
import it.polimi.ingsw.ps45.model.game.Game;
import it.polimi.ingsw.ps45.model.player.Player;

/**
 * The frame of the GUI that show the players's leader cards. It's also the frame that displays messages from the server.
 * It uses absolute positioning because some the elements were very hard to arrange properly using other LayoutManagers.
 */
public class LeaderBoard extends JFrame implements ActionListener{
	
	private static final Logger LOGGER = Logger.getLogger( LeaderBoard.class.getName());
	
	private transient Game g;
	private transient Player p;
	private String playerID;

	private JPanel contentPane;
	private JLayeredPane layeredPane;
	private JPanel frontPanel;
	

	private JTextArea textArea;
	
	private JComboBox playerList;
	
	private static final String backgroundImagePath = "images/punchboard.png";
	
	
	private ArrayList<JLabel> leaderCards;
	private ArrayList<JLabel> activatedLeaderCards;
	
	
	private static final int width = 720;
	private static final int height = 454;
	private static final int cardWidth = 80;
	private static final int cardHeight = 128;
	
	private static final int cardsX = 46;
	private static final int cardsXGap = 110;
	private static final int leaderY = 44;
	private static final int activatedLeaderY = 182;
	
	private static final int playerBoxX = 45;
	private static final int playerBoxY = 18;
	private static final int boxWidht = 100;
	private static final int boxHeight = 20;
	

	

	
	private static final int textAreaWidth = 500;
	private static final int textAreaHeight = 100;
	private static final int textAreaY = 325;
	
	
	
	
	/**
 	 * Constructor
 	 * Initializes the background and the front content panel on which all the elements are later added.
	 */
	public LeaderBoard(String playerID){
		
		
		setResizable(false);
		setTitle("Lorenzo il Magnifico - LeaderBoard");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, width, height + 25);
		contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
		this.playerID = playerID;
		
		
		leaderCards = new ArrayList<JLabel>();
		activatedLeaderCards = new ArrayList<JLabel>();

		
		setBackground();
		setFrontPanel();
		initializePlayerComboBox();
		initializeTextArea();
	}
	
	/**
 	 * Initializes the text area that displays the messages from the server.
	 */
	private void initializeTextArea() {
        textArea = new JTextArea();
        textArea.setBounds((width - textAreaWidth)/2, textAreaY, textAreaWidth, textAreaHeight);
        textArea.setEditable(false);
        frontPanel.add(textArea);
	}
	
	/**
 	 * Updates the text area with new text.
 	 * @param s the new text to be displayed.
	 */
	public void updateTextArea(String s){
		textArea.setText(s);
	}

	/**
	 * Updates the Icons of the Card Labels with the images corresponding to the current player's leader cards.
	 * It creates new labels and deletes the old ones.
	 */
	public void updateCards(){
		//Removing old labels
		for(JLabel l: leaderCards){
			frontPanel.remove(l);
		}
		for(JLabel l: activatedLeaderCards){
			frontPanel.remove(l);
		}
		
		leaderCards = new ArrayList<JLabel>();
		activatedLeaderCards = new ArrayList<JLabel>();
		

		
		updateLeaderCards();
		updateActivatedLeaderCards();
		
		frontPanel.revalidate(); 
		frontPanel.repaint();
	}
	
	/**
	 * Updates the Icons of the Card Labels with the images corresponding to the current selected player's leader cards.
	 */
	public void updateLeaderCards(){
		int gap = 0;
		
		for(LeaderCard lc:p.getResourceSet().getLeaderCardList()){
			leaderCards.add(initializeCardLabel(cardsX + gap, leaderY, lc));
			gap = gap + cardsXGap;
		}
	}
	
	/**
	 * Updates the Icons of the Card Labels with the images corresponding to the current selected player's activated leader cards.
	 */
	public void updateActivatedLeaderCards(){
		int gap = 0;
		
		for(LeaderCard lc:p.getResourceSet().getActivatedLeaderCardList()){
			activatedLeaderCards.add(initializeCardLabel(cardsX + gap, activatedLeaderY, lc));
			gap = gap + cardsXGap;
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
		
		JLabel backgroundLabel = new JLabel("");

		
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
	 * Creates a new label then adds it to the front panel.
	 * Also adds it to the front panel.
	 * @param x the x position of the label.
	 * @param y the y position of the label.
	 * @param card the leader card the new label's icon will contain.
	 */
	public JLabel initializeCardLabel(int x, int y, LeaderCard card){
		
		JLabel newLabel = new JLabel("");
		newLabel.setBounds(x, y, cardWidth, cardHeight);
		
		if(card != null){
			ImageIcon imageIcon = new ImageIcon("images/leadercards/" + card.getName() + ".jpg"); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(cardWidth, cardHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);

			newLabel.setIcon(imageIcon);
		}

		frontPanel.add(newLabel);
		
		return newLabel;
	}
	
	/**
	 * Initializes the ComboBox with which the user can change the current player of the board(to see other player's leader cards).
	 */
	public void initializePlayerComboBox(){

		playerList = new JComboBox();
        playerList.addActionListener(this);
        playerList.setBounds(playerBoxX, playerBoxY, boxWidht, boxHeight);
        frontPanel.add(playerList);
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
	 * This method is called when the user change's the boards current player.
	 * The player is changed with the newly selected one then the leader cards on the board are updated.
	 */
	//Only updates cards, has nothing to do with command boxes
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
	}
	
	
	/**
	 * Sets a new game state that the frame will read from then updates all the elements of the frame.
	 * @param g the new game.
	 */
	public void update(Game g){
		
		this.g = g;
		updatePlayerComboBox();
		updateLeaderCards();
	}



	
	

}
