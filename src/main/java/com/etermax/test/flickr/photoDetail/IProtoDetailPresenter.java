package com.etermax.test.flickr.photoDetail;

import com.etermax.test.flickr.model.Photo;
import com.etermax.test.flickr.model.PhotoInfo;

/**
 * Created by Mendez Fernandez on 28/12/2016.
 */

public interface IProtoDetailPresenter extends IPhotoDetail{

    /**
     * Show photo detail
     * @param photoInfo
     */
    void showPhotoDetail(PhotoInfo photoInfo);

    /**
     * Get photo detail
     * @param photo
     */
    void getPhotoDetail(Photo photo);
}
