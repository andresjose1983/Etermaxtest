package com.etermax.test.flickr.model;

/**
 * Created by Mendez Fernandez on 27/12/2016.
 */

public class PhotosHeader {

    private PhotoResponse photos;

    private String stat;

    public PhotosHeader(PhotoResponse photos, String stat) {
        this.photos = photos;
        this.stat = stat;
    }

    public PhotoResponse getPhotos() {
        return photos;
    }

    public String getStat() {
        return stat;
    }
}
