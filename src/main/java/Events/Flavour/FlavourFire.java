package Events.Flavour;

import Core.Results.EventResult;
import Core.Piliakalnis;

import java.util.Random;

public class FlavourFire extends BaseFlavourEvent {
    private static final int CHANCE_PERCENT = 3;
    private static final int DEFENSE_LOSS = 10;
    private static final int VARIANT_COUNT = 3;
    private static final int FOOD_LOSS_DIV = 4;
    private static final int MORALE_LOSS_DIV = 10;

    private final Random rnd = new Random();

    public FlavourFire() {
        super("Netiketas gaisras", CHANCE_PERCENT);
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.getPopulation() > 0 && (p.getFortLevel() > 0 || p.getFarmLevel() > 0 || p.getMarketLevel() > 0);
    }

    @Override
    public EventResult execute(Piliakalnis p) {
        int variant = rnd.nextInt(VARIANT_COUNT); // [0; 2]
        String text;

        if (variant == 0) {
            int loss = p.getFood() / FOOD_LOSS_DIV;
            changeFood(p, -loss);

            text = "GAISRAS! Liepsna pakyla virs piliakalnio.\n" +
                    "Vienas is sandeliu ima liepsnoti ir maisto atsargos sumazeja (-" + loss + ").";

        } else if (variant == 1) {
            changeDefense(p, -DEFENSE_LOSS);

            text = "GAISRAS! Liepsna pakyla virs piliakalnio.\n" +
                    "Dalis medines palisados apsidega, gynyba susilpneja (-" + DEFENSE_LOSS + ").";

        } else {
            int loss = p.getMorale() / MORALE_LOSS_DIV;
            changeMorale(p, -loss);

            text = "GAISRAS! Liepsna pakyla virs piliakalnio.\n" +
                    "Visi issigando liepsnu ir neramiai zvilgcioja i stogus. Morale krenta (-" + loss + ").";
        }

        return new EventResult(text);
    }
}
