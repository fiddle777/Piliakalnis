package Core;

import Events.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EventManager {
    private final List<GameEvent> allEvents = new ArrayList<>();
    private final Random rnd = new Random();

    public EventManager(){
        allEvents.add(new Event_EOT_BaseChanges());
        allEvents.add(new Event_GameOver_Food());
        allEvents.add(new Event_GameOver_Morale());
        allEvents.add(new Event_GameOver_Population());
        allEvents.add(new Event_Notification_Gold());
        allEvents.add(new Event_Notification_Food());
        allEvents.add(new Event_Notification_Morale());
        allEvents.add(new Event_Notification_Population());
        allEvents.add(new Event_Notification_Defense());
        allEvents.add(new Event_Notification_Faith());
    }

    public List<EventResult> rollEvents(Piliakalnis p){
        List<EventResult> results = new ArrayList<>();
        List<GameEvent> randomCandidates = new ArrayList<>();
        for(GameEvent ge : allEvents){
            if(ge.canTrigger(p)){
                if(ge.isRandom()){
                    randomCandidates.add(ge);
                } else {
                    results.add(ge.execute(p));
                }
            }
        }
        if(!randomCandidates.isEmpty()){
            GameEvent chosen = randomCandidates.get(rnd.nextInt(randomCandidates.size()));
            results.add(chosen.execute(p));
        }
        if(results.isEmpty()){
            return null;
        }
        return results;
    }
}
