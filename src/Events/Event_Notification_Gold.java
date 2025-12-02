package Events;

import Core.Piliakalnis;

public class Event_Notification_Gold extends NotificationEvent {

    private static final int LOW_GOLD_THRESHOLD = 80;
    private static final int HIGH_GOLD_THRESHOLD = 400;
    private static final int LOW_MORALE_DELTA = -2;
    private static final int HIGH_MORALE_DELTA = 1;
    private static final String LOW_GOLD_TEXT = "DEMESIO! Izdas tusteja, skoloms pradedama ieskoti pasiteisinimu. " +
            "Zmones ima abejoti valdzios ekonominiu gabumu.";
    private static final String HIGH_GOLD_TEXT = "Izdas pilnas, kerdziai ir pirkliai kalba apie turtinga piliakalnio rinka. " +
            "Zmones tiki, kad valdovas zino ka daro.";

    @Override
    public String getEventText() {
        return "Aukso atsargu ispejimas";
    }

    @Override
    public boolean canTrigger(Piliakalnis piliakalnis) {
        int gold = piliakalnis.getGold();
        return gold < LOW_GOLD_THRESHOLD || gold >= HIGH_GOLD_THRESHOLD;
    }

    @Override
    protected NotificationOutcome resolveOutcome(Piliakalnis piliakalnis) {
        if (piliakalnis.getGold() < LOW_GOLD_THRESHOLD) {
            return new NotificationOutcome(LOW_GOLD_TEXT, LOW_MORALE_DELTA);
        }
        return new NotificationOutcome(HIGH_GOLD_TEXT, HIGH_MORALE_DELTA);
    }
}
