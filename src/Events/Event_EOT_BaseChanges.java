package Events;

import Core.EventResult;
import Core.GameEvent;
import Core.Piliakalnis;

public class Event_EOT_BaseChanges implements GameEvent {
    @Override
    public String getEventText() {
        return "Pagrindiniai metu pabaigos pokyciai";
    }
    @Override
    public boolean canTrigger(Piliakalnis p) {
        return true;
    }
    @Override
    public EventResult execute(Piliakalnis p) {
        p.year += 1;
        p.yearsOfRule += 1;
        p.food -= (p.population / 2);
        return new EventResult("");
    }
    @Override
    public boolean isRandom() {
        return false;
    }
}
