package Events;

import Core.EventResult;
import Core.Piliakalnis;

public class Event_Raid_Livonians extends BaseEvent {

    private static final int START_YEAR = 1202;
    private static final int END_YEAR = 1235;
    private static final int FAITH_REQUIREMENT = 20;
    private static final int HIGH_DEFENSE_THRESHOLD = 50;
    private static final int MID_DEFENSE_THRESHOLD = 20;
    private static final int CHANCE_PERCENT = 6;

    @Override
    public String getEventText() {
        return "Kalavijuociu reidas";
    }

    @Override
    public boolean canTrigger(Piliakalnis piliakalnis) {
        return piliakalnis.getYear() >= START_YEAR && piliakalnis.getYear() <= END_YEAR && piliakalnis.getFaith() > FAITH_REQUIREMENT;
    }

    @Override
    public EventResult execute(Piliakalnis piliakalnis) {
        int popLoss;
        int foodLoss;
        int moraleLoss;

        if (piliakalnis.getDefense() < MID_DEFENSE_THRESHOLD) {
            popLoss = piliakalnis.getPopulation() / 10;
            foodLoss = piliakalnis.getFood() / 4;
            moraleLoss = 10;
        } else if (piliakalnis.getDefense() <= HIGH_DEFENSE_THRESHOLD) {
            popLoss = piliakalnis.getPopulation() / 20;
            foodLoss = piliakalnis.getFood() / 6;
            moraleLoss = 6;
        } else {
            popLoss = piliakalnis.getPopulation() / 30;
            foodLoss = piliakalnis.getFood() / 8;
            moraleLoss = 3;
        }

        adjustPopulation(piliakalnis, -popLoss);
        adjustFood(piliakalnis, -foodLoss);
        adjustMorale(piliakalnis, -moraleLoss);

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
