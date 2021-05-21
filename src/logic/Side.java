package logic;

public enum Side {
    HERO(1),
    ENEMY(-1);
    private int val;

    Side(int val)
    {
        this.val = val;
    }

    public int getVal()
    {
        return this.val;
    }

    public Side getOpposite() {
        return this.val == 1 ? Side.ENEMY : Side.HERO;
    }



}
