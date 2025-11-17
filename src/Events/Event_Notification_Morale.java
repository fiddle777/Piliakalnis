package Events;

import Core.EventResult;
import Core.GameEvent;
import Core.Piliakalnis;

public class Event_Notification_Morale implements GameEvent {

    @Override
    public String getEventText() {
        return "Morales bukles ispejimas";
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.morale < 25 || p.morale >= 75;
    }

    @Override
    public EventResult execute(Piliakalnis p) {
        if (p.morale < 25) {
            p.morale -= 2;
            if (p.morale < 0) {p.morale = 0;}
            String text = "DEMESIO! Turgavieteje ir prie lauzu girdisi nepasitenkinimo murmejimas. Jei niekas nesikeis, gali kilti rimtesni neramumai.";
            return new EventResult(text);
        } else if (p.morale >= 75) {
            p.morale += 1;
            if (p.morale > 100) {p.morale = 100;}
            String text = "Zmones kalba apie teisinga ir tvirta valdzia. Piliakalnyje tvyro pasitikejimo nuotaika.";
            return new EventResult(text);
        }
        return new EventResult("");
    }

    @Override
    public boolean isRandom() {
        return false;
    }
}
