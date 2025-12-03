package Actions;

import Core.ActionResult;
import Core.GameAction;
import Core.Piliakalnis;

public class Action_Build_Market implements GameAction {

    @Override
    public String getName() {
        return "Plesti turgu";
    }

    @Override
    public String getCategory1() {
        return "Statyba";
    }

    @Override
    public String getCategory2() {
        return "Ekonomika";
    }

    @Override
    public boolean isAvailable(Piliakalnis p) {
        return p.getMarketLevel() < 3 && p.getGold() >= 130;
    }

    @Override
    public ActionResult execute(Piliakalnis p) {
        p.setGold(Math.max(0, p.getGold() - 130));
        p.setMarketLevel(p.getMarketLevel() + 1);

        String story = "Jus pleciate turgu ir pritraukiate daugiau pirkiu ir pirklio karavanu. "
                + "Ilgainiui auksas ima greiciau kauptis, "
                + "turgaus lygis: " + p.getMarketLevel() + ".";
        return new ActionResult(story);
    }

    @Override
    public String getCostDescription() {
        return "Auksas -130";
    }

    @Override
    public String getRequirementDescription() {
        return "Turgaus lygis < 3, Auksas \u2265 130";
    }

    @Override
    public String getDescription() {
        return "Pleciamas turgus, padidinamos ekonomines galimybes ir prekybos srautai.";
    }
}
