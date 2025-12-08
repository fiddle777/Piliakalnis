package Events;

import Core.EventResult;
import Core.GameEvent;
import Core.Piliakalnis;

public abstract class BaseRaidEvent implements GameEvent {

    private final String eventText;
    private final int chancePercent;

    protected BaseRaidEvent(String eventText, int chancePercent) {
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
    public EventResult execute(Piliakalnis p) {
        int goldLoss = Math.max(0, calculateGoldLoss(p));
        int foodLoss = Math.max(0, calculateFoodLoss(p));
        int moraleLoss = Math.max(0, calculateMoraleLoss(p));
        int popLoss = Math.max(0, calculatePopulationLoss(p));

        p.setGold(Math.max(0, p.getGold() - goldLoss));
        p.setFood(Math.max(0, p.getFood() - foodLoss));
        p.setMorale(Math.max(0, p.getMorale() - moraleLoss));
        p.setPopulation(Math.max(0, p.getPopulation() - popLoss));

        String story = buildStoryText(goldLoss, foodLoss, moraleLoss, popLoss);
        return new EventResult(story);
    }

    public abstract boolean canTrigger(Piliakalnis p);

    protected abstract int calculateGoldLoss(Piliakalnis p);

    protected abstract int calculateFoodLoss(Piliakalnis p);

    protected abstract int calculateMoraleLoss(Piliakalnis p);

    protected abstract int calculatePopulationLoss(Piliakalnis p);

    protected abstract String buildStoryText(int goldLoss, int foodLoss, int moraleLoss, int popLoss);
}
