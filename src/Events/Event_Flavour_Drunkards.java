package Events;

import Core.EventResult;
import Core.GameEvent;
import Core.Piliakalnis;

import java.util.Random;

public class Event_Flavour_Drunkards implements GameEvent {

    private static final int MIN_POPULATION = 16;
    private static final int MIN_GOLD = 10;
    private static final int SMALL_MORALE_LOSS = 1;
    private static final int BIG_MORALE_LOSS = 2;
    private static final int SMALL_GOLD_LOSS = 10;
    private static final int BIG_GOLD_LOSS = 20;
    private static final int CHANCE_PERCENT = 7;

    private final Random rnd = new Random();

    @Override
    public String getEventText() {
        return "Girti pagonys siaucia";
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.getPopulation() >= MIN_POPULATION && p.getGold() >= MIN_GOLD;
    }

    @Override
    public EventResult execute(Piliakalnis p) {
        int moraleLoss;
        if (rnd.nextBoolean()) {
            moraleLoss = SMALL_MORALE_LOSS;
        } else {
            moraleLoss = BIG_MORALE_LOSS;
        }

        int goldLoss;
        if (rnd.nextBoolean()) {
            goldLoss = SMALL_GOLD_LOSS;
        } else {
            goldLoss = BIG_GOLD_LOSS;
        }

        p.setMorale(p.getMorale() - moraleLoss);
        if (p.getMorale() < 0) {
            p.setMorale(0);
        }

        p.setGold(p.getGold() - goldLoss);
        if (p.getGold() < 0) {
            p.setGold(0);
        }

        String text = "Po ilgos puotos keli uzsivele pagonys nusprende padegti lauko tualetus,\n" +
                "protestuodami pries per dideli tvarkos kieki. Kvapas ir kaimas abudu kencia.\n" +
                "Tvarkos atstatymas kainuoja " + goldLoss + " aukso, o smarve " +
                "sumenkina morale (-" + moraleLoss + ").";

        return new EventResult(text);
    }

    @Override
    public int getChancePercent() {
        return CHANCE_PERCENT;
    }
}
