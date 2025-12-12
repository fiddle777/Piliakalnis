package Actions.Build;

import Actions.BaseAction;
import Core.Results.ActionResult;
import Core.Piliakalnis;

public class BuildFarmstead extends BaseAction {

    private static final int GOLD_COST = 120;
    private static final int MIN_POPULATION = 5;
    private static final int MAX_FARM_LEVEL = 3;

    public BuildFarmstead() {
        super(
                "Plesti uki",
                "Statyba",
                "Maistas",
                "Plecia ukio strukturas, padidina maisto atsargas.",
                "Auksas -" + GOLD_COST,
                "Auksas >= " + GOLD_COST + ", Gyventojai >= " + MIN_POPULATION + ", Ukio lygis < " + MAX_FARM_LEVEL
        );
    }

    @Override
    public boolean isAvailable(Piliakalnis p) {
        return p.getFarmLevel() < MAX_FARM_LEVEL
                && p.getGold() >= GOLD_COST
                && p.getPopulation() >= MIN_POPULATION;
    }

    @Override
    public ActionResult execute(Piliakalnis p) {
        p.setGold(Math.max(0, p.getGold() - GOLD_COST));
        p.setFarmLevel(p.getFarmLevel() + 1);

        String story = "Pleciate ukio strukturas ir paskirstote zemes. "
                + "Ilgainiui maisto atsargos augs sparciau. "
                + "Ukio lygis: " + p.getFarmLevel() + ".";

        return new ActionResult(story);
    }
}
