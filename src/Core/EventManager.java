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
        allEvents.add(new Event_GameOver_MindaugasRandom());
        allEvents.add(new Event_GameOver_Mindaugas());
        allEvents.add(new Event_Flavour_BebruDarba());
        allEvents.add(new Event_Flavour_Drunkards());
        allEvents.add(new Event_Flavour_Fire());
        allEvents.add(new Event_Flavour_Missionaries());
        allEvents.add(new Event_Raid_Teutons());
        allEvents.add(new Event_Raid_Livonians());
        allEvents.add(new Event_Raid_Looters());
        allEvents.add(new Event_Raid_Curonians());
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
            List<GameEvent> eligibleRandomEvents = new ArrayList<>();
            for(GameEvent ge : randomCandidates){
                int chance = ge.getChancePercent();
                if(chance >= 100){
                    eligibleRandomEvents.add(ge);
                } else if(chance > 0){
                    int roll = rnd.nextInt(100) + 1;
                    if(roll <= chance){
                        eligibleRandomEvents.add(ge);
                    }
                }
            }
            if(!eligibleRandomEvents.isEmpty()){
                GameEvent selectedEvent = eligibleRandomEvents.get(rnd.nextInt(eligibleRandomEvents.size()));
                results.add(selectedEvent.execute(p));
            }
        }
        return results.isEmpty() ? List.of() : results;
    }
}