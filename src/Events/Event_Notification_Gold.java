package Events;

import Core.EventResult;
import Core.GameEvent;
import Core.Piliakalnis;

public class Event_Notification_Gold implements GameEvent {

    @Override
    public String getEventText() {
        return "Aukso atsargu ispejimas";
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.gold < 80 || p.gold >= 400;
    }

    @Override
    public EventResult execute(Piliakalnis p) {
        if (p.gold < 80) {
            p.morale -= 2;
            if (p.morale < 0) {p.morale = 0;}
            String text = "DEMESIO! Izdas tusteja, skoloms pradedama ieskoti pasiteisinimu. Zmones ima abejoti valdzios ekonominiu gabumu.";
            return new EventResult(text);
        } else if (p.gold >= 400) {
            p.morale += 1;
            if (p.morale > 100) {p.morale = 100;}
            String text = "Izdas pilnas, kerdziai ir pirkliai kalba apie turtinga piliakalnio rinka. Zmones tiki, kad valdovas zino ka daro.";
            return new EventResult(text);
        }
        return new EventResult("");
    }

    @Override
    public boolean isRandom() {
        return false;
    }
}