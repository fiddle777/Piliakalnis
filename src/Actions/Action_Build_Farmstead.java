package Actions;

import Core.ActionResult;
import Core.GameAction;
import Core.Piliakalnis;

public class Action_Build_Farmstead implements GameAction {

    @Override
    public String getName() {
        return "Plesti uki";
    }

    @Override
    public String getCategory1() {
        return "Statyba";
    }

    @Override
    public String getCategory2() {
        return "Maistas";
    }

    @Override
    public boolean isAvailable(Piliakalnis p) {
        return p.getFarmLevel() < 3
                && p.getGold() >= 120
                && p.getPopulation() >= 5;
    }

    @Override
    public ActionResult execute(Piliakalnis p) {
        p.setGold(Math.max(0, p.getGold() - 120));
        p.setFarmLevel(p.getFarmLevel() + 1);

        String story = "Pleciate ukio strukturas ir paskirstote zemes. "
                + "Ilgainiui maisto atsargos augs sparciau. "
                + "Ukio lygis: " + p.getFarmLevel() + ".";
        return new ActionResult(story);
    }

    @Override
    public String getCostDescription() {
        return "Auksas -120";
    }

    @Override
    public String getRequirementDescription() {
        return "Auksas >= 120, Gyventojai >= 5, Ukio lygis < 3";
    }

    @Override
    public String getDescription() {
        return "Plecia ukio strukturas, padidina maisto atsargas.";
    }
}
