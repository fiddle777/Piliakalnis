package Events;

import Core.EventResult;
import Core.Piliakalnis;

public class Event_GameOver_Morale extends BaseEvent {

    @Override
    public String getEventText() {
        return "Zlugimas del morales";
    }

    @Override
    public boolean canTrigger(Piliakalnis piliakalnis) {
        return piliakalnis.getMorale() <= 0;
    }

    @Override
    public EventResult execute(Piliakalnis piliakalnis) {
        String text = "Pavaldiniai nebetiki jumis kaip valdovu. Jie sukyla ir jus nuvercia.\n" +
                "Jusu valdymas baigesi.";
        return new EventResult(text, true);
    }

    @Override
    public boolean isRandom() {
        return false;
    }
}
