package gui;

import entity.ranger.*;

public class Hero {
	private String name;
	private String url;
	private int maxHp;
	private int energyUsage;
	private int attack;

	
	Hero(String name){
		this.name = name;
		switch(name) {
			case "Inkblue" -> {
				url = Inkblue.getUrl();
				maxHp = Inkblue.getMaxHP();
				energyUsage = Inkblue.getEnergyUsage();
				attack = Inkblue.getAttack();
			}
			case "Slime" -> {
				url = Slime.getUrl();
				maxHp = Slime.getMaxHP();
				energyUsage = Slime.getEnergyUsage();
				attack = Slime.getAttack();
			}
			case "Minotaur" -> {
				url = Minotaur.getUrl();
				maxHp = Minotaur.getMaxHP();
				energyUsage = Minotaur.getEnergyUsage();
				attack = Minotaur.getAttack();
			}
			case "Alien" -> {
				url = Alien.getUrl();
				maxHp = Alien.getMaxHP();
				energyUsage = Alien.getEnergyUsage();
				attack = Alien.getAttack();
			}
			case "Inkred" -> {
				url = Inkred.getUrl();
				maxHp = Inkred.getMaxHP();
				energyUsage = Inkred.getEnergyUsage();
				attack = Inkred.getAttack();
			}
		}
	}
	
	public String tooltipMassage() {
		return "name: "+ name + "\nmaxHp: " + maxHp + "\nattack: " + attack +"\nEnergy: " + energyUsage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public int getEnergyUsage() {
		return energyUsage;
	}

	public void setEnergyUsage(int energy) {
		this.energyUsage = energy;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}
	
	
	
}
