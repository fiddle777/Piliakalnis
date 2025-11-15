package Events;

import Core.EventResult;
import Core.GameEvent;
import Core.Piliakalnis;

public class Event_Notification_LowFood implements GameEvent {

    @Override
    public String getEventText() {
        return "Mazai maisto";
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.food < 40;
    }

    @Override
    public EventResult execute(Piliakalnis p) {
        return new EventResult("DEMESIO! Sandeliuose beveik neliko maisto.");
    }

    @Override
    public boolean isRandom() {
        return false;
    }
}
