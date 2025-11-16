package Events;

import Core.EventResult;
import Core.GameEvent;
import Core.Piliakalnis;

import java.util.Random;

public class Event_Flavour_Drunkards implements GameEvent {

    private final Random rnd = new Random();

    @Override
    public String getEventText() {
        return "Girti pagonys siaucia";
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.population > 15 && p.gold >= 10;
    }

    @Override
    public EventResult execute(Piliakalnis p) {
        int moraleLoss;
        if (rnd.nextBoolean()) {
            moraleLoss = 1;
        } else {
            moraleLoss = 2;
        }
        int goldLoss;
        if (rnd.nextBoolean()) {
            goldLoss = 10;
        } else {
            goldLoss = 20;
        }

        p.morale -= moraleLoss;
        if (p.morale < 0) p.morale = 0;
        p.gold -= goldLoss;
        if (p.gold < 0) p.gold = 0;

        String text = "Po ilgos puotos keli uzsivele pagonys nusprende padegti lauko tualetus,\n" +
                "protestuodami pries per dideli tvarkos kieki. Kvapas ir kaimas abudu kencia.\n" +
                "Tvarkos atstatymas kainuoja " + goldLoss + " aukso, o smarve " +
                "sumenkina morale (-" + moraleLoss + ").";
        return new EventResult(text);
    }

    @Override
    public int getChancePercent() {
        return 7;
    }
}
