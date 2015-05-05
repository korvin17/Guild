package com.korvin.guild.server;

import com.korvin.guild.event.Event;
import com.korvin.guild.event.town.TownEvent;

import java.util.ArrayDeque;
import java.util.Queue;

import static com.korvin.guild.event.EventType.END_DAY;

/**
 * Created by Korvin on 01.05.2015.
 */
public enum Server {
    Server;

    private static State state;
    private Queue<Event> eventQueue;

    public static State getState() {
        return state;
    }

    public static void setState(State state) {
        Server.state = state;
    }

    public void initNextDay() {
        eventQueue = new ArrayDeque<>();
        eventQueue.add(new TownEvent());
        eventQueue.add(new Event(END_DAY));
    }

    public Event getNextEvent() {
        return eventQueue.poll();
    }
}
