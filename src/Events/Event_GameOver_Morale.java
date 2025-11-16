package Events;

import Core.EventResult;
import Core.GameEvent;

public class Event_GameOver_Morale implements GameEvent {

    @Override
    public String getEventText() {
        return "Zlugimas del morales";
    }

    @Override
    public boolean canTrigger(Core.Piliakalnis p) {
        return p.morale <= -0;
    }

    @Override
    public EventResult execute(Core.Piliakalnis p) {
        String text = "Pavaldiniai nebetiki jumis kaip valdovu. Jie sukyla ir jus nuvercia.\nJusu valdymas baigesi.";
        return new EventResult(text, true);
    }

    @Override
    public boolean isRandom() {
        return false;
    }
}
