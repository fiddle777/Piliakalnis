package Core.Engine;

import Core.Results.EventResult;
import Core.Piliakalnis;
import Events.*;
import Events.Flavour.FlavourBebruDarba;
import Events.Flavour.FlavourDrunkards;
import Events.Flavour.FlavourFire;
import Events.Flavour.FlavourMissionaries;
import Events.GameOver.*;
import Events.Notification.*;
import Events.Raid.RaidCuronians;
import Events.Raid.RaidLivonians;
import Events.Raid.RaidLooters;
import Events.Raid.RaidTeutons;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EventManager {
    private final List<GameEvent> allEvents = new ArrayList<>();
    private final Random rnd = new Random();
    private static final int PERCENT_MAX = 100;

    public EventManager() {
        EventFactory factory = new EventFactory();
        allEvents.addAll(factory.createDefaultEvents());
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

            if (chance >= PERCENT_MAX) {
                eligible.add(ge);
            } else if (chance > 0) {
                int roll = rnd.nextInt(PERCENT_MAX) + 1; // 1–100
                if (roll <= chance) {
                    eligible.add(ge);
                }
            }
        }

        return eligible;
    }
}
