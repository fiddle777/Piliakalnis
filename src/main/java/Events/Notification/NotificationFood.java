package Events.Notification;

import Core.GameConfig;
import Core.Piliakalnis;

public class NotificationFood extends BaseNotificationEvent {
    private static final int LOW_FOOD_PER_PERSON = 3;
    private static final int HIGH_FOOD_PER_PERSON = 5;
    private static final int MORALE_LOSS = 2;
    private static final int MORALE_GAIN = 1;

    public NotificationFood() {
        super("Maisto atsargu ispejimas");
    }

    @Override
    protected int getCurrentValue(Piliakalnis p) {
        if (p.getPopulation() <= 0) {
            return 0;
        }
        return p.getFood() / p.getPopulation();
    }

    @Override
    protected int getLowThreshold() {
        return LOW_FOOD_PER_PERSON;
    }

    @Override
    protected int getHighThreshold() {
        return HIGH_FOOD_PER_PERSON;
    }

    @Override
    protected void applyLowEffect(Piliakalnis p) {
        int newMorale = Math.max(0, p.getMorale() - MORALE_LOSS);
        p.setMorale(newMorale);
    }

    @Override
    protected void applyHighEffect(Piliakalnis p) {
        int newMorale = Math.min(GameConfig.MAX_MORALE, p.getMorale() + MORALE_GAIN);
        p.setMorale(newMorale);
    }

    @Override
    protected String getLowMessage() {
        return "Sandeliai tusteja, o dubenys vis dazniau buna pustusciai.\n"
                + "Zmones snabzdasi apie artejanti bada - morale krenta.";
    }

    @Override
    protected String getHighMessage() {
        return "Kloniuose ir sandeliuose matosi gausios kruvos grudu ir atsargu.\n"
                + "Zmones jauciasi saugiai - maisto uzteks dar ilgam.";
    }
}