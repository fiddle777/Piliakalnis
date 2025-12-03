package Actions;

import Core.ActionResult;
import Core.GameAction;
import Core.GameConfig;
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
        return p.getAltarLevel() < 3 && p.getGold() >= 100;
    }

    @Override
    public ActionResult execute(Piliakalnis p) {
        p.setGold(Math.max(0, p.getGold() - 100));
        p.setAltarLevel(p.getAltarLevel() + 1);

        int newMorale = Math.min(GameConfig.MAX_MORALE, p.getMorale() + 5);
        p.setMorale(newMorale);

        String story = "Pastatote nauja aukuro pakopa. Zmones dazniau renkasi apeigoms, "
                + "o tikejimas jumis, Valdove, stipreja. "
                + "Aukuro lygis: " + p.getAltarLevel() + ".";
        return new ActionResult(story);
    }

    @Override
    public String getCostDescription() {
        return "Auksas -100";
    }

    @Override
    public String getRequirementDescription() {
        return "Auksas >= 100, Aukuro lygis < 3";
    }

    @Override
    public String getDescription() {
        return "Aukuras stiprina tikejima ir pasitikejima jumis, Valdove.";
    }
}
