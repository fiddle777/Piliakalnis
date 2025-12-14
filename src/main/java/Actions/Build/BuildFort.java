package Actions.Build;

import Actions.BaseAction;
import Core.Results.ActionResult;
import Core.GameConfig;
import Core.Piliakalnis;

public class BuildFort extends BaseAction {

    private static final int GOLD_COST = 150;
    private static final int MIN_POPULATION = 30;
    private static final int MAX_FORT_LEVEL = 3;
    private static final int DEFENSE_GAIN_PER_LEVEL = 30;

    public BuildFort() {
        super(
                "Stiprinti gynyba",
                "Statybos",
                "Gynyba",
                "Stiprina gynybines sienas ir pakelia bendra piliakalnio gynyba.",
                "Auksas -" + GOLD_COST,
                "Auksas ≥ " + GOLD_COST + ", Gyventojai ≥ " + MIN_POPULATION + ", Fortifikaciju lygis < " + MAX_FORT_LEVEL
        );
    }

    @Override
    public boolean isAvailable(Piliakalnis p) {
        return p.getGold() >= GOLD_COST
                && p.getPopulation() >= MIN_POPULATION
                && p.getFortLevel() < MAX_FORT_LEVEL;
    }

    @Override
    public ActionResult execute(Piliakalnis p) {
        p.setGold(p.getGold() - GOLD_COST);
        p.setDefense(p.getDefense() + DEFENSE_GAIN_PER_LEVEL);
        p.setFortLevel(p.getFortLevel() + 1);

        String story = "Skiriate lesu piliakalnio gynybai.\n"
                + "Sienos sutvirtinamos, pylimai paukstinami, o sargyba sustiprinama.\n"
                + "Gynyba isauga +" + DEFENSE_GAIN_PER_LEVEL + ".";

        return new ActionResult(story);
    }
}
