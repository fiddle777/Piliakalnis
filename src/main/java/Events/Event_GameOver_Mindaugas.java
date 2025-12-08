package Events;

import Core.Piliakalnis;

public class Event_GameOver_Mindaugas extends BaseGameOverEvent {

    private static final int TARGET_YEAR = 1251;

    public Event_GameOver_Mindaugas() {
        super("Mindaugas pakrikstytas karaliumi");
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.getYear() == TARGET_YEAR;
    }

    @Override
    protected String buildGameOverText(Piliakalnis p) {
        return "1251 metais Mindaugas pakrikstomas ir pripazistamas karaliumi.\n" +
                "Jusu piliakalnis issilaike sunkius metus ir prisidejo prie zemiu stiprejimo.\n" +
                "Jusu vardas bus minimas salia kitu valdovu.";
    }
}
