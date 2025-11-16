package Events;

import Core.EventResult;
import Core.GameEvent;
import Core.Piliakalnis;

public class Event_GameOver_MindaugasRandom implements GameEvent {
    @Override
    public String getEventText() {
        return "Mindaugo kvietimas i taryba";
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.year >= 1240 && p.year <= 1250
                && p.morale > 40
                && p.food > p.population
                && p.gold > 100
                && p.defense > 40;
    }

    @Override
    public EventResult execute(Piliakalnis p) {
        String text = "Atvaziuoja raiteliai ir pranesa: Mindaugas kviecia jus i taryba.\n" +
                "Jusu darbai nepastebeti neliko – laikas jungtis prie didziojo vienijimo.\n";
        return new EventResult(text, true);
    }
    @Override
    public int getChancePercent() {
        return 5;
    }
}
