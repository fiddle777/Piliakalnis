package Events;

import Core.EventResult;
import Core.GameEvent;
import Core.Piliakalnis;

public class Event_Notification_Defense implements GameEvent {
    @Override
    public String getEventText() {
        return "Gynybos bukles ispejimas";
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.defense < 20 || p.defense >= 60;
    }

    @Override
    public EventResult execute(Piliakalnis p) {
        if(p.defense < 20) {
            p.morale -= 2;
            if(p.morale < 0) {p.morale = 0;}
            String text = "DEMESIO! Gynyba labai silpna, zmones nerimauja del galimu uzpuolimu.";
            return new EventResult(text);
        }
        else if(p.defense >= 60) {
            p.morale += 1;
            if(p.morale > 100) {p.morale = 100;}
            String text = "Zvalgyba pranesa, kad piliakalnio gynyba laikoma pakankama, zmones jauciasi saugiau.";
            return new EventResult(text);
        }
        return new EventResult("");
    }

    @Override
    public boolean isRandom() {
        return false;
    }
}
