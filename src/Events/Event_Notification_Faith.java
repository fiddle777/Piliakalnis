package Events;

import Core.Piliakalnis;

public class Event_Notification_Faith extends NotificationEvent {

    private static final int LOW_FAITH_THRESHOLD = 15;
    private static final int HIGH_FAITH_THRESHOLD = 50;
    private static final int LOW_MORALE_DELTA = -5;
    private static final int HIGH_MORALE_DELTA = 1;
    private static final String LOW_FAITH_TEXT = "DEMESIO! Ar pagonybe is viso turi ateiti? " +
            "Zmones slapta kalba apie kalavijuocius ir kryziaus zygio pamokas.";
    private static final String HIGH_FAITH_TEXT = "Pagonys dainuoja apeigines giesmes ir prisiekinja savo protvkam. " +
            "Valdovas stiprina protvyki ir vienybe.";

    @Override
    public String getEventText() {
        return "Tikejimo bukles ispejimas";
    }

    @Override
    public boolean canTrigger(Piliakalnis piliakalnis) {
        int faith = piliakalnis.getFaith();
        return faith < LOW_FAITH_THRESHOLD || faith >= HIGH_FAITH_THRESHOLD;
    }

    @Override
    protected NotificationOutcome resolveOutcome(Piliakalnis piliakalnis) {
        if (piliakalnis.getFaith() < LOW_FAITH_THRESHOLD) {
            return new NotificationOutcome(LOW_FAITH_TEXT, LOW_MORALE_DELTA);
        }
        return new NotificationOutcome(HIGH_FAITH_TEXT, HIGH_MORALE_DELTA);
    }
}
