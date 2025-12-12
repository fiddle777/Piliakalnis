package Actions.Action;

import Actions.BaseAction;
import Core.Results.ActionResult;
import Core.GameConfig;
import Core.Piliakalnis;

public class Hunt extends BaseAction {

    private static final int MIN_POPULATION = 10;
    private static final int FOOD_GAIN = 25;
    private static final int MORALE_GAIN = 2;

    public Hunt() {
        super(
                "Medziokle",
                "Ukis",
                "Maistas",
                "Issiunciate medziotojus i miskus papildyti maisto atsargas.",
                "Nera tiesioginiu islaidu",
                "Gyventojai ≥ " + MIN_POPULATION
        );
    }

    @Override
    public boolean isAvailable(Piliakalnis p) {
        return p.getPopulation() >= MIN_POPULATION;
    }

    @Override
    public ActionResult execute(Piliakalnis p) {
        p.setFood(p.getFood() + FOOD_GAIN);
        p.setMorale(Math.min(GameConfig.MAX_MORALE, p.getMorale() + MORALE_GAIN));

        String story = "Medziotojai iszigiuoja i aplinkinius miskus.\n"
                + "Sugrizta nesini zveriena ir kailiais – maisto atsargos padideja +" + FOOD_GAIN + ".\n"
                + "Sekminga medziokle pakelia nuotaika (morale +" + MORALE_GAIN + ").";

        return new ActionResult(story);
    }
}
