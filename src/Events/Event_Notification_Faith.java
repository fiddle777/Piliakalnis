package Events;

import Core.EventResult;
import Core.GameEvent;
import Core.Piliakalnis;

public class Event_Notification_Faith implements GameEvent {

    @Override
    public String getEventText() {
        return "Tikejimo bukles ispejimas";
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.faith < 10 || p.faith >= 40;
    }

    @Override
    public EventResult execute(Piliakalnis p) {
        if (p.faith < 10) {
            p.morale -= 2;
            if (p.morale < 0) {p.morale = 0;}
            String text = "DEMESIO! Zmones ima abejoti dievu palankumu. Valstieciu nuotaika prasteja.";
            return new EventResult(text);
        } else if (p.faith >= 40) {
            p.morale += 1;
            if (p.morale > 100) {
                p.morale = 100;
            }
            String text = "Aukuras dega, apeigos vyksta reguliarai. Tikejimas stiprina bendruomene ir palaiko morale.";
            return new EventResult(text);
        }
        return new EventResult("");
    }

    @Override
    public boolean isRandom() {
        return false;
    }
}
