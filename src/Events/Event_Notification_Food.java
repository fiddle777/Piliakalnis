package Events;

import Core.EventResult;
import Core.GameEvent;
import Core.Piliakalnis;

public class Event_Notification_Food implements GameEvent {

    @Override
    public String getEventText() {
        return "Maisto atsargu ispejimas";
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.food < p.population || p.food >= p.population * 5;
    }

    @Override
    public EventResult execute(Piliakalnis p) {
        if (p.food < p.population) {
            p.morale -= 2;
            if (p.morale < 0) {p.morale = 0;}
            String text = "DEMESIO! Sandeliuose maista galima suskaiciuoti ant pirstu. Zmones nerimauja del artinciu bado metu.";
            return new EventResult(text);
        } else if (p.food >= p.population * 5) {
            p.morale += 1;
            if (p.morale > 100) {p.morale = 100;}
            String text = "Kloniuose ir sandeliuose matosi gausus derlius. Zmones jauciasi saugiau, nes artimiausiu metu badu nekvepia.";
            return new EventResult(text);
        }
        return new EventResult("");
    }

    @Override
    public boolean isRandom() {
        return false;
    }
}
