package Events;

import Core.EventResult;
import Core.GameEvent;
import Core.Piliakalnis;

public class Event_GameOver_Food implements GameEvent {

    @Override
    public String getEventText() {
        return "Zlugimas del bado";
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.getFood() <= 0;
    }

    @Override
    public EventResult execute(Piliakalnis p) {
        String text = "Maisto atsargos visiskai isseko. Likusieji issiskirste arba mire nuo bado.\n" +
                "Jusu valdymas baigesi.";
        return new EventResult(text, true);
    }

    @Override
    public boolean isRandom() {
        return false;
    }
}
