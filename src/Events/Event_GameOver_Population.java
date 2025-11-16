package Events;

import Core.EventResult;
import Core.GameEvent;

public class Event_GameOver_Population implements GameEvent {

    @Override
    public String getEventText() {
        return "Piliakalnis tuscias";
    }

    @Override
    public boolean canTrigger(Core.Piliakalnis p) {
        return p.population <= 0;
    }

    @Override
    public EventResult execute(Core.Piliakalnis p) {
        String text = "Piliakalnyje neliko zmoniu. Nera kam prisiminti jusu vardo.\nJusu valdymas baigesi.";
        return new EventResult(text, true);
    }

    @Override
    public boolean isRandom() {
        return false;
    }
}
