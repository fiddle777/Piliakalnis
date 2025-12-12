package Events;

import Core.Piliakalnis;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventRaidLootersCanTriggerBoundaryTest {

    @Test
    void raidLooters_canTriggerBoundary_defenseBelowTriggers_defenseAtThresholdDoesNot() {
        Piliakalnis p = Piliakalnis.createInitPiliakalnis();
        Event_Raid_Looters raid = new Event_Raid_Looters();

        // Needs (gold > 0 OR food > 0)
        p.setGold(1);
        p.setFood(0);

        // Defense below threshold
        p.setDefense(19);
        assertTrue(raid.canTrigger(p), "Expected trigger when defense is 19 (< 20) and resources > 0.");

        // Defense at threshold
        p.setDefense(20);
        assertFalse(raid.canTrigger(p), "Expected no trigger when defense is 20 (not < 20).");
    }
}
