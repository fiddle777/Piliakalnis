package Events;

import Core.EventResult;
import Core.GameEvent;
import Core.Piliakalnis;

public class Event_Raid_Looters implements GameEvent {

    @Override
    public String getEventText() {
        return "Plesiku ispuolis turgavieteje";
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.defense < 20 && (p.gold > 0 || p.food > 0);
    }

    @Override
    public EventResult execute(Piliakalnis p) {

        int goldLoss = p.gold/10;
        int foodLoss = p.food/10;
        int moraleLoss = 4;

        p.gold -= goldLoss;
        if (p.gold < 0) p.gold = 0;
        p.food -= foodLoss;
        if (p.food < 0) p.food = 0;
        p.morale -= moraleLoss;
        if (p.morale < 0) p.morale = 0;

        String text = "Plesikai nakti isibrauna i turgaviete.\n"
                + "Sandeliuose dingsta " + goldLoss + " aukso ir " + foodLoss + " maisto.\n"
                + "Zmones nepatenkinti, morale krenta -" + moraleLoss + ".";

        return new EventResult(text);
    }

    @Override
    public int getChancePercent() {
        return 10;
    }
}
