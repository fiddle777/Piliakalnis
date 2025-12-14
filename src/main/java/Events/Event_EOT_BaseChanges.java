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
        int foodConsumption = p.getPopulation() / GameConfig.FOOD_CONSUMPTION_DIV;
        int foodProduction = p.getFarmLevel() * (GameConfig.FARM_BASE_PRODUCTION + p.getPopulation() / GameConfig.POPULATION_FARM_BONUS_DIV);
        p.setFood(p.getFood() + foodProduction - foodConsumption);

        // gold
        int goldUpkeep = p.getPopulation() / GameConfig.GOLD_UPKEEP_DIV;
        int marketIncome = p.getMarketLevel() * GameConfig.MARKET_INCOME_PER_LEVEL;
        int baseIncome = GameConfig.BASE_INCOME;
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
