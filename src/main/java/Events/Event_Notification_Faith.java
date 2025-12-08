package Events;

import Core.GameConfig;
import Core.Piliakalnis;

public class Event_Notification_Faith extends BaseNotificationEvent {

    private static final int LOW_FAITH_THRESHOLD = 10;
    private static final int HIGH_FAITH_THRESHOLD = 40;
    private static final int MORALE_PENALTY = 2;
    private static final int MORALE_BONUS = 1;

    public Event_Notification_Faith() {
        super("Tikejimo bukles ispejimas");
    }

    @Override
    protected int getCurrentValue(Piliakalnis p) {
        return p.getFaith();
    }

    @Override
    protected int getLowThreshold() {
        return LOW_FAITH_THRESHOLD;
    }

    @Override
    protected int getHighThreshold() {
        return HIGH_FAITH_THRESHOLD;
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
        return "DEMESIO! Zmones ima abejoti dievu palankumu, aukuras sventvieteje retai dega.\n"
                + "Valstieciu nuotaika prasteja.";
    }

    @Override
    protected String getHighMessage() {
        return "Aukuras dega, apeigos vyksta reguliarai.\n"
                + "Tikejimas stiprina bendruomene ir palaiko morale.";
    }
}
