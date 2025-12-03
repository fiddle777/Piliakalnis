package Events;

import Core.EventResult;
import Core.GameConfig;
import Core.GameEvent;
import Core.Piliakalnis;

public class Event_Notification_Faith implements GameEvent {

    private static final int LOW_FAITH_THRESHOLD = 10;
    private static final int HIGH_FAITH_THRESHOLD = 40;
    private static final int MORALE_PENALTY = 2;
    private static final int MORALE_BONUS = 1;

    @Override
    public String getEventText() {
        return "Tikejimo bukles ispejimas";
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.getFaith() < LOW_FAITH_THRESHOLD || p.getFaith() >= HIGH_FAITH_THRESHOLD;
    }

    @Override
    public EventResult execute(Piliakalnis p) {
        if (p.getFaith() < LOW_FAITH_THRESHOLD) {
            int newMorale = Math.max(0, p.getMorale() - MORALE_PENALTY);
            p.setMorale(newMorale);

            String text = "DEMESIO! Zmones ima abejoti dievu palankumu, aukuras sventvieteje retai dega.\n"
                    + "Valstieciu nuotaika prasteja.";
            return new EventResult(text);

        } else if (p.getFaith() >= HIGH_FAITH_THRESHOLD) {
            int newMorale = Math.min(GameConfig.MAX_MORALE, p.getMorale() + MORALE_BONUS);
            p.setMorale(newMorale);

            String text = "Aukuras dega, apeigos vyksta reguliarai.\n"
                    + "Tikejimas stiprina bendruomene ir palaiko morale.";
            return new EventResult(text);
        }

        return new EventResult("");
    }

    @Override
    public boolean isRandom() {
        return false;
    }
}
