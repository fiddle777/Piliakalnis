package Actions;

import Core.ActionResult;
import Core.GameAction;
import Core.Piliakalnis;

public class Action_Action_HarvestCrops implements GameAction {
    @Override
    public String getName() {
        return "Rinkti derliu";
    }

    @Override
    public String getCategory1() {
        return "Veiksmas";
    }

    @Override
    public String getCategory2() {
        return "Maistas";
    }

    @Override
    public boolean isAvailable(Piliakalnis p) {
        return p.population >= 15;
    }

    @Override
    public ActionResult execute(Piliakalnis p) {
        p.food += 40;

        String story = "Valdovo isakymu surenkamas derlius. Sandeliai prisipildo maisto.";
        return new ActionResult(story);
    }

    @Override
    public String getCostDescription() {
        return "Nera tiesioginiu islaidu.";
    }

    @Override
    public String getRequirementDescription() {
        return "Gyventojai >= 15";
    }

    @Override
    public String getDescription() {
        return "Isakote surinkti derliu ir papildyti maisto atsargas.";
    }
}
