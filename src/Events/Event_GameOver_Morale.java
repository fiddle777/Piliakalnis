package Events;

import Core.EventResult;
import Core.GameEvent;
import Core.Piliakalnis;

public class Event_GameOver_Morale implements GameEvent {

    @Override
    public String getEventText() {
        return "Zlugimas del morales";
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.getMorale() <= 0;
    }

    @Override
    public EventResult execute(Piliakalnis p) {
        String text = "Pavaldiniai nebetiki jumis kaip valdovu. Jie sukyla ir jus nuvercia.\n" +
                "Jusu valdymas baigesi.";
        return new EventResult(text, true);
    }

    @Override
    public boolean isRandom() {
        return false;
    }
}
