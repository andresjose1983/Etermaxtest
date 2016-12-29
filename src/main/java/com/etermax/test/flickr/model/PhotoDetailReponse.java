package com.etermax.test.flickr.model;

import java.io.Serializable;

/**
 * Created by Mendez Fernandez on 28/12/2016.
 */

public class PhotoDetailReponse implements Serializable{

    PhotoInfo photo;
    String stat;

    public PhotoDetailReponse(PhotoInfo photo, String stat) {
        this.photo = photo;
        this.stat = stat;
    }

    public PhotoInfo getPhoto() {
        return photo;
    }

    public String getStat() {
        return stat;
    }
}
