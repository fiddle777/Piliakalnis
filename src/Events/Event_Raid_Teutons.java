package Events;

import Core.EventResult;
import Core.GameEvent;
import Core.Piliakalnis;

import java.util.Random;

public class Event_Raid_Teutons implements GameEvent {

    private final Random rnd = new Random();

    @Override
    public String getEventText() {
        return "Kryziuociu ordino puolimas";
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.year >= 1230 && p.faith > 20 && p.population > 0;
    }

    @Override
    public EventResult execute(Piliakalnis p) {

        int foodLoss = p.food/10;
        int popLoss = p.population / (5 + rnd.nextInt(10)); // pop/5 to pop/14
        int moraleLoss = 6;

        p.food -= foodLoss;
        if (p.food < 0) p.food = 0;

        p.population -= popLoss;
        if (p.population < 0) p.population = 0;

        p.morale -= moraleLoss;
        if (p.morale < 0) p.morale = 0;

        String text = "APGULTIS! Kryziuociu ordinas degina musu zemes!\n"
                + "Balti skydai, geleziniai kryziai ir zirgu trepsejimas skamba kaip mirties varpai.\n"
                + "Piliakalnis atsilaiko, bet pavaldiniai kencia.\n"
                + "zmoniu zuvo " + popLoss + ", o morale smunka -" + moraleLoss + ". Prarasta maisto " + foodLoss + ".";

        return new EventResult(text);
    }

    @Override
    public int getChancePercent() {
        return 8;
    }
}
