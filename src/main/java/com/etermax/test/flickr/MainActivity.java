package com.etermax.test.flickr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.etermax.test.flickr.main.IMain;
import com.etermax.test.flickr.main.IMainPresenter;
import com.etermax.test.flickr.main.MainPresenter;
import com.etermax.test.flickr.model.PhotoResponse;

public class MainActivity extends AppCompatActivity implements IMain {

    private IMainPresenter mIMainPresenter;
    private int page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIMainPresenter = new MainPresenter(this);
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void showPhotosByPage(PhotoResponse photoResponse) {

    }

    @Override
    public void setPage(int page) {
        this.page = page;
    }
}
