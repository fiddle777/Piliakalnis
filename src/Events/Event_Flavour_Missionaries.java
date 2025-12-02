package Events;

import Core.EventResult;
import Core.Piliakalnis;

public class Event_Flavour_Missionaries extends BaseEvent {

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
    public boolean canTrigger(Piliakalnis piliakalnis) {
        return piliakalnis.getYear() >= YEAR_THRESHOLD;
    }

    @Override
    public EventResult execute(Piliakalnis piliakalnis) {
        String text;

        if (piliakalnis.getFaith() > HIGH_FAITH_THRESHOLD) {
            adjustFaith(piliakalnis, HIGH_FAITH_GAIN);
            adjustMorale(piliakalnis, HIGH_MORALE_GAIN);
            text = "Sventasis Brunonas, vadinamas Bonifacijumi, arkivyskupas ir vienuolis, \n" +
                    "vienuoliktais savo atsivertimo metais Rusios ir Lietuvos pasienyje \n" +
                    "pagoniu buvo nukirsdintas kartu su astuoniolika savo biciuliu ir 1009 m. kovo 9 diena pasieke dangu.\n" +
                    "Tikejimas sustipreja (+" + HIGH_FAITH_GAIN + "), o bendruomene jauciasi susivienijusi (+" + HIGH_MORALE_GAIN + " morale).";
        } else if (piliakalnis.getFaith() < LOW_FAITH_THRESHOLD) {
            adjustFaith(piliakalnis, -LOW_FAITH_LOSS);
            adjustMorale(piliakalnis, -LOW_MORALE_LOSS);
            adjustGold(piliakalnis, LOW_GOLD_GAIN);
            text = "Pranasai is vakarietisku zemiu ateina skelbti keistu ziniu. \n" +
                    "Svietejai dalina dovanas ir Rigos mieste nukaltus sidabrinius tiems, kurie kriksta priimsia.\n" +
                    "Dalis zmoniu suabejoja senu dievu galia (-" + LOW_FAITH_LOSS + " tikejimo), morale krenta (-" + LOW_MORALE_LOSS + "), \n" +
                    "bet i piliakalni iteka +" + LOW_GOLD_GAIN + " aukso.";
        } else {
            adjustGold(piliakalnis, MID_GOLD_GAIN);
            adjustMorale(piliakalnis, -MID_MORALE_LOSS);
            text = "Pranasai is vakarietisku zemiu ateina skelbti keistu ziniu. \n" +
                    "Vieni klausosi su idomumu, kiti raukosi, treti ziuri i dovanas ir mandrus audeklus. \n" +
                    "Daug nesikeicia, bet kazkiek svetimu tradiciju prasisunkia, \n" +
                    "o keletas sidabriniu (+" + MID_GOLD_GAIN + ") atsiduria piliakalnio aruoduose, \n" +
                    "taciau dalis bendruomenes jauciasi sutrikusi (-" + MID_MORALE_LOSS + " morale).";
        }

        return new EventResult(text);
    }

    @Override
    public int getChancePercent() {
        return CHANCE_PERCENT;
    }
}
