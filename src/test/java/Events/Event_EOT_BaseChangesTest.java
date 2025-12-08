package Events;

import Core.EventResult;
import Core.Piliakalnis;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Event_EOT_BaseChangesTest {

    @Test
    void starvationCase() {
        Piliakalnis p = new Piliakalnis();
        p.setPopulation(100);
        p.setFood(50);
        p.setMorale(50);
        p.setYear(1230);

        Event_EOT_BaseChanges eot = new Event_EOT_BaseChanges();

        EventResult result = eot.execute(p);

        assertTrue(p.getFood() < 50, "Food should decrease after EOT");
        assertTrue(p.getPopulation() < 100, "Population should drop when food not enough");
        assertTrue(p.getMorale() < 50, "Morale should drop from starvation");
        assertEquals(1231, p.getYear(), "Year should increment by 1");
        assertFalse(result.isGameOver());
    }
}
