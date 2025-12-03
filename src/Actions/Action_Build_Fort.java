package Actions;

import Core.ActionResult;
import Core.GameAction;
import Core.GameConfig;
import Core.Piliakalnis;

public class Action_Build_Fort implements GameAction {

    @Override
    public String getName() {
        return "Stiprinti gynyba";
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
        return p.getGold() >= 150
                && p.getPopulation() >= 30
                && p.getFortLevel() < 3;
    }

    @Override
    public ActionResult execute(Piliakalnis p) {
        p.setGold(Math.max(0, p.getGold() - 150));
        p.setFortLevel(p.getFortLevel() + 1);

        int newDefense = Math.min(GameConfig.MAX_DEFENSE, p.getDefense() + 20);
        p.setDefense(newDefense);

        String story = "Jus stiprinate gynybines fortifikacijas aplink piliakalni. "
                + "Sienos auksteja, tvoros tvirtinamos, "
                + "gynyba padideja, dabar fortifikaciju lygis: " + p.getFortLevel() + ".";
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
