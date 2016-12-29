package com.etermax.test.flickr.photoDetail;

import com.etermax.test.flickr.model.Photo;
import com.etermax.test.flickr.model.PhotoInfo;

/**
 * Created by Mendez Fernandez on 28/12/2016.
 */

public class PhotoDetailPresenter implements IProtoDetailPresenter {

    public IPhotoDetail mIPhotoDetail;
    private PhotoDetailInteractor mPhotoDetailInteractor;

    public PhotoDetailPresenter(IPhotoDetail iPhotoDetail) {
        mIPhotoDetail = iPhotoDetail;
        mPhotoDetailInteractor = new PhotoDetailInteractor(this);
    }

    @Override
    public void hideProgressBar() {
        mIPhotoDetail.hideProgressBar();
    }

    @Override
    public void showProgressBar() {
        mIPhotoDetail.showProgressBar();
    }

    @Override
    public void showPhotoDetail(PhotoInfo photoInfo) {
        mIPhotoDetail.showPhotoDetail(photoInfo);
    }

    @Override
    public void getPhotoDetail(Photo photo) {
        mPhotoDetailInteractor.getPhotoDetail(photo);
    }

    @Override
    public void showError(String error) {
        mIPhotoDetail.showError(error);
    }
}
