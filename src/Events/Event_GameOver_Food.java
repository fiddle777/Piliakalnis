package Events;

import Core.EventResult;
import Core.Piliakalnis;

public class Event_GameOver_Food extends BaseEvent {

    @Override
    public String getEventText() {
        return "Zlugimas del bado";
    }

    @Override
    public boolean canTrigger(Piliakalnis piliakalnis) {
        return piliakalnis.getFood() <= 0;
    }

    @Override
    public EventResult execute(Piliakalnis piliakalnis) {
        String text = "Maisto atsargos visiskai isseko. Likusieji issiskirste arba mire nuo bado.\n" +
                "Jusu valdymas baigesi.";
        return new EventResult(text, true);
    }

    @Override
    public boolean isRandom() {
        return false;
    }
}
