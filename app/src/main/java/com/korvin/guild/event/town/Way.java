package com.korvin.guild.event.town;

import java.io.Serializable;

/**
 * Created by Korvin on 03.05.2015.
 */
public class Way implements Serializable {
    private CharSequence description;
    private Place from;
    private Place to;

    public Way(CharSequence description, Place from, Place to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    public Place getFrom() {
        return from;
    }

    public Place getTo() {
        return to;
    }

    @Override
    public String toString() {
        return description.toString();
    }
}
