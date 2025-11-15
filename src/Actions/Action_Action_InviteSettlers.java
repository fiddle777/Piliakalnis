package Actions;

import Core.ActionResult;
import Core.GameAction;
import Core.Piliakalnis;

public class Action_Action_InviteSettlers implements GameAction {
    @Override
    public String getName() {
        return "Kviesti naujakurius";
    }

    @Override
    public String getCategory1() {
        return "Veiksmas";
    }

    @Override
    public String getCategory2() {
        return "Populiacija";
    }

    @Override
    public boolean isAvailable(Piliakalnis p) {
        return p.gold >= 80;
    }

    @Override
    public ActionResult execute(Piliakalnis p) {
        p.gold -= 80;
        p.population += 10;

        String story = "Isleidziate auksa, kad pritrauktumete naujakuriu. Piliakalnyje isikuria naujos seimos.";
        return new ActionResult(story);
    }

    @Override
    public String getCostDescription() {
        return "Auksas -80";
    }

    @Override
    public String getRequirementDescription() {
        return "Auksas >= 80";
    }

    @Override
    public String getDescription() {
        return "Skiriate aukso, kad pritrauktumete naujakuriu i savo piliakalni.";
    }
}
