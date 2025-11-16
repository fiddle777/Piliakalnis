package Actions;

import Core.ActionResult;
import Core.GameAction;
import Core.Piliakalnis;

public class Action_Build_Farmstead implements GameAction {

    @Override
    public String getName() {
        return "Plesti ukii / garda";
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
        return p.farmLevel < 3 && p.gold >= 120 && p.population >= 5;
    }

    @Override
    public ActionResult execute(Piliakalnis p) {
        p.gold -= 120;
        p.farmLevel += 1;
        String story = "Jus pleciate uki bei gyvuliu gardus.\n"
                + "Maisto gamyba padideja, ukio lygis: " + p.farmLevel + ".";
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