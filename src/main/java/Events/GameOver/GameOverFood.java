package Events.GameOver;

import Core.Piliakalnis;

public class GameOverFood extends BaseGameOverEvent {

    public GameOverFood() {
        super("Zlugimas del bado");
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.getFood() <= 0;
    }

    @Override
    protected String buildGameOverText(Piliakalnis p) {
        return "Maisto atsargos visiskai isseko. Likusieji issiskirste arba mire nuo bado.\n" +
                "Jusu valdymas baigesi.";
    }
}
