package Actions;

import Core.ActionResult;
import Core.GameAction;
import Core.Piliakalnis;

public class Action_Build_Market implements GameAction {

    @Override
    public String getName() {
        return "Plesti turgų";
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
        return p.marketLevel < 100 && p.gold >= 130;
    }

    @Override
    public ActionResult execute(Piliakalnis p) {
        p.gold -= 130;
        p.marketLevel += 1;
        p.gold += 40;
        p.morale += 2;

        String story = "Jus pleciate turgu ir pritraukiate daugiau pirkiu.\n"
                + "Ilgainiui auksas ima greiciau kauptis, turgaus lygis: " + p.marketLevel + ".";
        return new ActionResult(story);
    }

    @Override
    public String getCostDescription() {
        return "Auksas -130 (iskart), veliau daugiau pajamu.";
    }

    @Override
    public String getRequirementDescription() {
        return "Turgaus lygis < 3, Auksas ≥ 130";
    }

    @Override
    public String getDescription() {
        return "Pleciamas turgus, padidinamos ekonomines galimybes.";
    }
}
