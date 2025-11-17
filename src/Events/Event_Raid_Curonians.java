package Events;

import Core.EventResult;
import Core.GameEvent;
import Core.Piliakalnis;

public class Event_Raid_Curonians implements GameEvent {

    @Override
    public String getEventText() {
        return "Kursiu luotai horizonte";
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.faith < 15;
    }

    @Override
    public EventResult execute(Piliakalnis p) {

        int goldLoss = p.gold/4;
        int moraleLoss = p.morale/3;
        int popLoss = p.population/20;

        p.gold -= goldLoss;
        if (p.gold < 0) p.gold = 0;
        p.morale += moraleLoss;
        if (p.morale < 0) p.morale = 0;
        p.population -= popLoss;
        if (p.population < 0) p.population = 0;

        String text = "Apgultis! Kursiu luotai upe atplaukia!\n"
                + "isnesamas grobis - prarandate " + goldLoss + " aukso.\n"
                + "prie lauzo sklinda istorijos apie narsias kautynes.\n"
                + "morale krenta, -" + moraleLoss + ", zuvo " + popLoss + ".";

        return new EventResult(text);
    }

    @Override
    public int getChancePercent() {
        return 6;
    }
}
