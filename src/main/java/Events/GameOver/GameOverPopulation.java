package Events.GameOver;

import Core.Piliakalnis;

public class GameOverPopulation extends BaseGameOverEvent {

    public GameOverPopulation() {
        super("Piliakalnis tuscias");
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.getPopulation() <= 0;
    }

    @Override
    protected String buildGameOverText(Piliakalnis p) {
        return "Piliakalnyje neliko zmoniu. Nera kam prisiminti jusu vardo.\n" +
                "Jusu valdymas baigesi.";
    }
}
