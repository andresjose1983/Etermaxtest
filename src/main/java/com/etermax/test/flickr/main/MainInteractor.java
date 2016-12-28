package com.etermax.test.flickr.main;

import com.etermax.test.flickr.model.PhotosHeader;
import com.etermax.test.flickr.service.RestClient;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mendez Fernandez on 27/12/2016.
 */

public class MainInteractor {

    private IMainPresenter mIMainPresenter;

    public MainInteractor(IMainPresenter mIMainPresenter) {
        this.mIMainPresenter = mIMainPresenter;
        getPhotosByPage(1);
    }

    public void getPhotosByPage(final int page) {
        try {
            Call<PhotosHeader> photos = RestClient.getPhotos(page);
            photos.enqueue(new Callback<PhotosHeader>() {
                @Override
                public void onResponse(Call<PhotosHeader> call, Response<PhotosHeader> response) {
                    mIMainPresenter.showPhotosByPage(response.body().getPhotos());
                    hideProgressbar();
                }

                @Override
                public void onFailure(Call<PhotosHeader> call, Throwable t) {
                    mIMainPresenter.showError(t.getMessage());
                    hideProgressbar();
                }
            });
        } catch (IOException e) {
            mIMainPresenter.showError(e.getMessage());
            hideProgressbar();
        }
    }

    private void hideProgressbar() {
        mIMainPresenter.hideProgressBar();
        mIMainPresenter.hideProgressBarByPage();
    }
}
