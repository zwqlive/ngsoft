package org.ngsoft.game.player.events;

import org.ngsoft.game.event.EventArgs;
import org.ngsoft.game.event.EventType;
import org.ngsoft.game.event.IEvent;

/**
 * Created by will on 2015-3-20.
 */
public class LevelUpEvent implements IEvent {

    @Override
    public int id() {
        return EventType.PlayeyLevelUP.getId();
    }

    @Override
    public void fire(EventArgs args) {

    }
}
