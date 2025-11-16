package Events;

import Core.EventResult;
import Core.GameEvent;

public class Event_GameOver_Food implements GameEvent {
    @Override
    public String getEventText() {
        return "Zlugimas del bado";
    }
    @Override
    public boolean canTrigger(Core.Piliakalnis p) {
        return p.food <= 0;
    }
    @Override
    public EventResult execute(Core.Piliakalnis p) {
        String text = "Maisto atsargos visiskai isseko. Likusieji issiskirste arba mire is bado.\nJusu valdymas baigesi.";
        return new EventResult(text, true);
    }
    @Override
    public boolean isRandom() {
        return false;
    }
}
