package Actions;

import Core.Piliakalnis;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActionLevyTaxesDeltaTest {

    @Test
    void levyTaxes_changesGoldAndMorale_butNotFood() {
        Piliakalnis p = Piliakalnis.createInitPiliakalnis();

        int goldBefore = p.getGold();
        int moraleBefore = p.getMorale();
        int foodBefore = p.getFood();

        Action_Action_LevyTaxes action = new Action_Action_LevyTaxes();
        action.execute(p);

        assertNotEquals(goldBefore, p.getGold(), "LevyTaxes must change gold.");
        assertNotEquals(moraleBefore, p.getMorale(), "LevyTaxes must change morale.");
        assertEquals(foodBefore, p.getFood(), "LevyTaxes must NOT change food (unrelated stat).");

        assertTrue(p.getGold() > goldBefore, "LevyTaxes should increase gold.");
        assertTrue(p.getMorale() < moraleBefore, "LevyTaxes should decrease morale.");
    }
}
