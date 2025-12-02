package Events;

import Core.GameConfig;
import Core.GameEvent;
import Core.Piliakalnis;

public abstract class BaseEvent implements GameEvent {

    protected void adjustGold(Piliakalnis piliakalnis, int delta) {
        piliakalnis.setGold(Math.max(0, piliakalnis.getGold() + delta));
    }

    protected void adjustFood(Piliakalnis piliakalnis, int delta) {
        piliakalnis.setFood(Math.max(0, piliakalnis.getFood() + delta));
    }

    protected void adjustMorale(Piliakalnis piliakalnis, int delta) {
        piliakalnis.setMorale(clamp(piliakalnis.getMorale() + delta, 0, GameConfig.MAX_MORALE));
    }

    protected void adjustFaith(Piliakalnis piliakalnis, int delta) {
        piliakalnis.setFaith(clamp(piliakalnis.getFaith() + delta, 0, GameConfig.MAX_FAITH));
    }

    protected void adjustPopulation(Piliakalnis piliakalnis, int delta) {
        piliakalnis.setPopulation(Math.max(0, piliakalnis.getPopulation() + delta));
    }

    protected void adjustDefense(Piliakalnis piliakalnis, int delta) {
        piliakalnis.setDefense(Math.max(0, piliakalnis.getDefense() + delta));
    }

    protected void adjustFortLevel(Piliakalnis piliakalnis, int delta) {
        piliakalnis.setFortLevel(Math.max(0, piliakalnis.getFortLevel() + delta));
    }

    protected void adjustFarmLevel(Piliakalnis piliakalnis, int delta) {
        piliakalnis.setFarmLevel(Math.max(0, piliakalnis.getFarmLevel() + delta));
    }

    protected void adjustAltarLevel(Piliakalnis piliakalnis, int delta) {
        piliakalnis.setAltarLevel(Math.max(0, piliakalnis.getAltarLevel() + delta));
    }

    protected void adjustMarketLevel(Piliakalnis piliakalnis, int delta) {
        piliakalnis.setMarketLevel(Math.max(0, piliakalnis.getMarketLevel() + delta));
    }

    protected int clamp(int value, int min, int max) {
        return Math.min(max, Math.max(min, value));
    }
}
