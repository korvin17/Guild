package com.korvin.guild.event.town;

import com.korvin.guild.event.story.StoryEvent;
import com.korvin.guild.event.story.StoryItem;
import com.korvin.guild.generator.Generator;

/**
 * Created by Korvin on 03.05.2015.
 */
public class TownEvent extends StoryEvent<Way> {

    private Place place;

    public TownEvent() {
        this.place = Generator.generateTown();
    }

    @Override
    public void selectedOption(Way way) {
        place = way.getTo();
    }

    @Override
    public void selectedNextPage() {
        place = new Place("новое описание", place.getImage(), place.getWays());
    }

    @Override
    public StoryItem<Way> getStoryItem() {
        StoryItem<Way> storyItem = new StoryItem<Way>(place.getDescription(), place.getImage(), place.getWays());
        return storyItem;
    }
}
