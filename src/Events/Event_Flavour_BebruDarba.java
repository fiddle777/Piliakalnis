package Events;

import Core.EventResult;
import Core.Piliakalnis;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Event_Flavour_BebruDarba extends BaseEvent {

    private static final int MIN_POPULATION = 20;
    private static final int MORALE_LOSS_SMALL = 1;
    private static final int MORALE_LOSS_BIG = 5;
    private static final int CHANCE_PERCENT = 20;

    private final Random rnd = new Random();

    @Override
    public String getEventText() {
        return "Bebrai nugvelbia statybines medziagas";
    }

    @Override
    public boolean canTrigger(Piliakalnis piliakalnis) {
        return piliakalnis.getPopulation() >= MIN_POPULATION;
    }

    @Override
    public EventResult execute(Piliakalnis piliakalnis) {
        List<String> candidates = new ArrayList<>();
        if (piliakalnis.getFortLevel() > 0) candidates.add("fort");
        if (piliakalnis.getFarmLevel() > 0) candidates.add("farm");
        if (piliakalnis.getAltarLevel() > 0) candidates.add("altar");
        if (piliakalnis.getMarketLevel() > 0) candidates.add("market");

        if (candidates.isEmpty()) {
            String text = "Bebru burys pastebejo sukrauta mediena, bet rimtesniu statybu dar nematyti. \n" +
                    "Jie kiek pasikapsto po krumus ir nuplaukia toliau.";
            return new EventResult(text);
        }

        String target = candidates.get(rnd.nextInt(candidates.size()));
        int variant = rnd.nextInt(2);

        String affectedName;
        switch (target) {
            case "fort" -> {
                affectedName = "fortifikaciju";
                if (variant == 0) {
                    adjustFortLevel(piliakalnis, -1);
                }
            }
            case "farm" -> {
                affectedName = "ukio pastatu";
                if (variant == 0) {
                    adjustFarmLevel(piliakalnis, -1);
                }
            }
            case "altar" -> {
                affectedName = "aukuro ir sventvietes";
                if (variant == 0) {
                    adjustAltarLevel(piliakalnis, -1);
                }
            }
            default -> {
                affectedName = "turgaus ir sandeliu";
                if (variant == 0) {
                    adjustMarketLevel(piliakalnis, -1);
                }
            }
        }

        String text;
        if (variant == 0) {
            adjustMorale(piliakalnis, -MORALE_LOSS_SMALL);
            text = "Bebru burys pastebejo sukrauta mediena statyboms ir ja issinese.\n" +
                    "Dalies " + affectedName + " darbai visiskai suzlugdomi - viskas turi prasideti is naujo.\n" +
                    "Statybos sustoja, amatininkai burnoja.";
        } else {
            adjustMorale(piliakalnis, -MORALE_LOSS_BIG);
            text = "Bebru burys pastebejo sukrauta mediena statyboms ir ja issinese.\n" +
                    "Darbininkai visa diena vaikosi zverelius aplink,\n" + affectedName + " statybos veluoja, amatininkai burnoja.";
        }

        return new EventResult(text);
    }

    @Override
    public int getChancePercent() {
        return CHANCE_PERCENT;
    }
}
