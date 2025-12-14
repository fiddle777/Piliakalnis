package Actions.Action;

import Actions.BaseAction;
import Core.Results.ActionResult;
import Core.GameConfig;
import Core.Piliakalnis;

public class LevyTaxes extends BaseAction {

    private static final int GOLD_GAIN = 50;
    private static final int MORALE_LOSS = 10;
    private static final int MIN_POPULATION = 20;

    public LevyTaxes() {
        super(
                "Rinkti mokescius",
                "Veiksmas",
                "Ekonomika",
                "Renkate metinius mokescius. Duoda aukso, bet mazina morale.",
                "Morale -" + MORALE_LOSS,
                "Gyventojai >= " + MIN_POPULATION
        );
    }

    @Override
    public boolean isAvailable(Piliakalnis p) {
        return p.getPopulation() >= MIN_POPULATION;
    }

    @Override
    public ActionResult execute(Piliakalnis p) {
        p.setGold(p.getGold() + GOLD_GAIN);

        p.setMorale(p.getMorale() - MORALE_LOSS);

        String story = "Paskelbiate metinius mokescius. Izdas pasipildo, "
                + "bet pavaldiniu morale krenta.";

        return new ActionResult(story);
    }
}
