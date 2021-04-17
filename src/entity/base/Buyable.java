package entity.base;

public interface Buyable {
    public abstract int getEnergyUsage();
    public abstract boolean canBuy();
    public abstract void Buy();
}
