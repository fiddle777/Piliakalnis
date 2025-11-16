package Core;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Piliakalnis {
    public int year;
    public int yearsOfRule;
    public int gold;
    public int morale;
    public int food;
    public int population;
    public int defense;
    public int faith;
    public int fortLevel;
    public int farmLevel;
    public int altarLevel;
    public int marketLevel;

    public static Piliakalnis createInitPiliakalnis() {
        Piliakalnis p = new Piliakalnis();
        p.year = 1200;
        p.yearsOfRule = 0;
        p.gold = 400;
        p.morale = 40;
        p.food = 100;
        p.population = 52;
        p.defense = 20;
        p.faith = 20;
        p.fortLevel = 0;
        p.farmLevel = 0;
        p.altarLevel = 0;
        p.marketLevel = 0;
        return p;
    }
}
