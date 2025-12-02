package Events;

import Core.EventResult;
import Core.GameEvent;
import Core.Piliakalnis;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Event_Flavour_BebruDarba implements GameEvent {

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
    public boolean canTrigger(Piliakalnis p) {
        return p.getPopulation() >= MIN_POPULATION;
    }

    @Override
    public EventResult execute(Piliakalnis p) {
        List<String> candidates = new ArrayList<>();
        if (p.getFortLevel() > 0) candidates.add("fort");
        if (p.getFarmLevel() > 0) candidates.add("farm");
        if (p.getAltarLevel() > 0) candidates.add("altar");
        if (p.getMarketLevel() > 0) candidates.add("market");

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
                if (variant == 0 && p.getFortLevel() > 0) {
                    p.setFortLevel(p.getFortLevel() - 1);
                }
            }
            case "farm" -> {
                affectedName = "ukio pastatu";
                if (variant == 0 && p.getFarmLevel() > 0) {
                    p.setFarmLevel(p.getFarmLevel() - 1);
                }
            }
            case "altar" -> {
                affectedName = "aukuro ir sventvietes";
                if (variant == 0 && p.getAltarLevel() > 0) {
                    p.setAltarLevel(p.getAltarLevel() - 1);
                }
            }
            default -> {
                affectedName = "turgaus ir sandeliu";
                if (variant == 0 && p.getMarketLevel() > 0) {
                    p.setMarketLevel(p.getMarketLevel() - 1);
                }
            }
        }

        String text;
        if (variant == 0) {
            p.setMorale(Math.max(0, p.getMorale() - MORALE_LOSS_SMALL));
            text = "Bebru burys pastebejo sukrauta mediena statyboms ir ja issinese.\n" +
                    "Dalies " + affectedName + " darbai visiskai suzlugdomi - viskas turi prasideti is naujo.\n" +
                    "Statybos sustoja, amatininkai burnoja.";
        } else {
            p.setMorale(Math.max(0, p.getMorale() - MORALE_LOSS_BIG));
            text = "Bebru burys pastebejo sukrauta mediena statyboms ir ja issinese.\n" +
                    "Darbininkai visa diena vaikosi zverelius aplink,\n" + affectedName + " statybos veluoja," +
                    "amatininkai burnoja.";
        }

        return new EventResult(text);
    }

    @Override
    public int getChancePercent() {
        return CHANCE_PERCENT;
    }
}
