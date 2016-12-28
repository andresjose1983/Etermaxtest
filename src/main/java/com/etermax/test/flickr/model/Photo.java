package com.etermax.test.flickr.model;

import java.io.Serializable;

/**
 * Created by Mendez Fernandez on 27/12/2016.
 */

public class Photo implements Serializable {

    private long id;
    private int farm;
    private String owner;
    private String secret;
    private int server;
    private String title;

    public Photo(long id, int farm, String owner, String secret, int server, String title) {
        this.id = id;
        this.farm = farm;
        this.owner = owner;
        this.secret = secret;
        this.server = server;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public int getFarm() {
        return farm;
    }

    public String getOwner() {
        return owner;
    }

    public String getSecret() {
        return secret;
    }

    public int getServer() {
        return server;
    }

    public String getTitle() {
        return title;
    }
}
