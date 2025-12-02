package Events;

import Core.EventResult;
import Core.GameConfig;
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

        // clamps
        if (p.getFood() < 0) {
            p.setFood(0);
        }
        if (p.getGold() < 0) {
            p.setGold(0);
        }
        if (p.getMorale() < 0) {
            p.setMorale(0);
        }
        if (p.getMorale() > GameConfig.MAX_MORALE) {
            p.setMorale(GameConfig.MAX_MORALE);
        }
        if (p.getFaith() < 0) {
            p.setFaith(0);
        }
        if (p.getFaith() > GameConfig.MAX_FAITH) {
            p.setFaith(GameConfig.MAX_FAITH);
        }

        String story = "Metai " + p.getYear() + "AD baigiasi. Pavaldiniai maitinasi, prekiauja, gyvena...";
        return new EventResult(story);
    }

    @Override
    public boolean isRandom() {
        return false;
    }
}
