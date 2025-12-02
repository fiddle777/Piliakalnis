package Events;

import Core.Piliakalnis;

public class Event_Notification_Defense extends NotificationEvent {

    private static final int LOW_DEFENSE_THRESHOLD = 10;
    private static final int HIGH_DEFENSE_THRESHOLD = 30;
    private static final int LOW_MORALE_DELTA = -4;
    private static final int HIGH_MORALE_DELTA = 2;
    private static final String LOW_DEFENSE_TEXT = "DEMESIO! Piliakalnis neturi rimtu fortifikaciju. Zmones jauciasi nesaugiai.";
    private static final String HIGH_DEFENSE_TEXT = "Valdovo kariauna ir zemi pylimai sukelia pagarba kaimynam. Zmones pasitiki apsauga.";

    @Override
    public String getEventText() {
        return "Gynybos bukles ispejimas";
    }

    @Override
    public boolean canTrigger(Piliakalnis piliakalnis) {
        int defense = piliakalnis.getDefense();
        return defense <= LOW_DEFENSE_THRESHOLD || defense >= HIGH_DEFENSE_THRESHOLD;
    }

    @Override
    protected NotificationOutcome resolveOutcome(Piliakalnis piliakalnis) {
        if (piliakalnis.getDefense() <= LOW_DEFENSE_THRESHOLD) {
            return new NotificationOutcome(LOW_DEFENSE_TEXT, LOW_MORALE_DELTA);
        }
        return new NotificationOutcome(HIGH_DEFENSE_TEXT, HIGH_MORALE_DELTA);
    }
}
