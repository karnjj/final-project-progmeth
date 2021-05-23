package gui;

import entity.base.Buyable;
import entity.ranger.*;
import logic.GameController;
import logic.Side;

public class Hero implements Buyable {
	private Ranger ranger;
	private float buyCountdown;

	public Hero(String name) {
		switch(name) {
			case "Inkblue" ->
					ranger = new Inkblue(-1,-1, Side.HERO);
			case "Slime" ->
					ranger = new Slime(-1,-1, Side.HERO);
			case "Minotaur" ->
					ranger = new Minotaur(-1,-1, Side.HERO);
			case "Alien" ->
					ranger = new Alien(-1,-1, Side.HERO);
			case "Inkred" ->
					ranger = new Inkred(-1,-1, Side.HERO);
		}
	}
	
	public String tooltipMassage() {
		return "name: "+ ranger.getName() + "\nmaxHp: " + ranger.getMaxHP() + "\nattack: " + ranger.getAttack() +"\nEnergy: " + ranger.getEnergyUsage();
	}

	@Override
	public boolean canBuy() {
		return this.buyCountdown == 0 && GameController.getCurrentEnergy() >= ranger.getEnergyUsage();
	}
	
	public void Buy() {
		if (this.canBuy()) {
			GameController.useEnergy(ranger.getEnergyUsage());
			GameController.createRanger(ranger.getName(), ranger.getSide());
			setBuyCountdown(ranger.getBuyDelay());
		}
	}
	
	public void update(double dt) {
		if(this.buyCountdown != 0)
			setBuyCountdown(this.buyCountdown - dt);
	}

	public Ranger getRanger() {
		return this.ranger;
	}

	

	public void setBuyCountdown(double buyCountdown) {
		this.buyCountdown = (float) buyCountdown;
		if (this.buyCountdown < 0f) this.buyCountdown = 0f;
	}

	public float getBuyCountdown() {
		return buyCountdown;
	}
}
