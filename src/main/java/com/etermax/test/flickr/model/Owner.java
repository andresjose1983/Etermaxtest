package com.etermax.test.flickr.model;

import java.io.Serializable;

/**
 * Created by Mendez Fernandez on 28/12/2016.
 */

public class Owner implements Serializable {

    private String realname;
    private String username;

    public Owner(String realname, String username) {
        this.realname = realname;
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public String getUsername() {
        return username;
    }
}
