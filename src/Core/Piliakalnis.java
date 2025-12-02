package Core;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

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
}
