package Events;

import Core.EventResult;
import Core.GameConfig;
import Core.GameEvent;
import Core.Piliakalnis;

public class Event_Notification_Food implements GameEvent {

    private static final int FOOD_HIGH_MULTIPLIER = 5;
    private static final int MORALE_LOSS = 2;
    private static final int MORALE_GAIN = 1;

    @Override
    public String getEventText() {
        return "Maisto atsargu ispejimas";
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.getFood() < p.getPopulation()
                || p.getFood() >= p.getPopulation() * FOOD_HIGH_MULTIPLIER;
    }

    @Override
    public EventResult execute(Piliakalnis p) {
        if (p.getFood() < p.getPopulation()) {
            int newMorale = Math.max(0, p.getMorale() - MORALE_LOSS);
            p.setMorale(newMorale);

            String text = "DEMESIO! Sandeliuose matosi vis daugiau tusciu lentynu, o katiluose verda vis plonesne sriuba.\n"
                    + "Zmones susirupine, zvalgosi i dangaus krastus ir klausia, ar ateinantys metai nebus bado metai.";
            return new EventResult(text);

        } else if (p.getFood() >= p.getPopulation() * FOOD_HIGH_MULTIPLIER) {
            int newMorale = Math.min(GameConfig.MAX_MORALE, p.getMorale() + MORALE_GAIN);
            p.setMorale(newMorale);

            String text = "Kloniuose ir sandeliuose matosi gausios kruvos grudų, mesos ir atsargu.\n"
                    + "Zmones jauciasi saugiau, nes artimiausiu metu badu nekvepia.";
            return new EventResult(text);
        }

        return new EventResult("");
    }

    @Override
    public boolean isRandom() {
        return false;
    }
}
