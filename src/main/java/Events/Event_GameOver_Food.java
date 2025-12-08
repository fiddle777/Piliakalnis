package Events;

import Core.Piliakalnis;

public class Event_GameOver_Food extends BaseGameOverEvent {

    public Event_GameOver_Food() {
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
