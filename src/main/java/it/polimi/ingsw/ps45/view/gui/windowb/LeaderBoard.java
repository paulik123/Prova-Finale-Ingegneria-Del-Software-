package it.polimi.ingsw.ps45.view.gui.windowb;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.polimi.ingsw.ps45.controller.command.Command;
import it.polimi.ingsw.ps45.gson.GsonWithInterface;
import it.polimi.ingsw.ps45.model.cards.Building;
import it.polimi.ingsw.ps45.model.cards.Character;
import it.polimi.ingsw.ps45.model.cards.LeaderCard;
import it.polimi.ingsw.ps45.model.cards.Card;
import it.polimi.ingsw.ps45.model.cards.Territory;
import it.polimi.ingsw.ps45.model.cards.Venture;
import it.polimi.ingsw.ps45.model.game.Game;
import it.polimi.ingsw.ps45.model.player.Player;
import it.polimi.ingsw.ps45.view.gui.CommandComboBoxListener;
import it.polimi.ingsw.ps45.view.gui.GUICommandParser;

public class LeaderBoard extends JFrame implements ActionListener{
	
	private Game g;
	private Player p;
	private String playerID;

	private JPanel contentPane;
	private JLayeredPane layeredPane;
	private JPanel frontPanel;
	
	private CommandComboBoxListener commandListener;
	private GUICommandParser commandParser;
	
	

	private JTextArea textArea;
	
	
	JComboBox playerList;
	
	
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
	
	private void initializeTextArea() {
        textArea = new JTextArea();
        textArea.setBounds((width - textAreaWidth)/2, textAreaY, textAreaWidth, textAreaHeight);
        textArea.setEditable(false);
        frontPanel.add(textArea);
	}
	
	public void updateTextArea(String s){
		textArea.setText(s);
	}

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
	
	public void updateLeaderCards(){
		int gap = 0;
		
		for(LeaderCard lc:p.getResourceSet().getLeaderCardList()){
			leaderCards.add(initializeCardLabel(cardsX + gap, leaderY, lc));
			gap = gap + cardsXGap;
		}
	}
	
	public void updateActivatedLeaderCards(){
		int gap = 0;
		
		for(LeaderCard lc:p.getResourceSet().getActivatedLeaderCardList()){
			activatedLeaderCards.add(initializeCardLabel(cardsX + gap, activatedLeaderY, lc));
			gap = gap + cardsXGap;
		}
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
		ImageIcon imageIcon = new ImageIcon("images\\punchboard.png"); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);  // transform it back
		backgroundLabel.setIcon(imageIcon);
		
	}
	
	public void setFrontPanel(){
		frontPanel = new JPanel();
		frontPanel.setOpaque(false);
		layeredPane.setLayer(frontPanel, 1);
		frontPanel.setBounds(0, 0, width, height + 25);
		layeredPane.add(frontPanel);
		frontPanel.setLayout(null);

	}
	
	
	public JLabel initializeCardLabel(int x, int y, LeaderCard card){
		
		JLabel newLabel = new JLabel("");
		newLabel.setBounds(x, y, cardWidth, cardHeight);
		
		if(card != null){
			ImageIcon imageIcon = new ImageIcon("images\\leadercards\\" + card.getName() + ".jpg"); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(cardWidth, cardHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);

			newLabel.setIcon(imageIcon);
		}

		frontPanel.add(newLabel);
		
		return newLabel;
	}
	
	public void initializePlayerComboBox(){

		playerList = new JComboBox();
        playerList.addActionListener(this);
        playerList.setBounds(playerBoxX, playerBoxY, boxWidht, boxHeight);
        frontPanel.add(playerList);
	}
	
	public void updatePlayerComboBox(){
		ArrayList<String> players = new ArrayList<String>();
		for(Player p: g.getPlayers()){
			players.add(p.getPlayerID());
		}
		
		DefaultComboBoxModel model = new DefaultComboBoxModel(players.toArray(new String[players.size()]));
		playerList.setModel(model);
		playerList.setSelectedItem(playerID);
	}
	
	
	//Only updates cards, has nothing to do with command boxes
	@Override
	public void actionPerformed(ActionEvent e) {

	        JComboBox cb = (JComboBox)e.getSource();
	        String playerID = (String)cb.getSelectedItem();
	        try {
				p = g.getPlayerByID(playerID);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			updateCards();
	}
	
	public Command getCommand() throws Exception{
		return commandParser.parse();
	}
	
	public void update(Game g){
		
		this.g = g;
		updatePlayerComboBox();
		updateLeaderCards();
	}



	
	

}
