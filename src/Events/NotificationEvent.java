package Events;

import Core.EventResult;
import Core.Piliakalnis;

public abstract class NotificationEvent extends BaseEvent {

    @Override
    public final EventResult execute(Piliakalnis piliakalnis) {
        NotificationOutcome outcome = resolveOutcome(piliakalnis);
        adjustMorale(piliakalnis, outcome.moraleDelta());
        return new EventResult(outcome.story());
    }

    @Override
    public boolean isRandom() {
        return false;
    }

    protected abstract NotificationOutcome resolveOutcome(Piliakalnis piliakalnis);

    protected record NotificationOutcome(String story, int moraleDelta) { }
}
