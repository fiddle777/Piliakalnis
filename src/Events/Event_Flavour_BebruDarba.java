package Events;

import Core.EventResult;
import Core.GameEvent;
import Core.Piliakalnis;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Event_Flavour_BebruDarba implements GameEvent {

    private final Random rnd = new Random();

    @Override
    public String getEventText() {
        return "Bebrai nugvelbia statybines medziagas";
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.population >= 20;
    }

    @Override
    public EventResult execute(Piliakalnis p) {
        List<String> candidates = new ArrayList<>();
        if (p.fortLevel > 0) candidates.add("fort");
        if (p.farmLevel > 0) candidates.add("farm");
        if (p.altarLevel > 0) candidates.add("altar");
        if (p.marketLevel > 0) candidates.add("market");
        if (candidates.isEmpty()) {
            String text = "Bebru burys pastebejo sukrauta mediena, bet rimtesniu statybu dar nematyti. \n" +
                    "Jie kiek pasikapsto po krumus ir nuplaukia toliau.";
            return new EventResult(text);
        }
        String target = candidates.get(rnd.nextInt(candidates.size()));

        int variant = rnd.nextInt(2); // 0 = cancel upgrade, 1 = delay by 1 turn
        String affectedName;
        switch (target) {
            case "fort" -> {
                affectedName = "fortifikaciju";
                if (variant == 0 && p.fortLevel > 0) p.fortLevel--;
            }
            case "farm" -> {
                affectedName = "ukio pastatu";
                if (variant == 0 && p.farmLevel > 0) p.farmLevel--;
            }
            case "altar" -> {
                affectedName = "aukuro ir sventvietes";
                if (variant == 0 && p.altarLevel > 0) p.altarLevel--;
            }
            default -> {
                affectedName = "turgaus ir sandeliu";
                if (variant == 0 && p.marketLevel > 0) p.marketLevel--;
            }
        }

        String text;
        if (variant == 0) {
            p.morale -= 1;
            if (p.morale < 0) p.morale = 0;
            text = "Bebru burys pastebejo sukrauta mediena statyboms ir ja issinese.\n" +
                    "Dalies " + affectedName + " darbai visiskai suzlugdomi – viskas turi prasideti is naujo.\n" +
                    "Statybos sustoja, amatininkai burnoja.";
        } else {
            p.morale -= 5;
            if (p.morale < 0) p.morale = 0;
            text = "Bebru burys pastebejo sukrauta mediena statyboms ir ja issinese.\n" +
                    "Darbininkai visa diena vaikosi zverelius aplink,\n" + affectedName + " statybos veluoja," +
                    "amatininkai burnoja.";
        }
        return new EventResult(text);
    }

    @Override
    public int getChancePercent() {
        return 20;
    }
}
