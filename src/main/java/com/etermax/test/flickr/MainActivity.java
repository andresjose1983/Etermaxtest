package com.etermax.test.flickr;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.etermax.test.flickr.adapter.PhotosAdapter;
import com.etermax.test.flickr.main.IMain;
import com.etermax.test.flickr.main.IMainPresenter;
import com.etermax.test.flickr.main.MainPresenter;
import com.etermax.test.flickr.model.PhotoResponse;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IMain {

    @BindView(R.id.pb_photo)
    ProgressBar mPbPhoto;

    @BindView(R.id.rv_photo)
    RecyclerView mRvPhoto;

    private IMainPresenter mIMainPresenter;
    private int mPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        mIMainPresenter = new MainPresenter(this);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showPhotosByPage(PhotoResponse photoResponse) {
        ((PhotosAdapter) mRvPhoto.getAdapter()).setPhotos(photoResponse.getPhotos());
    }

    @Override
    public void setPage(int page) {
        mPage = page;
    }

    @Override
    public void hideProgressBar() {
        mPbPhoto.setVisibility(View.GONE);
    }

    @Override
    public void showProgressbar() {
        mPbPhoto.setVisibility(View.VISIBLE);
    }

    private void init() {
        final int span = 3;

        GridLayoutManager mLayoutManager = new GridLayoutManager(this, span);
        mRvPhoto.setLayoutManager(mLayoutManager);
        mRvPhoto.setItemAnimator(new DefaultItemAnimator());
        mRvPhoto.setHasFixedSize(true);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x / span;

        mRvPhoto.setAdapter(new PhotosAdapter(this, width));
    }

}
