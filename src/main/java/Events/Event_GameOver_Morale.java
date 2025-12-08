package Events;

import Core.Piliakalnis;

public class Event_GameOver_Morale extends BaseGameOverEvent {

    public Event_GameOver_Morale() {
        super("Zlugimas del morales");
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.getMorale() <= 0;
    }

    @Override
    protected String buildGameOverText(Piliakalnis p) {
        return "Pavaldiniai nebetiki jumis kaip valdovu. Jie sukyla ir jus nuvercia.\n" +
                "Jusu valdymas baigesi.";
    }
}
