package Events;

import Core.EventResult;
import Core.GameEvent;
import Core.Piliakalnis;

public class Event_EOT_BaseChanges implements GameEvent {
    @Override
    public String getEventText() {
        return "Pagrindiniai metu pabaigos pokyciai";
    }
    @Override
    public boolean canTrigger(Piliakalnis p) {
        return true;
    }
    @Override
    public EventResult execute(Piliakalnis p) {
        //time
        p.year += 1;
        p.yearsOfRule += 1;
        //food
        int foodConsumption = p.population / 2;
        int foodProduction = p.farmLevel * (10 + p.population / 10);
        p.food += foodProduction - foodConsumption;
        //gold
        int goldUpkeep = p.population / 10;
        int marketIncome = p.marketLevel * 12;
        int baseIncome = 10;
        p.gold += baseIncome + marketIncome - goldUpkeep;
        //faith
        int faithDelta = p.altarLevel -1;
        p.faith += faithDelta;
        //clamps
        if (p.food < 0) p.food = 0;
        if (p.gold < 0) p.gold = 0;
        if (p.morale < 0) p.morale = 0;
        if (p.morale > 100) p.morale = 100;
        if (p.faith < 0) p.faith = 0;
        if (p.faith > 100) p.faith = 100;
        String story = "Metai " + p.year + "AD baigiasi. Pavaldiniai maitinasi, prekiauja, gyvena...";
        return new EventResult(story);
    }
    @Override
    public boolean isRandom() {
        return false;
    }
}
