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

    public EventManager() {
        allEvents.add(new Event_EOT_BaseChanges());
        allEvents.add(new GameOverFood());
        allEvents.add(new GameOverMorale());
        allEvents.add(new GameOverPopulation());
        allEvents.add(new NotificationGold());
        allEvents.add(new NotificationFood());
        allEvents.add(new NotificationMorale());
        allEvents.add(new NotificationPopulation());
        allEvents.add(new NotificationDefense());
        allEvents.add(new NotificationFaith());
        allEvents.add(new GameOverMindaugasRandom());
        allEvents.add(new GameOverMindaugas());
        allEvents.add(new FlavourBebruDarba());
        allEvents.add(new FlavourDrunkards());
        allEvents.add(new FlavourFire());
        allEvents.add(new FlavourMissionaries());
        allEvents.add(new RaidTeutons());
        allEvents.add(new RaidLivonians());
        allEvents.add(new RaidLooters());
        allEvents.add(new RaidCuronians());
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
