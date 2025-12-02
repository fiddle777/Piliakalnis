package Events;

import Core.EventResult;
import Core.Piliakalnis;

public class Event_GameOver_Population extends BaseEvent {

    private static final int MIN_POPULATION = 1;

    @Override
    public String getEventText() {
        return "Zlugimas del gyventoju trukumo";
    }

    @Override
    public boolean canTrigger(Piliakalnis piliakalnis) {
        return piliakalnis.getPopulation() < MIN_POPULATION;
    }

    @Override
    public EventResult execute(Piliakalnis piliakalnis) {
        String text = "Gyventoju likutis issivaiksto arba yra sunaikinamas.\n" +
                "Piliakalnyje nebepakanka zmoniu gyventi, juo labiau ginti.";
        return new EventResult(text, true);
    }

    @Override
    public boolean isRandom() {
        return false;
    }
}
