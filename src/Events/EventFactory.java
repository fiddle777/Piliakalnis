package Events;

import Core.GameEvent;

import java.util.List;

public final class EventFactory {

    private EventFactory() {
    }

    public static List<GameEvent> createDefaultEvents() {
        return List.of(
                new Event_EOT_BaseChanges(),
                new Event_GameOver_Food(),
                new Event_GameOver_Morale(),
                new Event_GameOver_Population(),
                new Event_Notification_Gold(),
                new Event_Notification_Food(),
                new Event_Notification_Morale(),
                new Event_Notification_Population(),
                new Event_Notification_Defense(),
                new Event_Notification_Faith(),
                new Event_GameOver_MindaugasRandom(),
                new Event_GameOver_Mindaugas(),
                new Event_Flavour_BebruDarba(),
                new Event_Flavour_Drunkards(),
                new Event_Flavour_Fire(),
                new Event_Flavour_Missionaries(),
                new Event_Raid_Teutons(),
                new Event_Raid_Livonians(),
                new Event_Raid_Looters(),
                new Event_Raid_Curonians()
        );
    }
}
