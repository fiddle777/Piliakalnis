package Actions;

import Core.ActionResult;
import Core.GameAction;
import Core.Piliakalnis;

public class Action_Action_PerformRitual implements GameAction {

    @Override
    public String getName() {
        return "Atlikti rituala";
    }

    @Override
    public String getCategory1() {
        return "Veiksmas";
    }

    @Override
    public String getCategory2() {
        return "Tikejimas";
    }

    @Override
    public boolean isAvailable(Piliakalnis p) {
        return p.food >= 20;
    }

    @Override
    public ActionResult execute(Piliakalnis p) {
        p.food -= 20;
        p.faith += 15;

        String story = "Aukure atliekamas ritualas. Dievai nurimsta, zmoniu tikejimas sustipreja.";
        return new ActionResult(story);
    }

    @Override
    public String getCostDescription() {
        return "Maistas -20";
    }

    @Override
    public String getRequirementDescription() {
        return "Maistas >= 20";
    }

    @Override
    public String getDescription() {
        return "Aukojate maista ir atliekate rituala, kad sustiprintumete tikejima.";
    }
}
