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
        return "Gyventojai";
    }

    @Override
    public boolean isAvailable(Piliakalnis p) {
        return p.getGold() >= 80;
    }

    @Override
    public ActionResult execute(Piliakalnis p) {
        p.setGold(Math.max(0, p.getGold() - 80));
        p.setPopulation(p.getPopulation() + 10);

        String story = "Isleidziate auksa, kad pritrauktumete naujakuriu. "
                + "I piliakalni atsikelia naujos seimos.";
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
