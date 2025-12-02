package Events;

import Core.EventResult;
import Core.GameEvent;
import Core.Piliakalnis;

public class Event_Notification_Defense implements GameEvent {

    private static final int LOW_DEFENSE_THRESHOLD = 20;
    private static final int HIGH_DEFENSE_THRESHOLD = 60;
    private static final int LOW_DEFENSE_MORALE_LOSS = 2;
    private static final int HIGH_DEFENSE_MORALE_GAIN = 1;

    @Override
    public String getEventText() {
        return "Gynybos bukles ispejimas";
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.getDefense() < LOW_DEFENSE_THRESHOLD ||
                p.getDefense() >= HIGH_DEFENSE_THRESHOLD;
    }

    @Override
    public EventResult execute(Piliakalnis p) {

        // CASE 1: Defense < 20 → morale -2 (clamped at 0)
        if (p.getDefense() < LOW_DEFENSE_THRESHOLD) {
            p.setMorale(p.getMorale() - LOW_DEFENSE_MORALE_LOSS);
            if (p.getMorale() < 0) {
                p.setMorale(0);
            }
            String text = "DEMESIO! Gynyba labai silpna, zmones nerimauja del galimu uzpuolimu.";
            return new EventResult(text);
        }

        // CASE 2: Defense >= 60 → morale +1 (clamped at 100)
        if (p.getDefense() >= HIGH_DEFENSE_THRESHOLD) {
            p.setMorale(p.getMorale() + HIGH_DEFENSE_MORALE_GAIN);
            if (p.getMorale() > 100) {
                p.setMorale(100);
            }
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
