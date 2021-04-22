package gui;

import entity.Inkblue;
import entity.Ranger;
import entity.Slime;

public class Hero {
	private String name;
	private String url;
	private int maxHp;
	private int energy;
	private int attack;

	
	Hero(String name){
		this.name = name;
		switch(name) {
			case "Inkblue" -> {
				url = Inkblue.getUrl();
				maxHp = Inkblue.getMaxHP();
				energy = Inkblue.getEnergyUsage();
				attack = Inkblue.getAttack();
			}
			case "Slime" -> {
				url = Slime.getUrl();
				maxHp = Slime.getMaxHP();
				energy = Slime.getEnergyUsage();
				attack = Slime.getAttack();
			}
		}
	}
	
	public String tooltipMassage() {
		return "name: "+ name + "\nmaxHp: " + maxHp + "\nattack: " + attack +"\nEnergy: " + energy;
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

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}
	
	
	
}
