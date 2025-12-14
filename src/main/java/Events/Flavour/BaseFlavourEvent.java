package Events.Flavour;

import Core.Results.EventResult;
import Core.GameConfig;
import Core.Engine.GameEvent;
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
        p.setMorale(p.getMorale() + delta);
    }

    protected void changeFaith(Piliakalnis p, int delta) {
        p.setFaith(p.getFaith() + delta);
    }

    protected void changeGold(Piliakalnis p, int delta) {
        p.setGold(p.getGold() + delta);
    }

    protected void changeFood(Piliakalnis p, int delta) {
        p.setFood(p.getFood() + delta);
    }

    protected void changeDefense(Piliakalnis p, int delta) {
        p.setDefense(p.getDefense() + delta);
    }
}
