package it.polimi.ingsw.ps45.view;

public abstract class View extends Thread{
	public abstract String getCommand();
	public abstract void updateView(String gameJSON);
}
