package com.etermax.test.flickr.photoDetail;

import com.etermax.test.flickr.model.PhotoInfo;

/**
 * Created by Mendez Fernandez on 28/12/2016.
 */

public interface IPhotoDetail {

    void hideProgressBar();

    void showProgressBar();

    void showPhotoDetail(PhotoInfo photoInfo);

    void showError(String error);
}
