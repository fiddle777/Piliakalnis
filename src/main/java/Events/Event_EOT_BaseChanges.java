package Events;

import Core.Results.EventResult;
import Core.GameConfig;
import Core.Engine.GameEvent;
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
        // time
        p.setYear(p.getYear() + 1);
        p.setYearsOfRule(p.getYearsOfRule() + 1);

        // food
        int foodConsumption = p.getPopulation() / 2;
        int foodProduction = p.getFarmLevel() * (10 + p.getPopulation() / 10);
        p.setFood(p.getFood() + foodProduction - foodConsumption);

        // gold
        int goldUpkeep = p.getPopulation() / 10;
        int marketIncome = p.getMarketLevel() * 12;
        int baseIncome = 10;
        p.setGold(p.getGold() + baseIncome + marketIncome - goldUpkeep);

        // faith
        int faithDelta = p.getAltarLevel() - 1;
        p.setFaith(p.getFaith() + faithDelta);

        String story = "Metai " + p.getYear() + "AD baigiasi. Pavaldiniai maitinasi, prekiauja, gyvena...";
        return new EventResult(story);
    }

    @Override
    public boolean isRandom() {
        return false;
    }
}
