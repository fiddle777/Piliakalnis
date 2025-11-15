package Events;

import Core.EventResult;
import Core.GameEvent;
import Core.Piliakalnis;

public class Event_Notification_LowFaith implements GameEvent {

    @Override
    public String getEventText() {
        return "Zemas tikejimas";
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.faith < 10;
    }

    @Override
    public EventResult execute(Piliakalnis p) {
        return new EventResult("DEMESIO! Zmoniu tikejimas silpsta.");
    }

    @Override
    public boolean isRandom() {
        return false;
    }
}
