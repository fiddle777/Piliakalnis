package Events;

import Core.EventResult;
import Core.GameEvent;
import Core.Piliakalnis;

public class Event_Raid_Livonians implements GameEvent {

    @Override
    public String getEventText() {
        return "Kalavijuociu reidas";
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.year >= 1202 && p.year <= 1235 && p.faith > 20;
    }

    @Override
    public EventResult execute(Piliakalnis p) {
        int popLoss = 0;
        int foodLoss = 0;
        int moraleLoss = 0;

        if (p.defense < 20) {
            popLoss = p.population/10;
            foodLoss = p.food/4;
            moraleLoss = 10;
        } else if (p.defense <= 50) {
            popLoss = p.population/20;
            foodLoss = p.food/6;
            moraleLoss = 6;
        } else {
            popLoss = p.population/30;
            foodLoss = p.food/8;
            moraleLoss = 3;
        }

        p.population -= popLoss;
        if (p.population < 0) p.population = 0;
        p.food -= foodLoss;
        if (p.food < 0) p.food = 0;
        p.morale -= moraleLoss;
        if (p.morale < 0) p.morale = 0;

        String text = "APGULTIS! Kalavijuociu pulkai uzslenka is Livonijos, tikrindami nepavergtu zemiu tvirtuma.\n"
                + "Kardeliai kieto plieno isbando tvirtoves ir pavaldiniu stipruma.\n"
                + (popLoss > 0 ? "Zuvo " + popLoss + " zmoniu.\n" : "")
                + (foodLoss > 0 ? "Sandeliuose prarandama " + foodLoss + " maisto.\n" : "")
                + (moraleLoss > 0 ? "Bendruomenes morale krenta -" + moraleLoss + ".\n" : "");

        return new EventResult(text);
    }

    @Override
    public int getChancePercent() {
        return 6;
    }
}