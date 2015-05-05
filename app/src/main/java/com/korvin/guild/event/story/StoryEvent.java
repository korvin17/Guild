package com.korvin.guild.event.story;

import com.korvin.guild.event.Event;
import com.korvin.guild.event.EventType;

/**
 * Created by Korvin on 03.05.2015.
 */
public abstract class StoryEvent<E> extends Event {

    public StoryEvent() {
        super(EventType.STORY);
    }

    public abstract StoryItem<E> getStoryItem();

    public abstract void selectedOption(E option);

    public abstract void selectedNextPage();

}

