package Core.Engine;

import Events.*;
import Events.Flavour.*;
import Events.GameOver.*;
import Events.Notification.*;
import Events.Raid.*;

import java.util.List;

public class EventFactory {
    public List<GameEvent> createDefaultEvents() {
        return List.of(
                new Event_EOT_BaseChanges(),

                new NotificationGold(),
                new NotificationFood(),
                new NotificationMorale(),
                new NotificationPopulation(),
                new NotificationDefense(),
                new NotificationFaith(),

                new FlavourBebruDarba(),
                new FlavourDrunkards(),
                new FlavourFire(),
                new FlavourMissionaries(),

                new RaidTeutons(),
                new RaidLivonians(),
                new RaidLooters(),
                new RaidCuronians(),

                new GameOverFood(),
                new GameOverMorale(),
                new GameOverPopulation(),
                new GameOverMindaugasRandom(),
                new GameOverMindaugas()
        );
    }
}
