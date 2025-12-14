package Events.Notification;

import Core.Piliakalnis;

public class NotificationDefense extends BaseNotificationEvent {

    private static final int LOW_DEFENSE_THRESHOLD = 20;
    private static final int HIGH_DEFENSE_THRESHOLD = 60;
    private static final int LOW_DEFENSE_MORALE_LOSS = 2;
    private static final int HIGH_DEFENSE_MORALE_GAIN = 1;

    public NotificationDefense() {
        super("Gynybos bukles ispejimas");
    }

    @Override
    protected int getCurrentValue(Piliakalnis p) {
        return p.getDefense();
    }

    @Override
    protected int getLowThreshold() {
        return LOW_DEFENSE_THRESHOLD;
    }

    @Override
    protected int getHighThreshold() {
        return HIGH_DEFENSE_THRESHOLD;
    }

    @Override
    protected void applyLowEffect(Piliakalnis p) {
        p.setMorale(p.getMorale() - LOW_DEFENSE_MORALE_LOSS);
    }

    @Override
    protected void applyHighEffect(Piliakalnis p) {
        p.setMorale(p.getMorale() + HIGH_DEFENSE_MORALE_GAIN);
    }

    @Override
    protected String getLowMessage() {
        return "DEMESIO! Gynyba labai silpna, zmones nerimauja del galimu uzpuolimu.";
    }

    @Override
    protected String getHighMessage() {
        return "Zvalgyba pranesa, kad piliakalnio gynyba laikoma pakankama, zmones jauciasi saugiau.";
    }
}
