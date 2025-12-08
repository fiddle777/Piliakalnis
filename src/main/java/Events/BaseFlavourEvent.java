package Events;

import Core.EventResult;
import Core.GameConfig;
import Core.GameEvent;
import Core.Piliakalnis;

public abstract class BaseFlavourEvent implements GameEvent {

    private final String eventText;
    private final int chancePercent;

    protected BaseFlavourEvent(String eventText, int chancePercent) {
        this.eventText = eventText;
        this.chancePercent = chancePercent;
    }

    @Override
    public String getEventText() {
        return eventText;
    }

    @Override
    public boolean isRandom() {
        return true;
    }

    @Override
    public int getChancePercent() {
        return chancePercent;
    }

    @Override
    public abstract boolean canTrigger(Piliakalnis p);
    @Override
    public abstract EventResult execute(Piliakalnis p);

    protected void changeMorale(Piliakalnis p, int delta) {
        int value = p.getMorale() + delta;
        if (value < 0) value = 0;
        if (value > GameConfig.MAX_MORALE) value = GameConfig.MAX_MORALE;
        p.setMorale(value);
    }

    protected void changeFaith(Piliakalnis p, int delta) {
        int value = p.getFaith() + delta;
        if (value < 0) value = 0;
        if (value > GameConfig.MAX_FAITH) value = GameConfig.MAX_FAITH;
        p.setFaith(value);
    }

    protected void changeGold(Piliakalnis p, int delta) {
        int value = p.getGold() + delta;
        if (value < 0) value = 0;
        p.setGold(value);
    }

    protected void changeFood(Piliakalnis p, int delta) {
        int value = p.getFood() + delta;
        if (value < 0) value = 0;
        p.setFood(value);
    }

    protected void changeDefense(Piliakalnis p, int delta) {
        int value = p.getDefense() + delta;
        if (value < 0) value = 0;
        p.setDefense(value);
    }
}
