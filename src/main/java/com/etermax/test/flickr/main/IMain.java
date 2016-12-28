package com.etermax.test.flickr.main;

import com.etermax.test.flickr.model.PhotoResponse;

/**
 * Created by Mendez Fernandez on 27/12/2016.
 */

public interface IMain {

    void showError(String error);

    void showPhotosByPage(PhotoResponse photoResponse);

    void setPage(int page);
}
