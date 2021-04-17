package entity.base;

public interface Attackable {
    public abstract void attack(Damageable e);
    public abstract int getAttackRange();
}
