package Events;

import Core.Piliakalnis;

public class Event_Notification_Population extends NotificationEvent {

    private static final int LOW_POPULATION_THRESHOLD = 40;
    private static final int HIGH_POPULATION_THRESHOLD = 120;
    private static final int LOW_MORALE_DELTA = -2;
    private static final int HIGH_MORALE_DELTA = 1;
    private static final String LOW_POPULATION_TEXT = "DEMESIO! Piliakalnyje matyti vis daugiau tusciu trobesiu. " +
            "Liko per mazai darbingos liaudies, kad atlaikytu spaudima.";
    private static final String HIGH_POPULATION_TEXT = "Sostapilio lankose nesutelpa visi piemenys, pirkliai ir kariai. " +
            "Piliakalnis dideja ir sutraukia smalsuolius.";

    @Override
    public String getEventText() {
        return "Gyventoju skaiciaus ispejimas";
    }

    @Override
    public boolean canTrigger(Piliakalnis piliakalnis) {
        int population = piliakalnis.getPopulation();
        return population < LOW_POPULATION_THRESHOLD || population >= HIGH_POPULATION_THRESHOLD;
    }

    @Override
    protected NotificationOutcome resolveOutcome(Piliakalnis piliakalnis) {
        if (piliakalnis.getPopulation() < LOW_POPULATION_THRESHOLD) {
            return new NotificationOutcome(LOW_POPULATION_TEXT, LOW_MORALE_DELTA);
        }
        return new NotificationOutcome(HIGH_POPULATION_TEXT, HIGH_MORALE_DELTA);
    }
}
