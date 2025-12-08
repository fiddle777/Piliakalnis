package Actions;

import Core.ActionResult;
import Core.Piliakalnis;

public class Action_Build_Market extends BaseAction {

    private static final int GOLD_COST = 130;
    private static final int MAX_MARKET_LEVEL = 3;

    public Action_Build_Market() {
        super(
                "Plesti turgu",
                "Statyba",
                "Ekonomika",
                "Pleciamas turgus, padidinamos ekonomines galimybes ir prekybos srautai.",
                "Auksas -" + GOLD_COST,
                "Turgaus lygis < " + MAX_MARKET_LEVEL + ", Auksas >= " + GOLD_COST
        );
    }

    @Override
    public boolean isAvailable(Piliakalnis p) {
        return p.getMarketLevel() < MAX_MARKET_LEVEL && p.getGold() >= GOLD_COST;
    }

    @Override
    public ActionResult execute(Piliakalnis p) {
        p.setGold(Math.max(0, p.getGold() - GOLD_COST));
        p.setMarketLevel(p.getMarketLevel() + 1);

        String story = "Jus pleciate turgu ir pritraukiate daugiau pirkiu ir pirklio karavanu. "
                + "Ilgainiui auksas ima greiciau kauptis, "
                + "turgaus lygis: " + p.getMarketLevel() + ".";

        return new ActionResult(story);
    }
}
