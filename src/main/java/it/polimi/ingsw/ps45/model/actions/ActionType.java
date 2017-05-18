package it.polimi.ingsw.ps45.model.actions;

public enum ActionType {
	FIRST{
		@Override
		public boolean isType(Action action){
			return false;
		}
	};
	
	
	public boolean isType(Action action){
		return true;
	}
}
