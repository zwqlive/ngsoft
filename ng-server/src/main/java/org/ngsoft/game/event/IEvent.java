package org.ngsoft.game.event;

/**
 * Created by will on 2015-3-19.
 */
public interface IEvent {
    int id();
    void fire(EventArgs args);
}
