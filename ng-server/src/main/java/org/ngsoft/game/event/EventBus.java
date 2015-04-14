package org.ngsoft.game.event;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by will on 2015-3-20.
 */
public final class EventBus {

    private static EventBus instance = new EventBus();
    private Map<Integer, CopyOnWriteArraySet<IEvent>> eventPool = new ConcurrentHashMap<>();

    private EventBus() {

    }

    public static void register(IEvent event) {
        CopyOnWriteArraySet<IEvent> evts = instance.eventPool.get(event.id());
        if (evts != null) {
                evts.add(event);
        } else {
                evts = new CopyOnWriteArraySet<>();
                evts.add(event);
                instance.eventPool.put(event.id(), evts);
        }
    }

    public static void fire(IEvent event) {
        event.fire(null);
    }

    public static void fire(EventType type) {
        fire(type, null);
    }

    public static <T extends EventArgs> void fire(EventType type, T args) {
        Set<IEvent> events = instance.eventPool.get(type.getId());
        if (events == null) {
            return;
        }
        events.forEach(event -> event.fire(args));
    }
}
