package com.etermax.test.flickr.photoDetail;

import com.etermax.test.flickr.model.Photo;
import com.etermax.test.flickr.model.PhotoInfo;

/**
 * Created by Mendez Fernandez on 28/12/2016.
 */

public interface IProtoDetailPresenter extends IPhotoDetail{

    void showPhotoDetail(PhotoInfo photoInfo);

    void getPhotoDetail(Photo photo);
}
