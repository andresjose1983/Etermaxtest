package com.etermax.test.flickr;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.etermax.test.flickr.model.Photo;
import com.etermax.test.flickr.model.PhotoInfo;
import com.etermax.test.flickr.photoDetail.IPhotoDetail;
import com.etermax.test.flickr.photoDetail.IProtoDetailPresenter;
import com.etermax.test.flickr.photoDetail.PhotoDetailPresenter;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoDetailActivity extends AppCompatActivity implements IPhotoDetail{

    @BindView(R.id.iv_photo)
    ImageView mIvPhoto;
    @BindView(R.id.tb_photo)
    Toolbar mTbPhoto;
    @BindView(R.id.pb_photo)
    ProgressBar mPbPhoto;
    @BindView(R.id.tv_date)
    TextView mTvDate;
    @BindView(R.id.tv_user)
    TextView mTvUser;
    @BindView(R.id.tv_description)
    TextView mTvDescription;

    private IProtoDetailPresenter mIProtoDetailPresenter;
    private Photo mPhoto;
    private final static String PHOTO_DETAIL_INTENT = "com.etermax.test.flickr.intent.PHOTO_DETAIL_INTENT";


    public static void show(MainActivity mainActivity, Photo photo, ImageView mIvPhoto){
        Intent intent = new Intent(mainActivity, PhotoDetailActivity.class)
                .putExtra(PHOTO_DETAIL_INTENT, photo);

        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(mainActivity, mIvPhoto, "photo");
        mainActivity.startActivity(intent, options.toBundle());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);
        ButterKnife.bind(this);
        init();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void init(){
        mPhoto = (Photo) getIntent().getExtras().getSerializable(PHOTO_DETAIL_INTENT);

        mTbPhoto.setTitle(mPhoto.getTitle());
        setSupportActionBar(mTbPhoto);
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        loadImagePhoto();
        mIProtoDetailPresenter = new PhotoDetailPresenter(this);
        mIProtoDetailPresenter.getPhotoDetail(mPhoto);
    }

    private void loadImagePhoto(){
        StringBuilder url = new StringBuilder()
                .append("https://farm")
                .append(mPhoto.getFarm())
                .append(".staticflickr.com/")
                .append(mPhoto.getServer())
                .append("/")
                .append(mPhoto.getId())
                .append("_")
                .append(mPhoto.getSecret())
                .append(".jpg");

        Glide.with(this).load(url.toString()).centerCrop().crossFade(500).into(mIvPhoto);
    }

    @Override
    public void hideProgressBar() {
        mPbPhoto.setVisibility(View.GONE);
    }

    @Override
    public void showProgressBar() {
        mPbPhoto.setVisibility(View.VISIBLE);
    }

    @Override
    public void showPhotoDetail(PhotoInfo photoInfo) {
        mTvUser.setText(photoInfo.getOwner().getUsername());
        mTvDescription.setText(photoInfo.getDescription().getContent());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(photoInfo.getDates().getPosted());
        mTvDate.setText(new SimpleDateFormat("EEEE MMMM d HH:mm:ss z yyyy").format(calendar.getTime()));
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}
