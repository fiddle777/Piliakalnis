package Events;

import Core.EventResult;
import Core.Piliakalnis;

public class Event_GameOver_Mindaugas extends BaseEvent {

    private static final int CROWNING_YEAR = 1251;

    @Override
    public String getEventText() {
        return "Mindaugas pakrikstytas karaliumi";
    }

    @Override
    public boolean canTrigger(Piliakalnis piliakalnis) {
        return piliakalnis.getYear() == CROWNING_YEAR;
    }

    @Override
    public EventResult execute(Piliakalnis piliakalnis) {
        String text = "1251 metais Mindaugas pakrikstomas ir pripazistamas karaliumi.\n" +
                "Jusu piliakalnis issilaike sunkius metus ir prisidejo prie zemiu stiprejimo.\n" +
                "Jusu vardas bus minimas salia kitu valdovu.";
        return new EventResult(text, true);
    }

    @Override
    public boolean isRandom() {
        return false;
    }
}
