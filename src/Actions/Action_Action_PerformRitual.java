package Actions;

import Core.ActionResult;
import Core.GameAction;
import Core.GameConfig;
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
        return p.getFood() >= 20;
    }

    @Override
    public ActionResult execute(Piliakalnis p) {
        p.setFood(Math.max(0, p.getFood() - 20));

        int newFaith = Math.min(GameConfig.MAX_FAITH, p.getFaith() + 15);
        p.setFaith(newFaith);

        String story = "Aukure aukojate maista ir atliekate rituala. "
                + "Zmoniu tikejimas ir dievu palankumo jausmas sustipreja.";
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
