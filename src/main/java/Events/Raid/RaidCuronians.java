package Events.Raid;

import Core.Piliakalnis;

public class RaidCuronians extends BaseRaidEvent {

    private static final int MAX_FAITH_FOR_RAID = 15;
    private static final int GOLD_LOSS_DIVISOR = 4;
    private static final int MORALE_LOSS_DIVISOR = 3;
    private static final int POP_LOSS_DIVISOR = 20;
    private static final int CHANCE_PERCENT = 6;

    public RaidCuronians() {
        super("Kursiu luotai horizonte", CHANCE_PERCENT);
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.getFaith() < MAX_FAITH_FOR_RAID;
    }

    @Override
    protected int calculateGoldLoss(Piliakalnis p) {
        return p.getGold() / GOLD_LOSS_DIVISOR;
    }

    @Override
    protected int calculateFoodLoss(Piliakalnis p) {
        // Siame reide maistas nelieciamas
        return 0;
    }

    @Override
    protected int calculateMoraleLoss(Piliakalnis p) {
        return p.getMorale() / MORALE_LOSS_DIVISOR;
    }

    @Override
    protected int calculatePopulationLoss(Piliakalnis p) {
        return p.getPopulation() / POP_LOSS_DIVISOR;
    }

    @Override
    protected String buildStoryText(int goldLoss, int foodLoss, int moraleLoss, int popLoss) {
        return "Apagultis! Kursiu luotai upe atplaukia!\n"
                + "Isnesamas grobis - prarandate " + goldLoss + " aukso.\n"
                + "Prie lauzo sklinda istorijos apie narsias kautynes.\n"
                + "Morale krenta -" + moraleLoss + ", zuvo " + popLoss + ".";
    }
}
