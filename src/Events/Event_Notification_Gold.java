package Events;

import Core.EventResult;
import Core.GameConfig;
import Core.GameEvent;
import Core.Piliakalnis;

public class Event_Notification_Gold implements GameEvent {

    private static final int LOW_GOLD_THRESHOLD = 80;
    private static final int HIGH_GOLD_THRESHOLD = 400;
    private static final int MORALE_LOSS = 2;
    private static final int MORALE_GAIN = 1;

    @Override
    public String getEventText() {
        return "Aukso atsargu ispejimas";
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.getGold() < LOW_GOLD_THRESHOLD || p.getGold() >= HIGH_GOLD_THRESHOLD;
    }

    @Override
    public EventResult execute(Piliakalnis p) {
        if (p.getGold() < LOW_GOLD_THRESHOLD) {
            int newMorale = Math.max(0, p.getMorale() - MORALE_LOSS);
            p.setMorale(newMorale);

            String text = "DEMESIO! Izdas tusteja, skoloms pradedama skolintis is svetimu pirkiu.\n"
                    + "Turgavieteje girdisi neramus bambekliu balsai, o amatininkai murma apie valdovo sprendimus.\n"
                    + "Zmones ima abejoti valdzios ekonominiu gabumu.";
            return new EventResult(text);

        } else if (p.getGold() >= HIGH_GOLD_THRESHOLD) {
            int newMorale = Math.min(GameConfig.MAX_MORALE, p.getMorale() + MORALE_GAIN);
            p.setMorale(newMorale);

            String text = "Izdas pilnas, kerdziai ir pirkliai kalba apie turtinga piliakalnio rinka.\n"
                    + "Zmones tiki, kad valdovas zino ka daro.";
            return new EventResult(text);
        }

        return new EventResult("");
    }

    @Override
    public boolean isRandom() {
        return false;
    }
}
