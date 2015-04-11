package org.ngsoft.game.event;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by will on 2015-3-20.
 */
public final class EventBus {

    private static EventBus instance = new EventBus();

    private EventBus(){

    }
    private Map<Integer, Set<IEvent>> eventPool = new ConcurrentHashMap<>();

    public static void register(IEvent event) {
        Set<IEvent> evts = instance.eventPool.get(event.id());
        if (evts != null) {
            synchronized (evts) {
                evts.add(event);
            }
        } else {
            synchronized (instance.eventPool) {
                evts = new HashSet<>();
                evts.add(event);
                instance.eventPool.put(event.id(), evts);
            }
        }
    }

    public static void fire(IEvent event) {
        event.fire(null);
    }

    public static void fire(EventType type) {
        fire(type, null);
    }

    public static <T extends EventArgs>  void fire(EventType type, T args) {
        Set<IEvent> events = instance.eventPool.get(type.getId());
        if (events == null) {
            return;
        }
        events.forEach(
                event -> {
                    event.fire(args);
                }
        );
    }
}
