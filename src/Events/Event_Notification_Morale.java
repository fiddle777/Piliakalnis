package Events;

import Core.Piliakalnis;

public class Event_Notification_Morale extends NotificationEvent {

    private static final int LOW_MORALE_THRESHOLD = 25;
    private static final int HIGH_MORALE_THRESHOLD = 75;
    private static final int LOW_MORALE_DELTA = -2;
    private static final int HIGH_MORALE_DELTA = 1;
    private static final String LOW_MORALE_TEXT = "DEMESIO! Turgavieteje ir prie lauzu girdisi nepasitenkinimo murmejimas. " +
            "Jei niekas nesikeis, gali kilti rimtesni neramumai.";
    private static final String HIGH_MORALE_TEXT = "Zmones kalba apie teisinga ir tvirta valdzia. Piliakalnyje tvyro pasitikejimo nuotaika.";

    @Override
    public String getEventText() {
        return "Morales bukles ispejimas";
    }

    @Override
    public boolean canTrigger(Piliakalnis piliakalnis) {
        int morale = piliakalnis.getMorale();
        return morale < LOW_MORALE_THRESHOLD || morale >= HIGH_MORALE_THRESHOLD;
    }

    @Override
    protected NotificationOutcome resolveOutcome(Piliakalnis piliakalnis) {
        if (piliakalnis.getMorale() < LOW_MORALE_THRESHOLD) {
            return new NotificationOutcome(LOW_MORALE_TEXT, LOW_MORALE_DELTA);
        }
        return new NotificationOutcome(HIGH_MORALE_TEXT, HIGH_MORALE_DELTA);
    }
}
