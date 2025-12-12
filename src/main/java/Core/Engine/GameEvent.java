package Core.Engine;

import Core.Results.EventResult;
import Core.Piliakalnis;

public interface GameEvent {
    String getEventText();
    boolean canTrigger(Piliakalnis p);
    EventResult execute (Piliakalnis p);
    default boolean isRandom(){
        return true;
    }
    default int getChancePercent(){
        return 100;
    }
}
