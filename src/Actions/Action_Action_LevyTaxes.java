package Actions;

import Core.ActionResult;
import Core.GameAction;
import Core.GameConfig;
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
        return p.getPopulation() >= 20;
    }

    @Override
    public ActionResult execute(Piliakalnis p) {
        p.setGold(p.getGold() + 50);

        int newMorale = Math.max(0, p.getMorale() - 10);
        newMorale = Math.min(GameConfig.MAX_MORALE, newMorale);
        p.setMorale(newMorale);

        String story = "Paskelbiate metinius mokescius. Izdas pasipildo, "
                + "bet pavaldiniu morale krenta.";
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
