package Events;

import Core.Piliakalnis;

public class Event_GameOver_Population extends BaseGameOverEvent {

    public Event_GameOver_Population() {
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
