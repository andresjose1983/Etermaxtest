package com.etermax.test.flickr.photoDetail;

import com.etermax.test.flickr.model.PhotoInfo;

/**
 * Created by Mendez Fernandez on 28/12/2016.
 */

public interface IPhotoDetail {

    /**
     * Hide progressbar
     */
    void hideProgressBar();

    /**
     * Show progressbar
     */
    void showProgressBar();

    /**
     * Show photo detail
     * @param photoInfo
     */
    void showPhotoDetail(PhotoInfo photoInfo);

    /**
     * Show server error
     * @param error
     */
    void showError(String error);
}
