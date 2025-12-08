package Actions;

import Core.ActionResult;
import Core.GameConfig;
import Core.Piliakalnis;

public class Action_Action_HoldFeast extends BaseAction {

    private static final int FOOD_COST = 40;
    private static final int MORALE_GAIN = 20;

    public Action_Action_HoldFeast() {
        super(
                "Rengti puota",
                "Veiksmas",
                "Morale",
                "Surengiate puota, kad pakeltumete pavaldiniu morale.",
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
        p.setMorale(Math.min(GameConfig.MAX_MORALE, p.getMorale() + MORALE_GAIN));

        String story =
                "Surengiate puota savo valstieciams. Maisto atsargos sumazeja, "
                        + "bet zmoniu nuotaika pagereja.";

        return new ActionResult(story);
    }
}
