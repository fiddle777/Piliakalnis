package Events;

import Core.EventResult;
import Core.GameEvent;
import Core.Piliakalnis;

public class Event_GameOver_Population implements GameEvent {

    @Override
    public String getEventText() {
        return "Piliakalnis tuscias";
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.getPopulation() <= 0;
    }

    @Override
    public EventResult execute(Piliakalnis p) {
        String text = "Piliakalnyje neliko zmoniu. Nera kam prisiminti jusu vardo.\n" +
                "Jusu valdymas baigesi.";
        return new EventResult(text, true);
    }

    @Override
    public boolean isRandom() {
        return false;
    }
}
