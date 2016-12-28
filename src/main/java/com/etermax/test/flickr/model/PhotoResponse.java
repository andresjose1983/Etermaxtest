package com.etermax.test.flickr.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mendez Fernandez on 27/12/2016.
 */

public class PhotoResponse implements Serializable {

    private int page;
    private int pages;
    private int perpage;
    private int total;
    private List<Photo> photo = new ArrayList<>();

    public PhotoResponse(List<Photo> photo, int page, int pages, int perpage, int total) {
        this.photo = photo;
        this.page = page;
        this.pages = pages;
        this.perpage = perpage;
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public int getPages() {
        return pages;
    }

    public int getPerpage() {
        return perpage;
    }

    public int getTotal() {
        return total;
    }

    public List<Photo> getPhotos() {
        return photo;
    }
}
