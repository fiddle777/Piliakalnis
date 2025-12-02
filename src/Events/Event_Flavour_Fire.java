package Events;

import Core.EventResult;
import Core.GameConfig;
import Core.GameEvent;
import Core.Piliakalnis;

import java.util.Random;

public class Event_Flavour_Fire implements GameEvent {

    private static final int CHANCE_PERCENT = 3;
    private static final int DEFENSE_LOSS = 10;

    private final Random rnd = new Random();

    @Override
    public String getEventText() {
        return "Netiketas gaisras";
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.getPopulation() > 0 &&
                (p.getFortLevel() > 0 ||
                        p.getFarmLevel() > 0 ||
                        p.getMarketLevel() > 0);
    }

    @Override
    public EventResult execute(Piliakalnis p) {

        int variant = rnd.nextInt(3); // [0; 2]
        String text;

        if (variant == 0) {
            int loss = p.getFood() / 4;
            p.setFood(p.getFood() - loss);
            if (p.getFood() < 0) p.setFood(0);

            text = "GAISRAS! Liepsna pakyla virs piliakalnio.\n" +
                    "Vienas is sandeliu ima liepsnoti ir maisto atsargos sumazeja (-" + loss + ").";

        } else if (variant == 1) {
            p.setDefense(p.getDefense() - DEFENSE_LOSS);
            if (p.getDefense() < 0) p.setDefense(0);

            text = "GAISRAS! Liepsna pakyla virs piliakalnio.\n" +
                    "Dalis medines palisados apsidega, gynyba susilpneja (-" + DEFENSE_LOSS + ").";

        } else {
            int loss = p.getMorale() / 10;
            p.setMorale(p.getMorale() - loss);
            if (p.getMorale() < 0) p.setMorale(0);

            text = "GAISRAS! Liepsna pakyla virs piliakalnio.\n" +
                    "Visi issigando liepsnu ir neramiai zvilgcioja i stogus. Morale krenta (-" + loss + ").";
        }

        return new EventResult(text);
    }

    @Override
    public int getChancePercent() {
        return CHANCE_PERCENT;
    }
}
