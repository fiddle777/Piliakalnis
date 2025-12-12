package Actions;

import Actions.Action.HoldFeast;
import Core.GameConfig;
import Core.Piliakalnis;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActionHoldFeastMoraleCapTest {

    @Test
    void holdFeast_doesNotExceedMaxMorale() {
        Piliakalnis p = Piliakalnis.createInitPiliakalnis();
        HoldFeast feast = new HoldFeast();

        p.setFood(999);
        p.setMorale(GameConfig.MAX_MORALE - 2);

        feast.execute(p);
        assertEquals(GameConfig.MAX_MORALE, p.getMorale(),
                "Morale must clamp at MAX_MORALE and not exceed it after HoldFeast.");
    }
}
