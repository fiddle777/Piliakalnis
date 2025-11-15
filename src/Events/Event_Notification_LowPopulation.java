package Events;

import Core.EventResult;
import Core.GameEvent;
import Core.Piliakalnis;

public class Event_Notification_LowPopulation implements GameEvent {

    @Override
    public String getEventText() {
        return "Mazai gyventoju";
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.population < 15;
    }

    @Override
    public EventResult execute(Piliakalnis p) {
        return new EventResult("DEMESIO! Jusu valdose liko labai mazai zmoniu.");
    }

    @Override
    public boolean isRandom() {
        return false;
    }
}
