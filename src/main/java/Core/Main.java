package Core;

public class Main {
    public static void main(String[] args) {
        Piliakalnis piliakalnis = Piliakalnis.createInitPiliakalnis();
        GameController gameController = new GameController(piliakalnis);
        gameController.startGame();
    }
}