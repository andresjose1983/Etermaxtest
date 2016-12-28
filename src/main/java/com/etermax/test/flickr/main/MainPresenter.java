package com.etermax.test.flickr.main;

import com.etermax.test.flickr.model.PhotoResponse;

/**
 * Created by Mendez Fernandez on 27/12/2016.
 */

public class MainPresenter implements IMainPresenter {

    private IMain mIMain;
    private MainInteractor mMainInteractor;

    public MainPresenter(IMain iMain) {
        mIMain = iMain;
        mMainInteractor = new MainInteractor(this);
        mIMain.showProgressBar();
    }

    @Override
    public void getPhotosByPage(int page) {
        mIMain.showProgressBarByPage();
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
    public void hideProgressBar() {
        mIMain.hideProgressBar();
    }

    @Override
    public void hideProgressBarByPage() {
        mIMain.hideProgressBarByPage();
    }
}
