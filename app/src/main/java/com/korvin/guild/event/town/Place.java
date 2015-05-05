package com.korvin.guild.event.town;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Korvin on 03.05.2015.
 */
public class Place implements Serializable {
    private CharSequence description;
    private int image;
    private List<Way> ways;


    public Place(CharSequence description, int image, List<Way> ways) {
        this.description = description;
        this.image = image;
        this.ways = ways;
    }

    public Place(CharSequence description, int image) {
        this.description = description;
        this.image = image;
        this.ways = new ArrayList<>();
    }

    public CharSequence getDescription() {
        return description;
    }

    public int getImage() {
        return image;
    }

    public List<Way> getWays() {
        return ways;
    }

    public void addWay(Way way) {
        this.ways.add(way);
    }

    public void addWay(CharSequence description, Place to) {
        Way way = new Way(description, this, to);
        this.ways.add(way);
    }

}
