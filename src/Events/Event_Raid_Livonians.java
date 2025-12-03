package Events;

import Core.EventResult;
import Core.GameEvent;
import Core.Piliakalnis;

public class Event_Raid_Livonians implements GameEvent {

    private static final int START_YEAR = 1202;
    private static final int END_YEAR = 1235;
    private static final int MIN_FAITH = 20;
    private static final int DEF_LOW = 20;
    private static final int DEF_MED = 50;
    private static final int CHANCE_PERCENT = 6;

    @Override
    public String getEventText() {
        return "Kalavijuociu reidas";
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.getYear() >= START_YEAR
                && p.getYear() <= END_YEAR
                && p.getFaith() > MIN_FAITH;
    }

    @Override
    public EventResult execute(Piliakalnis p) {
        int popLoss;
        int foodLoss;
        int moraleLoss;

        if (p.getDefense() < DEF_LOW) {
            popLoss = p.getPopulation() / 10;
            foodLoss = p.getFood() / 4;
            moraleLoss = 10;
        } else if (p.getDefense() <= DEF_MED) {
            popLoss = p.getPopulation() / 20;
            foodLoss = p.getFood() / 6;
            moraleLoss = 6;
        } else {
            popLoss = p.getPopulation() / 30;
            foodLoss = p.getFood() / 8;
            moraleLoss = 3;
        }

        p.setPopulation(Math.max(0, p.getPopulation() - popLoss));
        p.setFood(Math.max(0, p.getFood() - foodLoss));
        p.setMorale(Math.max(0, p.getMorale() - moraleLoss));

        String text = "APGULTIS! Kalavijuociu pulkai uzslenka is Livonijos, tikrindami nepavergtu zemiu tvirtuma.\n"
                + "Kardeliai kieto plieno isbando tvirtoves ir pavaldiniu stipruma.\n"
                + (popLoss > 0 ? "Zuvo " + popLoss + " zmoniu.\n" : "")
                + (foodLoss > 0 ? "Sandeliuose prarandama " + foodLoss + " maisto.\n" : "")
                + (moraleLoss > 0 ? "Bendruomenes morale krenta -" + moraleLoss + ".\n" : "");

        return new EventResult(text);
    }

    @Override
    public int getChancePercent() {
        return CHANCE_PERCENT;
    }
}
