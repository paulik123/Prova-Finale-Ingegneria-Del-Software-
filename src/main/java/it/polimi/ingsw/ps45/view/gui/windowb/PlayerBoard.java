package it.polimi.ingsw.ps45.view.gui.windowb;

import java.awt.BorderLayout;
import java.awt.Image;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.polimi.ingsw.ps45.gson.GsonWithInterface;
import it.polimi.ingsw.ps45.model.cards.Building;
import it.polimi.ingsw.ps45.model.cards.Card;
import it.polimi.ingsw.ps45.model.cards.Territory;
import it.polimi.ingsw.ps45.model.game.Game;
import it.polimi.ingsw.ps45.model.player.Player;

public class PlayerBoard extends JFrame{
	
	private Game g;
	private Player p;

	private JPanel contentPane;
	private JLayeredPane layeredPane;
	private JPanel frontPanel;
	
	private ArrayList<JLabel> buildings;
	private ArrayList<JLabel> territories;
	
	private static final int width = 720;
	private static final int height = 454;
	private static final int cardWidth = 80;
	private static final int cardHeight = 128;
	
	private static final int cardsX = 58;
	private static final int cardsXGap = 110;
	private static final int buildingY = 60;
	private static final int territoryY = 200;
	
	public PlayerBoard(){
		setResizable(false);
		setTitle("Lorenzo il Magnifico - PlayerBoard");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, width, height + 25);
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
		p = g.getPlayers().get(0);
		
		
		buildings = new ArrayList<JLabel>();
		territories = new ArrayList<JLabel>();

		
		setBackground();
		setFrontPanel();
		updateCards();
	}
	
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
		
		frontPanel.revalidate(); 
		frontPanel.repaint();
		
		updateBuildings();
		updateTerritories();
	}
	
	public void updateBuildings(){
		int gap = 0;
		
		for(Building b:p.getResourceSet().getBuildingList()){
			buildings.add(initializeCardLabel(cardsX + gap, buildingY, b));
			gap = gap + cardsXGap;
		}
		

	}
	
	public void updateTerritories(){
		int gap = 0;
		
		for(Territory t:p.getResourceSet().getTerritoryList()){
			territories.add(initializeCardLabel(cardsX + gap, territoryY, t));
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
		ImageIcon imageIcon = new ImageIcon("images\\playerboard_1.png"); // load the image to a imageIcon
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

}
