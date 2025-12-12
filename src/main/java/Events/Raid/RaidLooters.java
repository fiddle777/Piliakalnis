package Events.Raid;

import Core.Piliakalnis;

public class RaidLooters extends BaseRaidEvent {

    private static final int MAX_DEFENSE_FOR_RAID = 20;
    private static final int GOLD_LOSS_DIVISOR = 10;
    private static final int FOOD_LOSS_DIVISOR = 10;
    private static final int MORALE_LOSS = 4;
    private static final int CHANCE_PERCENT = 10;

    public RaidLooters() {
        super("Plesiku ispuolis turgavieteje", CHANCE_PERCENT);
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.getDefense() < MAX_DEFENSE_FOR_RAID
                && (p.getGold() > 0 || p.getFood() > 0);
    }

    @Override
    protected int calculateGoldLoss(Piliakalnis p) {
        return p.getGold() / GOLD_LOSS_DIVISOR;
    }

    @Override
    protected int calculateFoodLoss(Piliakalnis p) {
        return p.getFood() / FOOD_LOSS_DIVISOR;
    }

    @Override
    protected int calculateMoraleLoss(Piliakalnis p) {
        return MORALE_LOSS;
    }

    @Override
    protected int calculatePopulationLoss(Piliakalnis p) {
        return 0;
    }

    @Override
    protected String buildStoryText(int goldLoss, int foodLoss, int moraleLoss, int popLoss) {
        return "Plesikai nakti isibrauna i turgaviete.\n"
                + "Sandeliuose dingsta " + goldLoss + " aukso ir " + foodLoss + " maisto.\n"
                + "Zmones nepatenkinti, morale krenta -" + moraleLoss + ".";
    }
}
