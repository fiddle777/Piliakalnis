package Actions;

import Core.ActionResult;
import Core.GameAction;
import Core.Piliakalnis;

public class Action_Build_Fort implements GameAction {

    @Override
    public String getName() {
        return "Stiprinti fortifikacijas";
    }

    @Override
    public String getCategory1() {
        return "Statyba";
    }

    @Override
    public String getCategory2() {
        return "Gynyba";
    }

    @Override
    public boolean isAvailable(Piliakalnis p) {
        return p.gold >= 150 && p.population >= 30 && p.fortLevel < 3;
    }

    @Override
    public ActionResult execute(Piliakalnis p) {
        p.gold -= 150;
        p.fortLevel += 1;
        p.defense += 20;

        String story = "Jus stiprinate gynybines fortifikacijas aplink piliakalni.\n"
                + "Gynyba padideja, dabar fortifikaciju lygis: " + p.fortLevel + ".";
        return new ActionResult(story);
    }

    @Override
    public String getCostDescription() {
        return "Auksas -150";
    }

    @Override
    public String getRequirementDescription() {
        return "Auksas >= 150, Gyventojai >= 30, Fortifikaciju lygis < 3";
    }

    @Override
    public String getDescription() {
        return "Stiprina gynybines sienas ir pakelia bendro piliakalnio gynyba.";
    }
}
