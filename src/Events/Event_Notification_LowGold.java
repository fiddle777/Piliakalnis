package Events;

import Core.ActionResult;
import Core.EventResult;
import Core.GameEvent;
import Core.Piliakalnis;

public class Event_Notification_LowGold implements GameEvent {
    @Override
    public String getEventText(){
        return "Baiginejasi shmekeliai";
    }
    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.getGold() < 100;
    }
    @Override
    public EventResult execute(Piliakalnis p){
        return new EventResult("DEMESIO! baiginejasi auksas!");
    }

    @Override
    public boolean isRandom(){
        return false;
    }
}
