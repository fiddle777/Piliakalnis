package Events;

import Core.EventResult;
import Core.GameConfig;
import Core.GameEvent;
import Core.Piliakalnis;

public class Event_Flavour_Missionaries implements GameEvent {

    private static final int YEAR_THRESHOLD = 1220;
    private static final int HIGH_FAITH_THRESHOLD = 20;
    private static final int LOW_FAITH_THRESHOLD = 10;

    private static final int HIGH_FAITH_GAIN = 6;
    private static final int HIGH_MORALE_GAIN = 7;

    private static final int LOW_FAITH_LOSS = 5;
    private static final int LOW_MORALE_LOSS = 1;
    private static final int LOW_GOLD_GAIN = 10;

    private static final int MID_GOLD_GAIN = 5;
    private static final int MID_MORALE_LOSS = 1;

    private static final int CHANCE_PERCENT = 6;

    @Override
    public String getEventText() {
        return "Ateiviai pranasai is vakaru zemiu";
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.getYear() >= YEAR_THRESHOLD;
    }

    @Override
    public EventResult execute(Piliakalnis p) {
        String text;

        // HIGH FAITH (> 20)
        if (p.getFaith() > HIGH_FAITH_THRESHOLD) {
            p.setFaith(p.getFaith() + HIGH_FAITH_GAIN);
            if (p.getFaith() > GameConfig.MAX_FAITH) {
                p.setFaith(GameConfig.MAX_FAITH);
            }

            p.setMorale(p.getMorale() + HIGH_MORALE_GAIN);
            if (p.getMorale() > GameConfig.MAX_MORALE) {
                p.setMorale(GameConfig.MAX_MORALE);
            }

            text = "Sventasis Brunonas, vadinamas Bonifacijumi, arkivyskupas ir vienuolis, \n" +
                    "vienuoliktais savo atsivertimo metais Rusios ir Lietuvos pasienyje \n" +
                    "pagoniu buvo nukirsdintas kartu su astuoniolika savo biciuliu ir 1009 m. kovo 9 diena pasieke dangu.\n" +
                    "Tikejimas sustipreja (+5), o bendruomene jauciasi susivienijusi (+1 morale).";

            // LOW FAITH (< 10)
        } else if (p.getFaith() < LOW_FAITH_THRESHOLD) {

            p.setFaith(p.getFaith() - LOW_FAITH_LOSS);
            if (p.getFaith() < 0) p.setFaith(0);

            p.setMorale(p.getMorale() - LOW_MORALE_LOSS);
            if (p.getMorale() < 0) p.setMorale(0);

            p.setGold(p.getGold() + LOW_GOLD_GAIN);

            text = "Pranasai is vakarietisku zemiu ateina skelbti keistu ziniu. \n" +
                    "Svietejai dalina dovanas ir Rigos mieste nukaltus sidabrinius tiems, kurie kriksta priimsia\n" +
                    "Dalis zmoniu suabejoja senu dievu galia (-5 tikejimo), morale krenta (-1), \n" +
                    "bet i piliakalni iteka +10 aukso.";

            // MIDDLE FAITH (10–20)
        } else {

            p.setGold(p.getGold() + MID_GOLD_GAIN);

            p.setMorale(p.getMorale() - MID_MORALE_LOSS);
            if (p.getMorale() < 0) p.setMorale(0);

            text = "Pranasai is vakarietisku zemiu ateina skelbti keistu ziniu. \n" +
                    "Vieni klausosi su idomumu, kiti raukosi, treti ziuri i dovanas ir mandrus audeklus. \n" +
                    "Daug nesikeicia, bet kazkiek svetimu tradiciju prasisunkia, \n" +
                    "o keletas sidabriniu (+5) atsiduria piliakalnio aruoduose, \n" +
                    "taciau dalis bendruomenes jauciasi sutrikusi (-1 morale).";
        }

        return new EventResult(text);
    }

    @Override
    public int getChancePercent() {
        return CHANCE_PERCENT;
    }
}
