package logic;

public class GameController {
    private static Energy energy;

    public static void InitGame() {
        energy = new Energy();
    }

    public static int getCurrentEnergy() {
        return energy.getCurrentEnergy();
    }
    public static void useEnergy(int energy) {
        if (!GameController.energy.Use(energy)) {
            System.out.println("error");
        }
    }
}
