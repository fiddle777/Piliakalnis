package Actions;

import Core.ActionResult;
import Core.GameAction;
import Core.Piliakalnis;

public class Action_Action_LevyTaxes implements GameAction {

    @Override
    public String getName() {
        return "Rinkti mokescius";
    }

    @Override
    public String getCategory1() {
        return "Veiksmas";
    }

    @Override
    public String getCategory2() {
        return "Ekonomika";
    }

    @Override
    public boolean isAvailable(Piliakalnis p) {
        return p.population >= 20;
    }

    @Override
    public ActionResult execute(Piliakalnis p) {
        p.gold += 50;
        p.morale -= 10;

        String story = "Pavaldiniai sumoka metinius mokescius. Izdas padideja, bet morale krenta.";
        return new ActionResult(story);
    }

    @Override
    public String getCostDescription() {
        return "Morale -10";
    }

    @Override
    public String getRequirementDescription() {
        return "Gyventojai >= 20";
    }

    @Override
    public String getDescription() {
        return "Renkate metinius mokescius. Duoda aukso, bet mazina morale.";
    }
}
