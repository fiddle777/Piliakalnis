package Events.Notification;

import Core.GameConfig;
import Core.Piliakalnis;

public class NotificationGold extends BaseNotificationEvent {
    private static final int LOW_GOLD_THRESHOLD = 80;
    private static final int HIGH_GOLD_THRESHOLD = 400;
    private static final int MORALE_LOSS = 2;
    private static final int MORALE_GAIN = 1;

    public NotificationGold() {
        super("Izdo bukles ispejimas");
    }

    @Override
    protected int getCurrentValue(Piliakalnis p) {
        return p.getGold();
    }

    @Override
    protected int getLowThreshold() {
        return LOW_GOLD_THRESHOLD;
    }

    @Override
    protected int getHighThreshold() {
        return HIGH_GOLD_THRESHOLD;
    }

    @Override
    protected void applyLowEffect(Piliakalnis p) {
        p.setMorale(p.getMorale() - MORALE_LOSS);
    }

    @Override
    protected void applyHighEffect(Piliakalnis p) {
        p.setMorale(p.getMorale() + MORALE_GAIN);
    }

    @Override
    protected String getLowMessage() {
        return "Izdas beveik tuscias. Pirkliai ir kariai ima nerimauti del atlygio.\n"
                + "Zmones kalba, kad valdovas svaisto auksa - morale mazeja.";
    }

    @Override
    protected String getHighMessage() {
        return "Izdas pilnas, kerdziai ir pirkliai kalba apie turtinga piliakalnio rinka.\n"
                + "Zmones tiki, kad valdovas zino, ka daro - pasitikejimas auga.";
    }
}
