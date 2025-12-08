package Events;

import Core.Piliakalnis;

public class Event_Raid_Livonians extends BaseRaidEvent {

    private static final int START_YEAR = 1202;
    private static final int END_YEAR = 1235;
    private static final int MIN_FAITH = 20;

    private static final int DEF_LOW = 20;
    private static final int DEF_MED = 50;

    private static final int HIGH_POP_DIV = 10;
    private static final int MID_POP_DIV  = 20;
    private static final int LOW_POP_DIV  = 30;

    private static final int HIGH_FOOD_DIV = 4;
    private static final int MID_FOOD_DIV  = 6;
    private static final int LOW_FOOD_DIV  = 8;

    private static final int HIGH_MORALE_LOSS = 10;
    private static final int MID_MORALE_LOSS  = 6;
    private static final int LOW_MORALE_LOSS  = 3;

    private static final int CHANCE_PERCENT = 6;

    public Event_Raid_Livonians() {
        super("Kalavijuociu reidas", CHANCE_PERCENT);
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.getYear() >= START_YEAR
                && p.getYear() <= END_YEAR
                && p.getFaith() > MIN_FAITH;
    }

    private int getPopLoss(Piliakalnis p) {
        int defense = p.getDefense();
        if (defense < DEF_LOW) {
            return p.getPopulation() / HIGH_POP_DIV;
        } else if (defense <= DEF_MED) {
            return p.getPopulation() / MID_POP_DIV;
        } else {
            return p.getPopulation() / LOW_POP_DIV;
        }
    }

    private int getFoodLoss(Piliakalnis p) {
        int defense = p.getDefense();
        if (defense < DEF_LOW) {
            return p.getFood() / HIGH_FOOD_DIV;
        } else if (defense <= DEF_MED) {
            return p.getFood() / MID_FOOD_DIV;
        } else {
            return p.getFood() / LOW_FOOD_DIV;
        }
    }

    private int getMoraleLoss(Piliakalnis p) {
        int defense = p.getDefense();
        if (defense < DEF_LOW) {
            return HIGH_MORALE_LOSS;
        } else if (defense <= DEF_MED) {
            return MID_MORALE_LOSS;
        } else {
            return LOW_MORALE_LOSS;
        }
    }

    @Override
    protected int calculateGoldLoss(Piliakalnis p) {
        return 0;
    }

    @Override
    protected int calculateFoodLoss(Piliakalnis p) {
        return getFoodLoss(p);
    }

    @Override
    protected int calculateMoraleLoss(Piliakalnis p) {
        return getMoraleLoss(p);
    }

    @Override
    protected int calculatePopulationLoss(Piliakalnis p) {
        return getPopLoss(p);
    }

    @Override
    protected String buildStoryText(int goldLoss, int foodLoss, int moraleLoss, int popLoss) {
        // goldLoss visada 0 siame reide
        return "APGULTIS! Kalavijuociu pulkai uzslenka is Livonijos, tikrindami nepavergtu zemiu tvirtuma.\n"
                + "Kardeliai kieto plieno isbando tvirtoves ir pavaldiniu stipruma.\n"
                + (popLoss > 0 ? "Zuvo " + popLoss + " zmoniu.\n" : "")
                + (foodLoss > 0 ? "Sandeliuose prarandama " + foodLoss + " maisto.\n" : "")
                + (moraleLoss > 0 ? "Bendruomenes morale krenta -" + moraleLoss + ".\n" : "");
    }
}
