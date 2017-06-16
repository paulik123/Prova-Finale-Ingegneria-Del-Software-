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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.polimi.ingsw.ps45.controller.command.Command;
import it.polimi.ingsw.ps45.gson.GsonWithInterface;
import it.polimi.ingsw.ps45.model.cards.Building;
import it.polimi.ingsw.ps45.model.cards.Character;
import it.polimi.ingsw.ps45.model.cards.Card;
import it.polimi.ingsw.ps45.model.cards.Territory;
import it.polimi.ingsw.ps45.model.cards.Venture;
import it.polimi.ingsw.ps45.model.game.Game;
import it.polimi.ingsw.ps45.model.player.Player;
import it.polimi.ingsw.ps45.view.gui.CommandComboBoxListener;
import it.polimi.ingsw.ps45.view.gui.GUICommandParser;

public class ControlBoard extends JFrame implements ActionListener{
	
	private Game g;
	private Player p;
	private String playerID;

	private JPanel contentPane;
	private JLayeredPane layeredPane;
	private JPanel frontPanel;
	
	private CommandComboBoxListener commandListener;
	private GUICommandParser commandParser;
	
	
	private JComboBox commandList;
	private JComboBox areaList;
	private JComboBox pawnList;
	private JComboBox servants;
	private JTextField modes;
	
	JComboBox playerList;
	
	
	private ArrayList<JLabel> characters;
	private ArrayList<JLabel> ventures;
	
	private String[] commands = {"endturn", "placepawnnocard","placepawnmarket", "placepawnharvest","placepawnproduction","placepawnterritory","placepawncharacter", "placepawnbuilding","placepawnventure", "nopawnterritory","nopawncharacter", "nopawnbuilding","nopawnventure","acceptvatican","refusevatican", "addservantsharvest","addservantsproduction", "cp1","cp2","cp3", "harvest","production"};
	private String[] none = {"---"};
	
	private static final int width = 720;
	private static final int height = 454;
	private static final int cardWidth = 80;
	private static final int cardHeight = 128;
	
	private static final int cardsX = 46;
	private static final int cardsXGap = 110;
	private static final int characterY = 44;
	private static final int ventureY = 182;
	
	private static final int playerBoxX = 45;
	private static final int playerBoxY = 18;
	private static final int boxWidht = 100;
	private static final int boxHeight = 20;
	
	private static final int commandBoxY = 400;
	private static final int commandBoxX = 45;
	private static final int areaBoxX = 255;
	private static final int pawnBoxX = 365;
	private static final int servantsBoxX = 475;
	private static final int modeBoxX = 585;
	
	
	
	
	public ControlBoard(String playerID){
		setResizable(false);
		setTitle("Lorenzo il Magnifico - ControlBoard");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, width, height + 25);
		contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
		this.playerID = playerID;
		
		
		characters = new ArrayList<JLabel>();
		ventures = new ArrayList<JLabel>();

		
		setBackground();
		setFrontPanel();
		initializePlayerComboBox();
		initializeCommandComboBoxes();
		
		commandParser = new GUICommandParser(commandList, areaList, pawnList, servants, modes);
		

	}
	
	public void updateCards(){
		//Removing old labels
		for(JLabel l: characters){
			frontPanel.remove(l);
		}
		for(JLabel l: ventures){
			frontPanel.remove(l);
		}
		
		characters = new ArrayList<JLabel>();
		ventures = new ArrayList<JLabel>();
		
		frontPanel.revalidate(); 
		frontPanel.repaint();
		
		updateCharacters();
		updateVentures();
	}
	
	public void updateCharacters(){
		int gap = 0;
		
		for(Character c:p.getResourceSet().getCharacterList()){
			characters.add(initializeCardLabel(cardsX + gap, characterY, c));
			gap = gap + cardsX;
		}
	}
	
	public void updateVentures(){
		int gap = 0;
		
		for(Venture v:p.getResourceSet().getVentureList()){
			ventures.add(initializeCardLabel(cardsX + gap, ventureY, v));
			gap = gap + cardsX;
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
	
	public void updateCommandComboBoxes(){
		
		DefaultComboBoxModel model = null;
		try {
			model = new DefaultComboBoxModel(g.getPlayerByID(playerID).getAvailableCommands());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		commandList.setModel(model);
		
		
		commandListener.actionPerformed(new ActionEvent(commandList, ActionEvent.ACTION_PERFORMED, null));
	}
	

	public void initializeCommandComboBoxes(){
		commandList = new JComboBox();
		commandList.setSelectedIndex(0);
		commandList.setBounds(commandBoxX, commandBoxY, boxWidht * 2, boxHeight);
		frontPanel.add(commandList);
		
		areaList = new JComboBox(none);
		areaList.setSelectedIndex(0);
		areaList.setBounds(areaBoxX, commandBoxY, boxWidht, boxHeight);
		frontPanel.add(areaList);
		
		pawnList = new JComboBox(none);
		pawnList.setSelectedIndex(0);
		pawnList.setBounds(pawnBoxX, commandBoxY, boxWidht, boxHeight);
		frontPanel.add(pawnList);
		
		servants = new JComboBox();
		servants.setBounds(servantsBoxX, commandBoxY, boxWidht, boxHeight);
		frontPanel.add(servants);
		
		modes = new JTextField();;
		modes.setBounds(modeBoxX, commandBoxY, boxWidht, boxHeight);
		frontPanel.add(modes);
		
		
		try {
			commandListener = new CommandComboBoxListener(g, g.getPlayerByID(playerID), areaList, pawnList, servants);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		commandList.addActionListener(commandListener);
		//Simulate an actionperformed so the boxes update
		commandListener.actionPerformed(new ActionEvent(commandList, ActionEvent.ACTION_PERFORMED, null));
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
		try {
			commandListener.update(g, g.getPlayerByID(playerID));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		updatePlayerComboBox();
		updateCommandComboBoxes();
		updateCards();
	}

}
