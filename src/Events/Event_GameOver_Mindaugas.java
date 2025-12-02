package Events;

import Core.EventResult;
import Core.GameEvent;
import Core.Piliakalnis;

public class Event_GameOver_Mindaugas implements GameEvent {

    @Override
    public String getEventText() {
        return "Mindaugas pakrikstytas karaliumi";
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.getYear() == 1251;
    }

    @Override
    public EventResult execute(Piliakalnis p) {
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
