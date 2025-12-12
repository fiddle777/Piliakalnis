package Events.GameOver;

import Core.Piliakalnis;

public class GameOverMorale extends BaseGameOverEvent {

    public GameOverMorale() {
        super("Zlugimas del morales");
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.getMorale() <= 0;
    }

    @Override
    protected String buildGameOverText(Piliakalnis p) {
        return "Pavaldiniai nebetiki jumis kaip valdovu. Jie sukyla ir jus nuvercia.\n" +
                "Jusu valdymas baigesi.";
    }
}
