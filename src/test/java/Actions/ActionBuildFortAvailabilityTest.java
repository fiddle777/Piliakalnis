package Actions;

import Core.Piliakalnis;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActionBuildFortAvailabilityTest {

    @Test
    void buildFortIsNotAvailable_whenGoldIsInsufficient() {
        // Arrange
        Piliakalnis p = Piliakalnis.createInitPiliakalnis();

        // Force insufficient gold
        p.setGold(0);

        Action_Build_Fort buildFort = new Action_Build_Fort();

        int initialFortLevel = p.getFortLevel();
        int initialDefense = p.getDefense();

        boolean available = buildFort.isAvailable(p);
        if (available) {
            buildFort.execute(p);
        }

        assertFalse(available, "Build Fort must not be available when gold is insufficient.");
        assertEquals(initialFortLevel, p.getFortLevel(), "Fort level must not change when build is unavailable.");
        assertEquals(initialDefense, p.getDefense(), "Defense must not change when build is unavailable.");
    }
}
