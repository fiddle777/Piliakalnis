package Actions.Build;

import Actions.BaseAction;
import Core.Results.ActionResult;
import Core.GameConfig;
import Core.Piliakalnis;

public class BuildAltar extends BaseAction {

    private static final int GOLD_COST = 100;
    private static final int MAX_ALTAR_LEVEL = 3;
    private static final int MORALE_GAIN = 5;

    public BuildAltar() {
        super(
                "Statyti aukura",
                "Statyba",
                "Tikejimas",
                "Aukuras stiprina tikejima ir pasitikejima jumis, Valdove.",
                "Auksas -" + GOLD_COST,
                "Auksas >= " + GOLD_COST + ", Aukuro lygis < " + MAX_ALTAR_LEVEL
        );
    }

    @Override
    public boolean isAvailable(Piliakalnis p) {
        return p.getAltarLevel() < MAX_ALTAR_LEVEL && p.getGold() >= GOLD_COST;
    }

    @Override
    public ActionResult execute(Piliakalnis p) {
        p.setGold(Math.max(0, p.getGold() - GOLD_COST));
        p.setAltarLevel(p.getAltarLevel() + 1);

        p.setMorale(Math.min(GameConfig.MAX_MORALE, p.getMorale() + MORALE_GAIN));

        String story = "Pastatote nauja aukuro pakopa. Zmones dazniau renkasi apeigoms, "
                + "o tikejimas jumis, Valdove, stipreja. "
                + "Aukuro lygis: " + p.getAltarLevel() + ".";

        return new ActionResult(story);
    }
}
