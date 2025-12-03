package Events;

import Core.EventResult;
import Core.GameEvent;
import Core.Piliakalnis;

public class Event_Raid_Looters implements GameEvent {

    private static final int MAX_DEFENSE_FOR_RAID = 20;
    private static final int GOLD_LOSS_DIVISOR = 10;
    private static final int FOOD_LOSS_DIVISOR = 10;
    private static final int MORALE_LOSS = 4;
    private static final int CHANCE_PERCENT = 10;

    @Override
    public String getEventText() {
        return "Plesiku ispuolis turgavieteje";
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.getDefense() < MAX_DEFENSE_FOR_RAID
                && (p.getGold() > 0 || p.getFood() > 0);
    }

    @Override
    public EventResult execute(Piliakalnis p) {
        int goldLoss = p.getGold() / GOLD_LOSS_DIVISOR;
        int foodLoss = p.getFood() / FOOD_LOSS_DIVISOR;

        p.setGold(Math.max(0, p.getGold() - goldLoss));
        p.setFood(Math.max(0, p.getFood() - foodLoss));
        p.setMorale(Math.max(0, p.getMorale() - MORALE_LOSS));

        String text = "Plesikai nakti isibrauna i turgaviete.\n"
                + "Sandeliuose dingsta " + goldLoss + " aukso ir " + foodLoss + " maisto.\n"
                + "Zmones nepatenkinti, morale krenta -" + MORALE_LOSS + ".";

        return new EventResult(text);
    }

    @Override
    public int getChancePercent() {
        return CHANCE_PERCENT;
    }
}
