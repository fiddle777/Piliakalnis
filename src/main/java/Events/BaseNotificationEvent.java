package Events;

import Core.EventResult;
import Core.GameEvent;
import Core.Piliakalnis;

public abstract class BaseNotificationEvent implements GameEvent {

    private final String eventText;

    protected BaseNotificationEvent(String eventText) {
        this.eventText = eventText;
    }

    @Override
    public String getEventText() {
        return eventText;
    }

    @Override
    public boolean isRandom() {
        return false;
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        int value = getCurrentValue(p);
        return value < getLowThreshold() || value >= getHighThreshold();
    }

    @Override
    public EventResult execute(Piliakalnis p) {
        int value = getCurrentValue(p);

        if (value < getLowThreshold()) {
            applyLowEffect(p);
            return new EventResult(getLowMessage());
        }

        if (value >= getHighThreshold()) {
            applyHighEffect(p);
            return new EventResult(getHighMessage());
        }

        return new EventResult("");
    }

    protected abstract int getCurrentValue(Piliakalnis p);

    protected abstract int getLowThreshold();

    protected abstract int getHighThreshold();

    protected abstract void applyLowEffect(Piliakalnis p);

    protected abstract void applyHighEffect(Piliakalnis p);

    protected abstract String getLowMessage();

    protected abstract String getHighMessage();
}
