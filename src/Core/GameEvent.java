package Core;

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
