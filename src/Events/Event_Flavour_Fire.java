package Events;

import Core.EventResult;
import Core.GameEvent;
import Core.Piliakalnis;

import java.util.Random;

public class Event_Flavour_Fire implements GameEvent {

    private final Random rnd = new Random();

    @Override
    public String getEventText() {
        return "Netiketas gaisras";
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.population > 0 && (p.fortLevel > 0 || p.farmLevel > 0 || p.marketLevel > 0);
    }

    @Override
    public EventResult execute(Piliakalnis p) {
        int variant = rnd.nextInt(3);
        String text;
        if (variant == 0) {
            int loss = p.food / 4;
            p.food -= loss;
            if (p.food < 0) p.food = 0;
            text = "GAISRAS! Liepsna pakyla virs piliakalnio.\n" +
                    "Vienas is sandeliu ima liepsnoti ir maisto atsargos sumazeja (-" + loss + ").";
        } else if (variant == 1) {
            int loss = 10;
            p.defense -= loss;
            if (p.defense < 0) p.defense = 0;
            text = "GAISRAS! Liepsna pakyla virs piliakalnio.\n" +
                    "Dalis medines palisados apsidega, gynyba susilpneja (-" + loss + ").";
        } else {
            int loss = p.morale/10;
            p.morale -= loss;
            if (p.morale < 0) p.morale = 0;
            text = "GAISRAS! Liepsna pakyla virs piliakalnio.\n" +
                    "Visi issigando liepsnu ir neramiai zvilgcioja i stogus. Morale krenta (-" + loss + ").";
        }
        return new EventResult(text);
    }

    @Override
    public int getChancePercent() {
        return 3;
    }
}
