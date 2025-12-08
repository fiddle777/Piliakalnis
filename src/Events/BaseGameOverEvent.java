package Events;

import Core.EventResult;
import Core.GameEvent;
import Core.Piliakalnis;

public abstract class BaseGameOverEvent implements GameEvent {

    private final String eventText;

    protected BaseGameOverEvent(String eventText) {
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
    public final EventResult execute(Piliakalnis p) {
        String text = buildGameOverText(p);
        return new EventResult(text, true);
    }

    @Override
    public abstract boolean canTrigger(Piliakalnis p);
    protected abstract String buildGameOverText(Piliakalnis p);
}
