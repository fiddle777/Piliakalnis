package Events;

import Core.Piliakalnis;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventEOTBaseChangesPipelineTest {

    @Test
    void eotAppliedOnce_yearAndYearsOfRuleIncrementOnce_andFoodMovesInRightDirection() {
        Piliakalnis p = Piliakalnis.createInitPiliakalnis();

        // population=10 -> consumption=5
        // farmLevel=0 -> production=0
        p.setYear(1200);
        p.setYearsOfRule(0);

        p.setPopulation(10);
        p.setFarmLevel(0);

        // If EOT applies once: 50 + (0 - 5) = 45
        // If applied twice by accident: 40 should fail the test
        p.setFood(50);

        int yearBefore = p.getYear();
        int yearsOfRuleBefore = p.getYearsOfRule();
        int foodBefore = p.getFood();

        Event_EOT_BaseChanges eot = new Event_EOT_BaseChanges();

        eot.execute(p);
        assertEquals(yearBefore + 1, p.getYear(), "EOT must increment year exactly once.");
        assertEquals(yearsOfRuleBefore + 1, p.getYearsOfRule(), "EOT must increment yearsOfRule exactly once.");
        assertTrue(p.getFood() < foodBefore, "Food must decrease when production < consumption.");
        assertEquals(foodBefore - 5, p.getFood(),
                "Food should decrease by exactly population/2 when farmLevel=0 (no production).");
    }
}
