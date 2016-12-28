package com.etermax.test.flickr.main;

import com.etermax.test.flickr.model.PhotoResponse;

/**
 * Created by Mendez Fernandez on 27/12/2016.
 */

public class MainPresenter implements IMainPresenter {

    private IMain mIMain;
    private MainInteractor mMainInteractor;

    public MainPresenter(IMain mIMain) {
        this.mIMain = mIMain;
        mMainInteractor = new MainInteractor(this);
    }

    @Override
    public void getPhotosByPage(int page) {
        mMainInteractor.getPhotosByPage(page);
    }

    @Override
    public void showPhotosByPage(PhotoResponse photoResponse) {
        mIMain.setPage(photoResponse.getPage());
        mIMain.showPhotosByPage(photoResponse);
    }

    @Override
    public void showError(String error) {
        mIMain.showError(error);
    }

    @Override
    public void showProgressbar() {
        mIMain.showProgressbar();
    }

    @Override
    public void hideProgressBar() {
        mIMain.hideProgressBar();
    }
}
