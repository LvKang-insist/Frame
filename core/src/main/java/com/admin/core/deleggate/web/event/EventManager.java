package com.admin.core.deleggate.web.event;

import androidx.annotation.NonNull;

import java.util.HashMap;


/**
 * Copyright (C)
 *
 * @file: EventManager
 * @author: 345
 * @Time: 2019/5/5 8:33
 * @description: ${DESCRIPTION}
 */
public class EventManager {
    private static final HashMap<String, Event> EVENTS = new HashMap<>();

    private EventManager() {
//        addEvent("test",new TestEvent());
    }

    /**
     * 惰性单例
     */
    private static class Holder {
        private static final EventManager INSTANCE = new EventManager();
    }

    public static EventManager getInstance() {
        return Holder.INSTANCE;
    }

    public EventManager addEvent(@NonNull String name, @NonNull Event event) {
        EVENTS.put(name, event);
        return this;
    }

    public Event createEvent(@NonNull String action) {
        final Event event = EVENTS.get(action);
        if (event == null) {
            return new UndefineEvent();
        }
        return event;
    }
}
