package Events.Notification;

import Core.GameConfig;
import Core.Piliakalnis;

public class NotificationMorale extends BaseNotificationEvent {

    private static final int LOW_MORALE_THRESHOLD = 25;
    private static final int HIGH_MORALE_THRESHOLD = 75;
    private static final int MORALE_PENALTY = 2;
    private static final int MORALE_BONUS = 1;

    public NotificationMorale() {
        super("Morales bukles ispejimas");
    }

    @Override
    protected int getCurrentValue(Piliakalnis p) {
        return p.getMorale();
    }

    @Override
    protected int getLowThreshold() {
        return LOW_MORALE_THRESHOLD;
    }

    @Override
    protected int getHighThreshold() {
        return HIGH_MORALE_THRESHOLD;
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
        return "DEMESIO! Turgavieteje ir prie lauzu girdisi vis daugiau karciu zodziu ir nusivylimo.\n"
                + "Kai kurie pavaldiniai ima murmeti apie neteisingus sprendimus ir prasta dali.\n"
                + "Jei niekas nesikeis, gali kilti rimtesni neramumai.";
    }

    @Override
    protected String getHighMessage() {
        return "Zmones kalba apie teisinga ir tvirta valdzia.\n"
                + "Piliakalnyje tvyro pasitikejimo nuotaika.";
    }
}
