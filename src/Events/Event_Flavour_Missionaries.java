package Events;

import Core.EventResult;
import Core.GameEvent;
import Core.Piliakalnis;

public class Event_Flavour_Missionaries implements GameEvent {

    @Override
    public String getEventText() {
        return "Ateiviai pranasai is vakaru zemiu";
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.year >= 1220;
    }

    @Override
    public EventResult execute(Piliakalnis p) {
        String text;
        if (p.faith > 20) {
            p.faith += 6;
            if (p.faith > 100) p.faith = 100;
            p.morale += 7;
            if (p.morale > 100) p.morale = 100;
            text = "Sventasis Brunonas, vadinamas Bonifacijumi, arkivyskupas ir vienuolis, \n" +
                    "vienuoliktais savo atsivertimo metais Rusios ir Lietuvos pasienyje \n" +
                    "pagoniu buvo nukirsdintas kartu su astuoniolika savo biciuliu ir 1009 m. kovo 9 diena pasieke dangu.\n" +
                    "Tikejimas sustipreja (+5), o bendruomene jauciasi susivienijusi (+1 morale).";
        } else if (p.faith < 10) {
            p.faith -= 5;
            if (p.faith < 0) p.faith = 0;
            p.morale -= 1;
            if (p.morale < 0) p.morale = 0;
            p.gold += 10;
            text = "Pranasai is vakarietisku zemiu ateina skelbti keistu ziniu. \n" +
                    "Svietejai dalina dovanas ir Rigos mieste nukaltus sidabrinius tiems, kurie kriksta priimsia\n" +
                    "Dalis zmoniu suabejoja senu dievu galia (-5 tikejimo), morale krenta (-1), \n" +
                    "bet i piliakalni iteka +10 aukso.";
        } else {
            p.gold += 5;
            p.morale -= 1;
            if (p.morale < 0) p.morale = 0;
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
        return 6;
    }
}
