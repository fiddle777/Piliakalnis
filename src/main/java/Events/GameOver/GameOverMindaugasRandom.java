package Events.GameOver;

import Core.Piliakalnis;

public class GameOverMindaugasRandom extends BaseGameOverEvent {

    private static final int YEAR_MIN = 1240;
    private static final int YEAR_MAX = 1250;
    private static final int MORALE_REQ = 40;
    private static final int GOLD_REQ = 100;
    private static final int DEFENSE_REQ = 40;
    private static final int CHANCE_PERCENT = 5;

    public GameOverMindaugasRandom() {
        super("Mindaugo kvietimas i taryba");
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.getYear() >= YEAR_MIN &&
                p.getYear() <= YEAR_MAX &&
                p.getMorale() > MORALE_REQ &&
                p.getFood() > p.getPopulation() &&
                p.getGold() > GOLD_REQ &&
                p.getDefense() > DEFENSE_REQ;
    }

    @Override
    protected String buildGameOverText(Piliakalnis p) {
        return "Atvaziuoja raiteliai ir pranesa: Mindaugas kviecia jus i taryba.\n" +
                "Jusu darbai nepastebeti neliko – laikas jungtis prie didziojo vienijimo.\n";
    }

    @Override
    public boolean isRandom() {
        return true;
    }

    @Override
    public int getChancePercent() {
        return CHANCE_PERCENT;
    }
}
