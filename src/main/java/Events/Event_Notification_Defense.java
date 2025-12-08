package Events;

import Core.Piliakalnis;

public class Event_Notification_Defense extends BaseNotificationEvent {

    private static final int LOW_DEFENSE_THRESHOLD = 20;
    private static final int HIGH_DEFENSE_THRESHOLD = 60;
    private static final int LOW_DEFENSE_MORALE_LOSS = 2;
    private static final int HIGH_DEFENSE_MORALE_GAIN = 1;

    public Event_Notification_Defense() {
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
        if (p.getMorale() < 0) {
            p.setMorale(0);
        }
    }

    @Override
    protected void applyHighEffect(Piliakalnis p) {
        p.setMorale(p.getMorale() + HIGH_DEFENSE_MORALE_GAIN);
        if (p.getMorale() > 100) {
            p.setMorale(100);
        }
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
