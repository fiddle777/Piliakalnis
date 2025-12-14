package Core.Engine;

import Actions.Action.*;
import Actions.Build.*;
import java.util.List;

public class ActionFactory {

    public List<GameAction> createDefaultActions() {
        return List.of(
                new Hunt(),
                new HoldFeast(),
                new InviteSettlers(),
                new LevyTaxes(),
                new PerformRitual(),

                new BuildFort(),
                new BuildFarmstead(),
                new BuildAltar(),
                new BuildMarket()
        );
    }
}
