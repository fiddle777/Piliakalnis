package Events;

import Core.EventResult;
import Core.GameEvent;
import Core.Piliakalnis;

public class Event_Notification_LowMorale implements GameEvent {

    @Override
    public String getEventText() {
        return "Zema morale";
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.morale < 10;
    }

    @Override
    public EventResult execute(Piliakalnis p) {
        return new EventResult("DEMESIO! Pavaldiniu morale labai zema.");
    }

    @Override
    public boolean isRandom() {
        return false;
    }
}
