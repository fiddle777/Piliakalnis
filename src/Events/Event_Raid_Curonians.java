package Events;

import Core.EventResult;
import Core.GameEvent;
import Core.Piliakalnis;

public class Event_Raid_Curonians implements GameEvent {

    private static final int MAX_FAITH_FOR_RAID = 15;
    private static final int GOLD_LOSS_DIVISOR = 4;
    private static final int MORALE_LOSS_DIVISOR = 3;
    private static final int POP_LOSS_DIVISOR = 20;
    private static final int CHANCE_PERCENT = 6;

    @Override
    public String getEventText() {
        return "Kursiu luotai horizonte";
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.getFaith() < MAX_FAITH_FOR_RAID;
    }

    @Override
    public EventResult execute(Piliakalnis p) {
        int goldLoss = p.getGold() / GOLD_LOSS_DIVISOR;
        int moraleLoss = p.getMorale() / MORALE_LOSS_DIVISOR;
        int popLoss = p.getPopulation() / POP_LOSS_DIVISOR;

        p.setGold(Math.max(0, p.getGold() - goldLoss));
        p.setMorale(p.getMorale() + moraleLoss);
        if (p.getMorale() < 0) {
            p.setMorale(0);
        }
        p.setPopulation(Math.max(0, p.getPopulation() - popLoss));

        String text = "Apgultis! Kursiu luotai upe atplaukia!\n"
                + "Isnesamas grobis - prarandate " + goldLoss + " aukso.\n"
                + "Prie lauzo sklinda istorijos apie narsias kautynes.\n"
                + "Morale krenta, -" + moraleLoss + ", zuvo " + popLoss + ".";

        return new EventResult(text);
    }

    @Override
    public int getChancePercent() {
        return CHANCE_PERCENT;
    }
}
