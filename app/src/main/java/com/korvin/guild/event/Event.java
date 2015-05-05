package com.korvin.guild.event;

import java.io.Serializable;

/**
 * Created by Korvin on 02.05.2015.
 */
public class Event implements Serializable {
    protected EventType eventType;

    public Event(EventType eventType) {
        this.eventType = eventType;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }
}
