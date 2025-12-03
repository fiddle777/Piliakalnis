package Actions;

import Core.ActionResult;
import Core.GameAction;
import Core.Piliakalnis;

public class Action_Action_Hunt implements GameAction {

    @Override
    public String getName() {
        return "Organizuoti medziokle";
    }

    @Override
    public String getCategory1() {
        return "Veiksmas";
    }

    @Override
    public String getCategory2() {
        return "Maistas";
    }

    @Override
    public boolean isAvailable(Piliakalnis p) {
        return p.getPopulation() >= 10;
    }

    @Override
    public ActionResult execute(Piliakalnis p) {
        int gainedFood = p.getPopulation();
        p.setFood(p.getFood() + gainedFood);

        String story = "Valdovo isakymu gyventojai organizuoja medziokle ir papildo maisto atsargas.";
        return new ActionResult(story);
    }

    @Override
    public String getCostDescription() {
        return "Nera tiesioginiu islaidu.";
    }

    @Override
    public String getRequirementDescription() {
        return "Gyventojai >= 10";
    }

    @Override
    public String getDescription() {
        return "Organizuojate medziokle, kad padidintumete maisto atsargas.";
    }
}
