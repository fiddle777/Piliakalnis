package Events;

import Core.Piliakalnis;

public class Event_Notification_Food extends NotificationEvent {

    private static final int LOW_FOOD_MULTIPLIER = 1;
    private static final int HIGH_FOOD_MULTIPLIER = 5;
    private static final int LOW_MORALE_DELTA = -2;
    private static final int HIGH_MORALE_DELTA = 1;
    private static final String LOW_FOOD_TEXT = "DEMESIO! Sandeliuose maista galima suskaiciuoti ant pirstu. " +
            "Zmones nerimauja del artinciu bado metu.";
    private static final String HIGH_FOOD_TEXT = "Kloniuose ir sandeliuose matosi gausus derlius. " +
            "Zmones jauciasi saugiau, nes artimiausiu metu badu nekvepia.";

    @Override
    public String getEventText() {
        return "Maisto atsargu ispejimas";
    }

    @Override
    public boolean canTrigger(Piliakalnis piliakalnis) {
        int food = piliakalnis.getFood();
        int population = piliakalnis.getPopulation();
        return food < population * LOW_FOOD_MULTIPLIER || food >= population * HIGH_FOOD_MULTIPLIER;
    }

    @Override
    protected NotificationOutcome resolveOutcome(Piliakalnis piliakalnis) {
        if (piliakalnis.getFood() < piliakalnis.getPopulation() * LOW_FOOD_MULTIPLIER) {
            return new NotificationOutcome(LOW_FOOD_TEXT, LOW_MORALE_DELTA);
        }
        return new NotificationOutcome(HIGH_FOOD_TEXT, HIGH_MORALE_DELTA);
    }
}
