package logic;

public enum Side {
    HERO(1),
    ENEMY(-1);
    private int val;

    public int getVal()
    {
        return this.val;
    }

    private Side(int val)
    {
        this.val = val;
    }


}
