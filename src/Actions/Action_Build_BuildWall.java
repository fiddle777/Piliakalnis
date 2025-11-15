package Actions;

import Core.ActionResult;
import Core.GameAction;
import Core.Piliakalnis;

public class Action_Build_BuildWall implements GameAction {

    @Override
    public String getName() {
        return "Stiprinti gynybines sienas";
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
        return p.gold >= 100 && p.population >= 24;
    }

    @Override
    public ActionResult execute(Piliakalnis p) {
        p.gold -= 100;
        p.defense += 20;
        String storyText = "Pastiprintos gynybines sienos aplink piliakalni. Padidinta piliakalnio gynyba.";
        return new ActionResult(storyText);
    }
    @Override
    public String getCostDescription() {
        return "Auksas -100";
    }
    @Override
    public String getRequirementDescription() {
        return "Gyventojai ≥ 24";
    }
    @Override
    public String getDescription() {
        return "Stiprinamos gynybines sienas aplink piliakalni.";
    }
}
