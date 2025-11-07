package Actions;

import Core.ActionResult;
import Core.GameAction;
import Core.Piliakalnis;

public class BuildWallAction implements GameAction {

    @Override
    public String getName() {
        return "Statyti gynybines sienas";
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
        String storyText = "KRC PASKUI NORMALIAI PARASYSIM SIUTUOS DALYKUS Jus statote gynybines sienas aplink savo piliakalnį. Tai padidina jūsų gynybą, bet kainuoja kapeiku";
        return new ActionResult(storyText);
    }
}
