package com.etermax.test.flickr.model;

import java.io.Serializable;

/**
 * Created by Mendez Fernandez on 28/12/2016.
 */

public class PhotoInfo implements Serializable {

    private Title title;
    private Description description;
    private Owner owner;
    private Dates dates;

    public PhotoInfo(Title title, Description description, Owner owner, Dates dates) {
        this.title = title;
        this.description = description;
        this.owner = owner;
        this.dates = dates;
    }

    public Title getTitle() {
        return title;
    }

    public Description getDescription() {
        return description;
    }

    public Owner getOwner() {
        return owner;
    }

    public Dates getDates() {
        return dates;
    }
}
