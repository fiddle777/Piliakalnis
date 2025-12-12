package Core.Engine;

import Core.Results.ActionResult;
import Core.Piliakalnis;

public interface GameAction {
    String getName();
    String getCategory1();
    String getCategory2();
    boolean isAvailable(Piliakalnis p);
    ActionResult execute(Piliakalnis p);
    default String getCostDescription() {
        return "";
    }
    default String getRequirementDescription() {
        return "";
    }
    default String getDescription() {
        return "";
    }
}
