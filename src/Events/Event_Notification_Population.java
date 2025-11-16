package Events;

import Core.EventResult;
import Core.GameEvent;
import Core.Piliakalnis;

public class Event_Notification_Population implements GameEvent {

    @Override
    public String getEventText() {
        return "Gyventoju skaiciaus ispejimas";
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.population < 20 || p.population > 120;
    }

    @Override
    public EventResult execute(Piliakalnis p) {
        if (p.population < 20) {
            p.morale -= 2;
            if (p.morale < 0) {p.morale = 0;}
            String text = "DEMESIO! Kieme ir aplink piliakalni retai sutinkama zmoniu. Zmones nerimauja, ar uzteks ranku laukams, gynybai ir amatams.";
            return new EventResult(text);
        } else if (p.population > 120) {
            p.morale += 1;
            if (p.morale > 100) {p.morale = 100;}
            String text = "Piliakalnyje verda gyvenimas, kiemai ir dirbtuves pilnos gyventoju. Zmones tiki, kad turininga bendruomene lengviau atlaikys sunkesnius laikus.";
            return new EventResult(text);
        }
        return new EventResult("");
    }

    @Override
    public boolean isRandom() {
        return false;
    }
}