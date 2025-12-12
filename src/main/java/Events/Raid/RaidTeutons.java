package Events.Raid;

import Core.Piliakalnis;

import java.util.Random;

public class RaidTeutons extends BaseRaidEvent {

    private static final int MIN_YEAR = 1230;
    private static final int MIN_FAITH = 20;
    private static final int MORALE_LOSS = 6;
    private static final int FOOD_LOSS_DIVISOR = 10;
    private static final int CHANCE_PERCENT = 8;

    private final Random rnd = new Random();

    public RaidTeutons() {
        super("Kryziuociu ordino puolimas", CHANCE_PERCENT);
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.getYear() >= MIN_YEAR
                && p.getFaith() > MIN_FAITH
                && p.getPopulation() > 0;
    }

    @Override
    protected int calculateGoldLoss(Piliakalnis p) {
        return 0;
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
        int base = 5 + rnd.nextInt(10); // [5; 14]
        return p.getPopulation() / base;
    }

    @Override
    protected String buildStoryText(int goldLoss, int foodLoss, int moraleLoss, int popLoss) {
        return "APGULTIS! Kryziuociu ordinas degina musu zemes!\n"
                + "Balti skydai, geleziniai kryziai ir zirgu trepsejimas skamba kaip mirties varpai.\n"
                + "Piliakalnis atsilaiko, bet pavaldiniai kencia.\n"
                + "Zmoniu zuvo " + popLoss + ", o morale smunka -" + moraleLoss + ". Prarasta maisto " + foodLoss + ".";
    }
}
