package Events;

import Core.EventResult;
import Core.Piliakalnis;

import java.util.Random;

public class Event_Raid_Teutons extends BaseEvent {

    private static final int START_YEAR = 1230;
    private static final int MIN_FAITH = 21;
    private static final int MIN_POPULATION = 1;
    private static final int CHANCE_PERCENT = 8;
    private static final int BASE_MORALE_LOSS = 6;
    private static final int FOOD_LOSS_DIVISOR = 10;
    private static final int POP_LOSS_DIVISOR_BASE = 5;
    private static final int POP_LOSS_VARIATION = 10;

    private final Random rnd = new Random();

    @Override
    public String getEventText() {
        return "Kryziuociu ordino puolimas";
    }

    @Override
    public boolean canTrigger(Piliakalnis piliakalnis) {
        return piliakalnis.getYear() >= START_YEAR && piliakalnis.getFaith() >= MIN_FAITH && piliakalnis.getPopulation() >= MIN_POPULATION;
    }

    @Override
    public EventResult execute(Piliakalnis piliakalnis) {
        int foodLoss = piliakalnis.getFood() / FOOD_LOSS_DIVISOR;
        int popLoss = piliakalnis.getPopulation() / (POP_LOSS_DIVISOR_BASE + rnd.nextInt(POP_LOSS_VARIATION));

        adjustFood(piliakalnis, -foodLoss);
        adjustPopulation(piliakalnis, -popLoss);
        adjustMorale(piliakalnis, -BASE_MORALE_LOSS);

        String text = "APGULTIS! Kryziuociu ordinas degina musu zemes!\n"
                + "Balti skydai, geleziniai kryziai ir zirgu trepsejimas skamba kaip mirties varpai.\n"
                + "Piliakalnis atsilaiko, bet pavaldiniai kencia.\n"
                + "zmoniu zuvo " + popLoss + ", o morale smunka -" + BASE_MORALE_LOSS + ". Prarasta maisto " + foodLoss + ".";

        return new EventResult(text);
    }

    @Override
    public int getChancePercent() {
        return CHANCE_PERCENT;
    }
}
