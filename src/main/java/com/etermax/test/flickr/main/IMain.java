package com.etermax.test.flickr.main;

import android.widget.ImageView;

import com.etermax.test.flickr.model.Photo;
import com.etermax.test.flickr.model.PhotoResponse;

/**
 * Created by Mendez Fernandez on 27/12/2016.
 */

public interface IMain {

    /**
     * Show error coming from server
     * @param error
     */
    void showError(String error);

    /**
     * Set data to adapter
     * @param photoResponse
     */
    void showPhotosByPage(PhotoResponse photoResponse);

    /**
     * Set the current page
     * @param page
     */
    void setPage(int page);

    /**
     * Hide progress bar first time
     */
    void hideProgressBar();

    /**
     * Show progress bar first time
     */
    void showProgressBar();

    /**
     * show progress bar pagination
     */
    void showProgressBarByPage();

    /**
     * Hide progress bar pagination
     */
    void hideProgressBarByPage();

    /**
     * Go to PhotoDetail activity
     * @param photo
     * @param imageView
     */
    void goToPhotoDetail(Photo photo, ImageView imageView);
}
