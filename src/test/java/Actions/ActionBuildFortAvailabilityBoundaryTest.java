package Actions;

import Core.Piliakalnis;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActionBuildFortAvailabilityBoundaryTest {

    @Test
    void buildFort_availabilityBoundary_goldJustBelowFails_goldAtCostPasses() {
        Piliakalnis p = Piliakalnis.createInitPiliakalnis();
        Action_Build_Fort buildFort = new Action_Build_Fort();

        // Satisfy requirements (Fort requires population >= 30, fortLevel < 3)
        p.setPopulation(30);
        p.setFortLevel(0);

        // Gold just below cost
        p.setGold(149);
        assertFalse(buildFort.isAvailable(p), "Expected unavailable when gold is 149 (< 150).");

        // Gold exactly at cost
        p.setGold(150);
        assertTrue(buildFort.isAvailable(p), "Expected available when gold is exactly 150.");
    }
}
