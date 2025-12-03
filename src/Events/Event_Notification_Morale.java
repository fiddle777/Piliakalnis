package Events;

import Core.EventResult;
import Core.GameConfig;
import Core.GameEvent;
import Core.Piliakalnis;

public class Event_Notification_Morale implements GameEvent {

    private static final int LOW_MORALE_THRESHOLD = 25;
    private static final int HIGH_MORALE_THRESHOLD = 75;
    private static final int MORALE_PENALTY = 2;
    private static final int MORALE_BONUS = 1;

    @Override
    public String getEventText() {
        return "Morales bukles ispejimas";
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.getMorale() < LOW_MORALE_THRESHOLD || p.getMorale() >= HIGH_MORALE_THRESHOLD;
    }

    @Override
    public EventResult execute(Piliakalnis p) {
        if (p.getMorale() < LOW_MORALE_THRESHOLD) {
            int newMorale = Math.max(0, p.getMorale() - MORALE_PENALTY);
            p.setMorale(newMorale);

            String text = "DEMESIO! Turgavieteje ir prie lauzu girdisi vis daugiau karciu zodziu ir nusivylimo.\n"
                    + "Kai kurie pavaldiniai ima murmeti apie neteisingus sprendimus ir prasta dali.\n"
                    + "Jei niekas nesikeis, gali kilti rimtesni neramumai.";
            return new EventResult(text);

        } else if (p.getMorale() >= HIGH_MORALE_THRESHOLD) {
            int newMorale = Math.min(GameConfig.MAX_MORALE, p.getMorale() + MORALE_BONUS);
            p.setMorale(newMorale);

            String text = "Zmones kalba apie teisinga ir tvirta valdzia.\n"
                    + "Piliakalnyje tvyro pasitikejimo nuotaika.";
            return new EventResult(text);
        }

        return new EventResult("");
    }

    @Override
    public boolean isRandom() {
        return false;
    }
}
