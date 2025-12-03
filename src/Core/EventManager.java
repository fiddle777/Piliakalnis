package Core;

import Events.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EventManager {

    private final List<GameEvent> allEvents = new ArrayList<>();
    private final Random rnd = new Random();

    public EventManager() {
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

    public List<EventResult> rollEvents(Piliakalnis p) {
        List<EventResult> results = new ArrayList<>();
        List<GameEvent> randomCandidates = collectRandomCandidates(p, results);
        triggerRandomEvent(randomCandidates, p, results);
        return results.isEmpty() ? List.of() : results;
    }

    private List<GameEvent> collectRandomCandidates(Piliakalnis p, List<EventResult> results) {
        List<GameEvent> randomCandidates = new ArrayList<>();

        for (GameEvent ge : allEvents) {
            if (!ge.canTrigger(p)) {
                continue;
            }

            if (ge.isRandom()) {
                randomCandidates.add(ge);
            } else {
                results.add(ge.execute(p));
            }
        }

        return randomCandidates;
    }

    private void triggerRandomEvent(List<GameEvent> randomCandidates,
                                    Piliakalnis p,
                                    List<EventResult> results) {

        if (randomCandidates.isEmpty()) {
            return;
        }

        List<GameEvent> eligibleRandomEvents = filterByChance(randomCandidates);
        if (eligibleRandomEvents.isEmpty()) {
            return;
        }

        GameEvent selectedEvent =
                eligibleRandomEvents.get(rnd.nextInt(eligibleRandomEvents.size()));
        results.add(selectedEvent.execute(p));
    }

    private List<GameEvent> filterByChance(List<GameEvent> candidates) {
        List<GameEvent> eligible = new ArrayList<>();

        for (GameEvent ge : candidates) {
            int chance = ge.getChancePercent();

            if (chance >= 100) {
                eligible.add(ge);
            } else if (chance > 0) {
                int roll = rnd.nextInt(100) + 1; // 1–100
                if (roll <= chance) {
                    eligible.add(ge);
                }
            }
        }

        return eligible;
    }
}
