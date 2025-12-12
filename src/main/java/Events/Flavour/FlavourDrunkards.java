package Events.Flavour;

import Core.Results.EventResult;
import Core.Piliakalnis;

import java.util.Random;

public class FlavourDrunkards extends BaseFlavourEvent {

    private static final int MIN_POPULATION = 16;
    private static final int MIN_GOLD = 10;
    private static final int SMALL_MORALE_LOSS = 1;
    private static final int BIG_MORALE_LOSS = 2;
    private static final int SMALL_GOLD_LOSS = 10;
    private static final int BIG_GOLD_LOSS = 20;
    private static final int CHANCE_PERCENT = 7;

    private final Random rnd = new Random();

    public FlavourDrunkards() {
        super("Girti pagonys siaucia", CHANCE_PERCENT);
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.getPopulation() >= MIN_POPULATION && p.getGold() >= MIN_GOLD;
    }

    @Override
    public EventResult execute(Piliakalnis p) {
        int moraleLoss = rnd.nextBoolean() ? SMALL_MORALE_LOSS : BIG_MORALE_LOSS;
        int goldLoss = rnd.nextBoolean() ? SMALL_GOLD_LOSS : BIG_GOLD_LOSS;

        changeMorale(p, -moraleLoss);
        changeGold(p, -goldLoss);

        String text = "Po ilgos puotos keli uzsivele pagonys nusprende padegti lauko tualetus,\n" +
                "protestuodami pries per dideli tvarkos kieki. Kvapas ir kaimas abudu kencia.\n" +
                "Tvarkos atstatymas kainuoja " + goldLoss + " aukso, o smarve " +
                "sumenkina morale (-" + moraleLoss + ").";

        return new EventResult(text);
    }
}
