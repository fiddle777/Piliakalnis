package Events;

import Core.EventResult;
import Core.GameEvent;
import Core.Piliakalnis;

public class Event_Notification_LowDefense implements GameEvent {

    @Override
    public String getEventText() {
        return "Zema gynyba";
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.defense < 20 && p.population >= 30;
    }

    @Override
    public EventResult execute(Piliakalnis p) {
        return new EventResult("DEMESIO! Piliakalnio gynyba labai silpna.");
    }

    @Override
    public boolean isRandom() {
        return false;
    }
}
