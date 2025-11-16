package Actions;

import Core.ActionResult;
import Core.GameAction;
import Core.Piliakalnis;

public class Action_Build_Altar implements GameAction {

    @Override
    public String getName() {
        return "Statyti aukura";
    }

    @Override
    public String getCategory1() {
        return "Statyba";
    }

    @Override
    public String getCategory2() {
        return "Tikejimas";
    }

    @Override
    public boolean isAvailable(Piliakalnis p) {
        return p.altarLevel < 100 && p.gold >= 100;
    }

    @Override
    public ActionResult execute(Piliakalnis p) {
        p.gold -= 100;
        p.altarLevel += 1;
        p.faith += 15;
        p.morale += 5;

        String story = "Ant piliakalnio statote nauja aukura.\n"
                + "Tikejimas ir morale sustipreja, aukuro lygis: " + p.altarLevel + ".";
        return new ActionResult(story);
    }

    @Override
    public String getCostDescription() {
        return "Auksas -100";
    }

    @Override
    public String getRequirementDescription() {
        return "Auksas >= 100";
    }

    @Override
    public String getDescription() {
        return "Aukuras stiprina tikejima ir morales jausma.";
    }
}
