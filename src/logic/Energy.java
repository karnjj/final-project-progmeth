package logic;

public class Energy {
    private int level;
    private int currentEnergy;
    private int maxEnergy;
    private int levelUpUsage;

    // this for fix can't parse double to int if value less than one.
    private double countSecondBeforeUpdate;

    public Energy() {
        this.level = 1;
        this.currentEnergy = 0;
        this.maxEnergy = 100;
        this.levelUpUsage = this.maxEnergy / 2;

        this.countSecondBeforeUpdate = 0;
    }

    public void LevelUp() {
        if (!canLevelUp()) return;
        this.Use(this.levelUpUsage);
        this.setLevel(this.level + 1);
        this.maxEnergy += 30 * level;
        setLevelUpUsage(this.maxEnergy / 2);
    }

    public boolean canLevelUp() {
        return this.currentEnergy >= this.levelUpUsage && this.level < 6;
    }

    public boolean Use(int energy) {
        if (this.currentEnergy - energy < 0) return false;
        setCurrentEnergy(this.currentEnergy - energy);
        return true;
    }

    public void setLevel(int level) {
        this.level = level;
        if (this.level > 6) this.level = 6;
    }

    public int getLevel() {
        return level;
    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }

    public void setCurrentEnergy(int currentEnergy) {
        this.currentEnergy = currentEnergy;
        if (this.currentEnergy < 0) this.currentEnergy = 0;
        if (this.currentEnergy > this.maxEnergy) this.currentEnergy = this.maxEnergy;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    public int getLevelUpUsage() {
        return levelUpUsage;
    }

    public void setLevelUpUsage(int levelUpUsage) {
        this.levelUpUsage = levelUpUsage;
    }

    public void update(double dt) {
        countSecondBeforeUpdate += dt;
        if (countSecondBeforeUpdate > 0.1) {
            setCurrentEnergy((int) (getCurrentEnergy() + countSecondBeforeUpdate * 20 * this.getLevel()));
            countSecondBeforeUpdate -= 0.1;
        }
    }


}
