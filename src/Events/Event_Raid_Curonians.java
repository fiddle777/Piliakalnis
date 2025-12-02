package Events;

import Core.EventResult;
import Core.Piliakalnis;

public class Event_Raid_Curonians extends BaseEvent {

    private static final int FAITH_THRESHOLD = 15;
    private static final int CHANCE_PERCENT = 6;

    @Override
    public String getEventText() {
        return "Kursiu luotai horizonte";
    }

    @Override
    public boolean canTrigger(Piliakalnis piliakalnis) {
        return piliakalnis.getFaith() < FAITH_THRESHOLD;
    }

    @Override
    public EventResult execute(Piliakalnis piliakalnis) {
        int goldLoss = piliakalnis.getGold() / 4;
        int moraleLoss = piliakalnis.getMorale() / 3;
        int popLoss = piliakalnis.getPopulation() / 20;

        adjustGold(piliakalnis, -goldLoss);
        adjustMorale(piliakalnis, -moraleLoss);
        adjustPopulation(piliakalnis, -popLoss);

        String text = "Apgultis! Kursiu luotai upe atplaukia!\n"
                + "isnesamas grobis - prarandate " + goldLoss + " aukso.\n"
                + "Prie lauzo sklinda istorijos apie narsias kautynes.\n"
                + "Morale krenta, -" + moraleLoss + ", zuvo " + popLoss + ".";

        return new EventResult(text);
    }

    @Override
    public int getChancePercent() {
        return CHANCE_PERCENT;
    }
}
