package Events;

import Core.EventResult;
import Core.GameEvent;
import Core.Piliakalnis;

import java.util.Random;

public class Event_Raid_Teutons implements GameEvent {

    private static final int MIN_YEAR = 1230;
    private static final int MIN_FAITH = 20;
    private static final int MORALE_LOSS = 6;
    private static final int CHANCE_PERCENT = 8;

    private final Random rnd = new Random();

    @Override
    public String getEventText() {
        return "Kryziuociu ordino puolimas";
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.getYear() >= MIN_YEAR
                && p.getFaith() > MIN_FAITH
                && p.getPopulation() > 0;
    }

    @Override
    public EventResult execute(Piliakalnis p) {
        int foodLoss = p.getFood() / 10;
        int popLoss = p.getPopulation() / (5 + rnd.nextInt(10)); // pop/5 to pop/14

        p.setFood(Math.max(0, p.getFood() - foodLoss));
        p.setPopulation(Math.max(0, p.getPopulation() - popLoss));
        p.setMorale(Math.max(0, p.getMorale() - MORALE_LOSS));

        String text = "APGULTIS! Kryziuociu ordinas degina musu zemes!\n"
                + "Balti skydai, geleziniai kryziai ir zirgu trepsejimas skamba kaip mirties varpai.\n"
                + "Piliakalnis atsilaiko, bet pavaldiniai kencia.\n"
                + "Zmoniu zuvo " + popLoss + ", o morale smunka -" + MORALE_LOSS + ". Prarasta maisto " + foodLoss + ".";

        return new EventResult(text);
    }

    @Override
    public int getChancePercent() {
        return CHANCE_PERCENT;
    }
}
