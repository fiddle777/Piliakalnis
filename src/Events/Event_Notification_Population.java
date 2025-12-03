package Events;

import Core.EventResult;
import Core.GameConfig;
import Core.GameEvent;
import Core.Piliakalnis;

public class Event_Notification_Population implements GameEvent {

    private static final int LOW_POP_THRESHOLD = 20;
    private static final int HIGH_POP_THRESHOLD = 120;
    private static final int MORALE_PENALTY = 2;
    private static final int MORALE_BONUS = 1;

    @Override
    public String getEventText() {
        return "Gyventoju skaiciaus ispejimas";
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.getPopulation() < LOW_POP_THRESHOLD || p.getPopulation() > HIGH_POP_THRESHOLD;
    }

    @Override
    public EventResult execute(Piliakalnis p) {
        if (p.getPopulation() < LOW_POP_THRESHOLD) {
            int newMorale = Math.max(0, p.getMorale() - MORALE_PENALTY);
            p.setMorale(newMorale);

            String text = "DEMESIO! Kieme ir aplink piliakalni retai matyti judesys.\n"
                    + "Truksta ranku laukams, gynybai ir amatams – zmones nerimauja.";
            return new EventResult(text);

        } else if (p.getPopulation() > HIGH_POP_THRESHOLD) {
            int newMorale = Math.min(GameConfig.MAX_MORALE, p.getMorale() + MORALE_BONUS);
            p.setMorale(newMorale);

            String text = "Piliakalnyje verda gyvenimas, kiemai ir dirbtuves pilnos gyventoju.\n"
                    + "Zmones tiki, kad turininga bendruomene lengviau atlaikys sunkesnius laikus.";
            return new EventResult(text);
        }

        return new EventResult("");
    }

    @Override
    public boolean isRandom() {
        return false;
    }
}
