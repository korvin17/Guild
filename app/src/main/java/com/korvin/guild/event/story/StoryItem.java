package com.korvin.guild.event.story;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Korvin on 03.05.2015.
 */
public class StoryItem<E> implements Serializable {
    private CharSequence description;
    private int image;
    private List<E> options;

    public StoryItem(CharSequence description, int image) {
        this.description = description;
        this.image = image;
        this.options = null;
    }

    public StoryItem(CharSequence description, int image, List<E> options) {
        this(description, image);
        this.options = options;
    }

    public StoryItem(CharSequence description, int image, E... options) {
        this(description, image, Arrays.asList(options));
    }

    public CharSequence getDescription() {
        return description;
    }

    public int getImage() {
        return image;
    }

    public List<E> getOptions() {
        return options;
    }
}
