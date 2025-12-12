package Core;

import lombok.Getter;

@Getter
public class Piliakalnis {
    private int year;
    private int yearsOfRule;
    private int gold;
    private int morale;
    private int food;
    private int population;
    private int defense;
    private int faith;
    private int fortLevel;
    private int farmLevel;
    private int altarLevel;
    private int marketLevel;

    public static Piliakalnis createInitPiliakalnis() {
        Piliakalnis p = new Piliakalnis();
        p.setYear(GameConfig.START_YEAR);
        p.setYearsOfRule(0);
        p.setGold(GameConfig.START_GOLD);
        p.setMorale(GameConfig.START_MORALE);
        p.setFood(GameConfig.START_FOOD);
        p.setPopulation(GameConfig.START_POPULATION);
        p.setDefense(GameConfig.START_DEFENSE);
        p.setFaith(GameConfig.START_FAITH);

        p.setFortLevel(0);
        p.setFarmLevel(0);
        p.setAltarLevel(0);
        p.setMarketLevel(0);

        return p;
    }
    public void setYear(int year) {
        this.year = year;
    }

    // 0 clamps
    public void setYearsOfRule(int yearsOfRule) {
        this.yearsOfRule = Math.max(0, yearsOfRule);
    }

    public void setGold(int gold) {
        this.gold = Math.max(0, gold);
    }

    public void setFood(int food) {
        this.food = Math.max(0, food);
    }

    public void setPopulation(int population) {
        this.population = Math.max(0, population);
    }

    public void setFortLevel(int fortLevel) {
        this.fortLevel = Math.max(0, fortLevel);
    }

    public void setFarmLevel(int farmLevel) {
        this.farmLevel = Math.max(0, farmLevel);
    }

    public void setAltarLevel(int altarLevel) {
        this.altarLevel = Math.max(0, altarLevel);
    }

    public void setMarketLevel(int marketLevel) {
        this.marketLevel = Math.max(0, marketLevel);
    }

    // bound clamps
    public void setMorale(int morale) {
        this.morale = clamp(morale, 0, GameConfig.MAX_MORALE);
    }

    public void setFaith(int faith) {
        this.faith = clamp(faith, 0, GameConfig.MAX_FAITH);
    }

    public void setDefense(int defense) {
        this.defense = clamp(defense, 0, GameConfig.MAX_DEFENSE);
    }

    private static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }
}
