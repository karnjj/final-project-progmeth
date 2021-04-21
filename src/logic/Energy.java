package logic;

public class Energy {
    private int level;
    private int currentEnergy;
    private int mxEnergy;

    // this for fix can't parse double to int if value less than one.
    private double countSecondBeforeUpdate;

    public Energy() {
        this.level = 1;
        this.currentEnergy = 0;
        this.mxEnergy = 100;
        this.countSecondBeforeUpdate = 0;
    }

    public void UpLevel() {
        this.setLevel(level + 1);
        this.mxEnergy += 80*level;
    }

    public boolean Use(int energy) {
        if(this.currentEnergy - energy < 0) return false;
        setCurrentEnergy(this.currentEnergy - energy);
        return true;
    }

    public void setLevel(int level) {
        this.level = level;
        if(this.level > 6) this.level = 6;
    }

    public int getLevel() {
        return level;
    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }

    public void setCurrentEnergy(int currentEnergy) {
        this.currentEnergy = currentEnergy;
        if(this.currentEnergy < 0) this.currentEnergy = 0;
        if(this.currentEnergy > this.mxEnergy) this.currentEnergy = this.mxEnergy;
    }

    public int getMxEnergy() {
        return mxEnergy;
    }

    public void setMxEnergy(int mxEnergy) {
        this.mxEnergy = mxEnergy;
    }

    public void update(double dt) {
//        System.out.println(getCurrentEnergy());
        countSecondBeforeUpdate += dt;
        if(countSecondBeforeUpdate > 0.1){
            setCurrentEnergy((int) (getCurrentEnergy() + countSecondBeforeUpdate*10*this.getLevel()));
            countSecondBeforeUpdate -= 0.1;
        }
    }
}
