package Events;

import Core.EventResult;
import Core.Piliakalnis;

import java.util.Random;

public class Event_Flavour_Fire extends BaseEvent {

    private static final int CHANCE_PERCENT = 3;
    private static final int DEFENSE_LOSS = 10;

    private final Random rnd = new Random();

    @Override
    public String getEventText() {
        return "Netiketas gaisras";
    }

    @Override
    public boolean canTrigger(Piliakalnis piliakalnis) {
        return piliakalnis.getPopulation() > 0 &&
                (piliakalnis.getFortLevel() > 0 ||
                        piliakalnis.getFarmLevel() > 0 ||
                        piliakalnis.getMarketLevel() > 0);
    }

    @Override
    public EventResult execute(Piliakalnis piliakalnis) {
        int variant = rnd.nextInt(3);
        String text;

        if (variant == 0) {
            int loss = piliakalnis.getFood() / 4;
            adjustFood(piliakalnis, -loss);
            text = "GAISRAS! Liepsna pakyla virs piliakalnio.\n" +
                    "Vienas is sandeliu ima liepsnoti ir maisto atsargos sumazeja (-" + loss + ").";
        } else if (variant == 1) {
            adjustDefense(piliakalnis, -DEFENSE_LOSS);
            text = "GAISRAS! Liepsna pakyla virs piliakalnio.\n" +
                    "Dalis medines palisados apsidega, gynyba susilpneja (-" + DEFENSE_LOSS + ").";
        } else {
            int loss = piliakalnis.getMorale() / 10;
            adjustMorale(piliakalnis, -loss);
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
