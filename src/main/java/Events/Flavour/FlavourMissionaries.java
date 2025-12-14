package Events.Flavour;

import Core.Results.EventResult;
import Core.Piliakalnis;

public class FlavourMissionaries extends BaseFlavourEvent {

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

    public FlavourMissionaries() {
        super("Ateiviai pranasai is vakaru zemiu", CHANCE_PERCENT);
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.getYear() >= YEAR_THRESHOLD;
    }

    @Override
    public EventResult execute(Piliakalnis p) {
        String text;
        if (p.getFaith() > HIGH_FAITH_THRESHOLD) {
            text = applyHighFaithOutcome(p);
        } else if (p.getFaith() < LOW_FAITH_THRESHOLD) {
            text = applyLowFaithOutcome(p);
        } else {
            text = applyMidFaithOutcome(p);
        }
        return new EventResult(text);
    }

    private String applyHighFaithOutcome(Piliakalnis p) {
        changeFaith(p, HIGH_FAITH_GAIN);
        changeMorale(p, HIGH_MORALE_GAIN);
        return "Sventasis Brunonas, vadinamas Bonifacijumi, arkivyskupas ir vienuolis, \n" +
                "vienuoliktais savo atsivertimo metais Rusios ir Lietuvos pasienyje \n" +
                "pagoniu buvo nukirsdintas kartu su ast...iolika savo biciuliu ir 1009 m. kovo 9 diena pasieke dangu.\n" +
                "Tikejimas sustipreja (+5), o bendruomene jauciasi susivienijusi (+1 morale).";
    }

    private String applyLowFaithOutcome(Piliakalnis p) {
        changeFaith(p, -LOW_FAITH_LOSS);
        changeMorale(p, -LOW_MORALE_LOSS);
        changeGold(p, LOW_GOLD_GAIN);
        return "Pranasai is vakarietisku zemiu ateina skelbti keistu ziniu. \n" +
                "Svietejai dalina dovanas ir Rigos mieste nukaltus sidabrinius tiems, kurie kriksta priimsia\n" +
                "Dalis zmoniu suabejoja senu dievu galia (-5 tikejimo), morale krenta (-1), \n" +
                "bet i piliakalni iteka +10 aukso.";
    }

    private String applyMidFaithOutcome(Piliakalnis p) {
        changeGold(p, MID_GOLD_GAIN);
        changeMorale(p, -MID_MORALE_LOSS);
        return "Pranasai is vakarietisku zemiu ateina skelbti keistu ziniu. \n" +
                "Vieni klausosi su idomumu, kiti raukosi, treti ziuri i dovanas ir mandrus audeklus. \n" +
                "Daug nesikeicia, bet kazkiek svetimu tradiciju prasisunkia, \n" +
                "o keletas sidabriniu (+5) atsiduria piliakalnio aruoduose, \n" +
                "taciau dalis bendruomenes jauciasi sutrikusi (-1 morale).";
    }

}
