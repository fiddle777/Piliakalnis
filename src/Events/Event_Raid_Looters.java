package Events;

import Core.EventResult;
import Core.Piliakalnis;

public class Event_Raid_Looters extends BaseEvent {

    private static final int DEFENSE_THRESHOLD = 20;
    private static final int MORALE_LOSS = 4;
    private static final int CHANCE_PERCENT = 10;

    @Override
    public String getEventText() {
        return "Plesiku ispuolis turgavieteje";
    }

    @Override
    public boolean canTrigger(Piliakalnis piliakalnis) {
        return piliakalnis.getDefense() < DEFENSE_THRESHOLD && (piliakalnis.getGold() > 0 || piliakalnis.getFood() > 0);
    }

    @Override
    public EventResult execute(Piliakalnis piliakalnis) {
        int goldLoss = piliakalnis.getGold() / 10;
        int foodLoss = piliakalnis.getFood() / 10;

        adjustGold(piliakalnis, -goldLoss);
        adjustFood(piliakalnis, -foodLoss);
        adjustMorale(piliakalnis, -MORALE_LOSS);

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
