package Events;

import Core.Piliakalnis;
import Events.Raid.RaidLooters;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventRaidLootersRegressionTest {

    @Test
    void raidLooters_appliesExpectedDeltas() {
        Piliakalnis p = Piliakalnis.createInitPiliakalnis();

        p.setDefense(0);     // < 20
        p.setGold(100);      // goldLoss = 100 / 10 = 10
        p.setFood(50);       // foodLoss = 50 / 10 = 5
        p.setMorale(40);     // moraleLoss = 4
        p.setPopulation(10); // popLoss = 0

        int goldBefore = p.getGold();
        int foodBefore = p.getFood();
        int moraleBefore = p.getMorale();
        int popBefore = p.getPopulation();

        RaidLooters raid = new RaidLooters();
        assertTrue(raid.canTrigger(p), "Raid should be eligible under low defense and nonzero resources.");

        raid.execute(p);
        assertEquals(goldBefore - 10, p.getGold(), "Gold loss must be exactly gold/10.");
        assertEquals(foodBefore - 5, p.getFood(), "Food loss must be exactly food/10.");
        assertEquals(moraleBefore - 4, p.getMorale(), "Morale loss must be exactly 4.");
        assertEquals(popBefore, p.getPopulation(), "Population must not change for this raid.");
    }
}
