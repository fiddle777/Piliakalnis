package Actions;

import Core.ActionResult;
import Core.GameAction;
import Core.GameConfig;
import Core.Piliakalnis;

public class Action_Action_HoldFeast implements GameAction {

    @Override
    public String getName() {
        return "Rengti puota";
    }

    @Override
    public String getCategory1() {
        return "Veiksmas";
    }

    @Override
    public String getCategory2() {
        return "Morale";
    }

    @Override
    public boolean isAvailable(Piliakalnis p) {
        return p.getFood() >= 40;
    }

    @Override
    public ActionResult execute(Piliakalnis p) {
        p.setFood(Math.max(0, p.getFood() - 40));

        int newMorale = Math.min(GameConfig.MAX_MORALE, p.getMorale() + 20);
        p.setMorale(newMorale);

        String story = "Surengiate puota savo valstieciams. Maisto atsargos sumazeja, "
                + "bet zmoniu nuotaika pagereja.";
        return new ActionResult(story);
    }

    @Override
    public String getCostDescription() {
        return "Maistas -40";
    }

    @Override
    public String getRequirementDescription() {
        return "Maistas >= 40";
    }

    @Override
    public String getDescription() {
        return "Surengiate puota, kad pakeltumete pavaldiniu morale.";
    }
}
