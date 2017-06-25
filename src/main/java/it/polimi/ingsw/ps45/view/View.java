package it.polimi.ingsw.ps45.view;

public abstract class View extends Thread{
	public abstract void updateView(String gameJSON);
	public abstract void showError(String error);
	public abstract void showResults(String results);
}
