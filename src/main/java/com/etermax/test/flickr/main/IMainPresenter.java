package com.etermax.test.flickr.main;

import com.etermax.test.flickr.model.PhotoResponse;

/**
 * Created by Mendez Fernandez on 27/12/2016.
 */

public interface IMainPresenter {

    void getPhotosByPage(final int page);

    void showPhotosByPage(PhotoResponse photoResponse);

    void showError(String error);

}
