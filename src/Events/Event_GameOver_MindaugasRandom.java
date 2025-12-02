package Events;

import Core.EventResult;
import Core.Piliakalnis;

public class Event_GameOver_MindaugasRandom extends BaseEvent {

    private static final int YEAR_MIN = 1240;
    private static final int YEAR_MAX = 1250;
    private static final int MORALE_REQUIREMENT = 40;
    private static final int GOLD_REQUIREMENT = 100;
    private static final int DEFENSE_REQUIREMENT = 40;
    private static final int CHANCE_PERCENT = 5;

    @Override
    public String getEventText() {
        return "Mindaugo kvietimas i taryba";
    }

    @Override
    public boolean canTrigger(Piliakalnis piliakalnis) {
        return piliakalnis.getYear() >= YEAR_MIN &&
                piliakalnis.getYear() <= YEAR_MAX &&
                piliakalnis.getMorale() > MORALE_REQUIREMENT &&
                piliakalnis.getFood() > piliakalnis.getPopulation() &&
                piliakalnis.getGold() > GOLD_REQUIREMENT &&
                piliakalnis.getDefense() > DEFENSE_REQUIREMENT;
    }

    @Override
    public EventResult execute(Piliakalnis piliakalnis) {
        String text = "Atvaziuoja raiteliai ir pranesa: Mindaugas kviecia jus i taryba.\n" +
                "Jusu darbai nepastebeti neliko – laikas jungtis prie didziojo vienijimo.\n";
        return new EventResult(text, true);
    }

    @Override
    public int getChancePercent() {
        return CHANCE_PERCENT;
    }
}
