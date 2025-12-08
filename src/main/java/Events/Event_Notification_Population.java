package Events;

import Core.GameConfig;
import Core.Piliakalnis;

public class Event_Notification_Population extends BaseNotificationEvent {

    private static final int LOW_POP_THRESHOLD = 20;
    private static final int HIGH_POP_THRESHOLD = 120;
    private static final int MORALE_PENALTY = 2;
    private static final int MORALE_BONUS = 1;

    public Event_Notification_Population() {
        super("Gyventoju skaiciaus ispejimas");
    }

    @Override
    protected int getCurrentValue(Piliakalnis p) {
        return p.getPopulation();
    }

    @Override
    protected int getLowThreshold() {
        return LOW_POP_THRESHOLD;
    }

    @Override
    protected int getHighThreshold() {
        return HIGH_POP_THRESHOLD;
    }

    @Override
    protected void applyLowEffect(Piliakalnis p) {
        int newMorale = Math.max(0, p.getMorale() - MORALE_PENALTY);
        p.setMorale(newMorale);
    }

    @Override
    protected void applyHighEffect(Piliakalnis p) {
        int newMorale = Math.min(GameConfig.MAX_MORALE, p.getMorale() + MORALE_BONUS);
        p.setMorale(newMorale);
    }

    @Override
    protected String getLowMessage() {
        return "DEMESIO! Kieme ir aplink piliakalni retai matyti judesys.\n"
                + "Truksta ranku laukams, gynybai ir amatams - zmones nerimauja.";
    }

    @Override
    protected String getHighMessage() {
        return "Piliakalnyje verda gyvenimas, kiemai ir dirbtuves pilnos gyventoju.\n"
                + "Zmones tiki, kad turininga bendruomene lengviau atlaikys sunkesnius laikus.";
    }
}
