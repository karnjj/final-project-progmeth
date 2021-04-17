package gui;

public class Hero {
	private String name;
	private String url;
	private int maxHp;
	private int energy;
	private int attack;

	
	Hero(String name){
		this.name = name;
		switch(name) {
			case "1" : url="walk1.png"; maxHp=100; energy=10; attack=20; break;
		}
	}
	
	public String tooltipMassage() {
		return "name: "+ name + "\nmaxHp:" + maxHp + "\nattack:" + attack +"\nEnergy:" + energy;
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
