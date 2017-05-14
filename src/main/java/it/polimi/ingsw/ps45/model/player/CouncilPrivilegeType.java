package it.polimi.ingsw.ps45.model.player;

public enum CouncilPrivilegeType {
	WOODANDSTONE, SERVANTS, COINS, MILITARY, FAITH;
	
	private static int WOODREWARD = 1;
	private static int STONEREWARD = 1;
	private static int SERVANTSREWARD = 2;
	private static int COINSREWARD = 2;
	private static int MILITARYREWARD = 2;
	private static int FAITHREWARD = 1;
	
	public static ConsumableSet getConsumableSet(CouncilPrivilegeType cpt){
		ConsumableSet cs = new ConsumableSet();
		switch(cpt){
		case WOODANDSTONE:
			cs.setConsumable("wood", WOODREWARD);
			cs.setConsumable("stone", STONEREWARD);
		case SERVANTS:
			cs.setConsumable("servants", SERVANTSREWARD);
		case COINS:
			cs.setConsumable("coins", COINSREWARD);
		case MILITARY:
			cs.setConsumable("militaryPoints", MILITARYREWARD);
		case FAITH:
			cs.setConsumable("faithPoints", FAITHREWARD);
		}
		return cs;
	}
	
	public static ConsumableSet getConsumableSet(CouncilPrivilegeType cpt1, CouncilPrivilegeType cpt2){
		if(cpt1 == cpt2){
			//TODO: HANDLE THIS
		}
		ConsumableSet cs = getConsumableSet(cpt1);
		cs.merge(getConsumableSet(cpt2));
		return cs;
	}
	public static ConsumableSet getConsumableSet(CouncilPrivilegeType cpt1, CouncilPrivilegeType cpt2, CouncilPrivilegeType cpt3){
		if(cpt1 == cpt2 || cpt1 == cpt3 || cpt2 == cpt3){
			//TODO: HANDLE THIS
		}
		ConsumableSet cs = getConsumableSet(cpt1);
		cs.merge(getConsumableSet(cpt2));
		return cs;
	}
}
