package com.etermax.test.flickr.photoDetail;

import com.etermax.test.flickr.model.Photo;
import com.etermax.test.flickr.model.PhotoDetailReponse;
import com.etermax.test.flickr.service.RestClient;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mendez Fernandez on 28/12/2016.
 */

public class PhotoDetailInteractor {

    private IProtoDetailPresenter mIProtoDetailPresenter;

    public PhotoDetailInteractor(IProtoDetailPresenter mIProtoDetailPresenter) {
        this.mIProtoDetailPresenter = mIProtoDetailPresenter;
    }

    /**
     * Get photo detail from server
     * @param photo
     */
    public void getPhotoDetail(Photo photo) {
        mIProtoDetailPresenter.showProgressBar();
        try {
            Call<PhotoDetailReponse> photoDetail = RestClient.getPhotoDetail(photo.getId());
            photoDetail.enqueue(new Callback<PhotoDetailReponse>() {
                @Override
                public void onResponse(Call<PhotoDetailReponse> call, Response<PhotoDetailReponse> response) {
                    mIProtoDetailPresenter.hideProgressBar();
                    mIProtoDetailPresenter.showPhotoDetail(response.body().getPhoto());
                }

                @Override
                public void onFailure(Call<PhotoDetailReponse> call, Throwable t) {
                    mIProtoDetailPresenter.hideProgressBar();
                    mIProtoDetailPresenter.showError(t.getMessage());
                }
            });
        } catch (IOException e) {
            mIProtoDetailPresenter.hideProgressBar();
            mIProtoDetailPresenter.showError(e.getMessage());
        }
    }
}
