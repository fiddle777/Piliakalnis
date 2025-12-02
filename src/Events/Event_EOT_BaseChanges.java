package Events;

import Core.EventResult;
import Core.GameConfig;
import Core.Piliakalnis;

public class Event_EOT_BaseChanges extends BaseEvent {

    private static final int BASE_INCOME = 10;

    @Override
    public String getEventText() {
        return "Pagrindiniai metu pabaigos pokyciai";
    }

    @Override
    public boolean canTrigger(Piliakalnis piliakalnis) {
        return true;
    }

    @Override
    public EventResult execute(Piliakalnis piliakalnis) {
        progressTime(piliakalnis);
        resolveFood(piliakalnis);
        resolveGold(piliakalnis);
        resolveFaith(piliakalnis);
        clampPiliakalnis(piliakalnis);

        String story = "Metai " + piliakalnis.getYear() + "AD baigiasi. Pavaldiniai maitinasi, prekiauja, gyvena...";
        return new EventResult(story);
    }

    @Override
    public boolean isRandom() {
        return false;
    }

    private void progressTime(Piliakalnis piliakalnis) {
        piliakalnis.setYear(piliakalnis.getYear() + 1);
        piliakalnis.setYearsOfRule(piliakalnis.getYearsOfRule() + 1);
    }

    private void resolveFood(Piliakalnis piliakalnis) {
        int foodConsumption = piliakalnis.getPopulation() / 2;
        int foodProduction = piliakalnis.getFarmLevel() * (10 + piliakalnis.getPopulation() / 10);
        adjustFood(piliakalnis, foodProduction - foodConsumption);
    }

    private void resolveGold(Piliakalnis piliakalnis) {
        int goldUpkeep = piliakalnis.getPopulation() / 10;
        int marketIncome = piliakalnis.getMarketLevel() * 12;
        adjustGold(piliakalnis, BASE_INCOME + marketIncome - goldUpkeep);
    }

    private void resolveFaith(Piliakalnis piliakalnis) {
        int faithDelta = piliakalnis.getAltarLevel() - 1;
        adjustFaith(piliakalnis, faithDelta);
    }

    private void clampPiliakalnis(Piliakalnis piliakalnis) {
        piliakalnis.setMorale(clamp(piliakalnis.getMorale(), 0, GameConfig.MAX_MORALE));
        piliakalnis.setFaith(clamp(piliakalnis.getFaith(), 0, GameConfig.MAX_FAITH));
    }
}
