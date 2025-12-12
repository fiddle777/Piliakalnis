package Actions.Action;

import Actions.BaseAction;
import Core.Results.ActionResult;
import Core.GameConfig;
import Core.Piliakalnis;

public class PerformRitual extends BaseAction {

    private static final int FOOD_COST = 20;
    private static final int FAITH_GAIN = 15;

    public PerformRitual() {
        super(
                "Atlikti rituala",
                "Veiksmas",
                "Tikejimas",
                "Aukojate maista ir atliekate rituala, kad sustiprintumete tikejima.",
                "Maistas -" + FOOD_COST,
                "Maistas >= " + FOOD_COST
        );
    }

    @Override
    public boolean isAvailable(Piliakalnis p) {
        return p.getFood() >= FOOD_COST;
    }

    @Override
    public ActionResult execute(Piliakalnis p) {
        p.setFood(Math.max(0, p.getFood() - FOOD_COST));
        p.setFaith(Math.min(GameConfig.MAX_FAITH, p.getFaith() + FAITH_GAIN));

        String story = "Aukure aukojate maista ir atliekate rituala. "
                + "Zmoniu tikejimas ir dievu palankumo jausmas sustipreja.";

        return new ActionResult(story);
    }
}
