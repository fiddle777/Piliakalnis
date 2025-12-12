package Core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PiliakalnisClampTest {

    @Test
    void foodClampsToZero_whenSetNegative() {
        Piliakalnis p = Piliakalnis.createInitPiliakalnis();

        p.setFood(-5);

        assertEquals(0, p.getFood(), "Food must clamp to 0 when set below 0.");
    }

    @Test
    void goldClampsToZero_whenSetNegative() {
        Piliakalnis p = Piliakalnis.createInitPiliakalnis();

        p.setGold(-1);

        assertEquals(0, p.getGold(), "Gold must clamp to 0 when set below 0.");
    }

    @Test
    void populationClampsToZero_whenSetNegative() {
        Piliakalnis p = Piliakalnis.createInitPiliakalnis();

        p.setPopulation(-999);

        assertEquals(0, p.getPopulation(), "Population must clamp to 0 when set below 0.");
    }

    @Test
    void moraleClampsToRange_whenOutOfBounds() {
        Piliakalnis p = Piliakalnis.createInitPiliakalnis();

        p.setMorale(-10);
        assertEquals(0, p.getMorale(), "Morale must clamp to 0 when set below 0.");

        p.setMorale(999);
        assertEquals(GameConfig.MAX_MORALE, p.getMorale(), "Morale must clamp to MAX_MORALE when set above max.");
    }

    @Test
    void faithClampsToRange_whenOutOfBounds() {
        Piliakalnis p = Piliakalnis.createInitPiliakalnis();

        p.setFaith(-10);
        assertEquals(0, p.getFaith(), "Faith must clamp to 0 when set below 0.");

        p.setFaith(999);
        assertEquals(GameConfig.MAX_FAITH, p.getFaith(), "Faith must clamp to MAX_FAITH when set above max.");
    }

    @Test
    void defenseClampsToRange_whenOutOfBounds() {
        Piliakalnis p = Piliakalnis.createInitPiliakalnis();

        p.setDefense(-10);
        assertEquals(0, p.getDefense(), "Defense must clamp to 0 when set below 0.");

        p.setDefense(999);
        assertEquals(GameConfig.MAX_DEFENSE, p.getDefense(), "Defense must clamp to MAX_DEFENSE when set above max.");
    }
}
