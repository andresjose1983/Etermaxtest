package com.etermax.test.flickr.model;

import java.io.Serializable;

/**
 * Created by Mendez Fernandez on 28/12/2016.
 */

public class Dates implements Serializable {

    long posted;

    public Dates(long posted) {
        this.posted = posted;
    }

    public long getPosted() {
        return posted;
    }
}
