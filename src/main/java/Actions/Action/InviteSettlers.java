package Actions.Action;

import Actions.BaseAction;
import Core.Results.ActionResult;
import Core.Piliakalnis;

public class InviteSettlers extends BaseAction {

    private static final int GOLD_COST = 80;
    private static final int POPULATION_GAIN = 10;

    public InviteSettlers() {
        super(
                "Kviesti naujakurius",
                "Veiksmas",
                "Gyventojai",
                "Skiriate aukso, kad pritrauktumete naujakuriu i savo piliakalni.",
                "Auksas -" + GOLD_COST,
                "Auksas >= " + GOLD_COST
        );
    }

    @Override
    public boolean isAvailable(Piliakalnis p) {
        return p.getGold() >= GOLD_COST;
    }

    @Override
    public ActionResult execute(Piliakalnis p) {
        p.setGold(p.getGold() - GOLD_COST);
        p.setPopulation(p.getPopulation() + POPULATION_GAIN);

        String story = "Isleidziate auksa, kad pritrauktumete naujakuriu. "
                + "I piliakalni atsikelia naujos seimos.";

        return new ActionResult(story);
    }
}
