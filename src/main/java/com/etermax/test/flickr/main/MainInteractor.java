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

    /**
     * Call api server by page
     * @param page
     */
    public void getPhotosByPage(final int page) {
        showProgressBar(page);
        try {
            Call<PhotosHeader> photos = RestClient.getPhotos(page);
            photos.enqueue(call());
        } catch (IOException e) {
            mIMainPresenter.showError(e.getMessage());
            hideProgressbar();
        }
    }

    /**
     * Call api server
     * @param text
     * @param page
     */
    public void search(final String text, final int page) {
        showProgressBar(page);
        try {
            Call<PhotosHeader> photos = RestClient.search(text, page);
            photos.enqueue(call());
        } catch (IOException e) {
            mIMainPresenter.showError(e.getMessage());
            hideProgressbar();
        }
    }

    private Callback<PhotosHeader> call(){
        return new Callback<PhotosHeader>() {
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
        };
    }

    private void hideProgressbar() {
        mIMainPresenter.hideProgressBar();
        mIMainPresenter.hideProgressBarByPage();
    }

    private void showProgressBar(final int page){
        if(page == 1)
            mIMainPresenter.showProgressBar();
        else
            mIMainPresenter.showProgressBarByPage();
    }
}
