package com.etermax.test.flickr;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.etermax.test.flickr.adapter.PhotosAdapter;
import com.etermax.test.flickr.main.IMain;
import com.etermax.test.flickr.main.IMainPresenter;
import com.etermax.test.flickr.main.MainPresenter;
import com.etermax.test.flickr.model.Photo;
import com.etermax.test.flickr.model.PhotoResponse;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IMain, SearchView.OnQueryTextListener {

    @BindView(R.id.pb_photo)
    ProgressBar mPbPhoto;
    @BindView(R.id.pb_photo_pagination)
    ProgressBar mPbPhotoPagination;

    @BindView(R.id.rv_photo)
    RecyclerView mRvPhoto;

    private SearchView mSearchView;
    private MenuItem mMenu;

    private IMainPresenter mIMainPresenter;
    private int mPage;
    private PhotosAdapter photosAdapter;
    private String mQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_photo, menu);
        mMenu = menu.findItem(R.id.action_search);
        // Associate searchable configuration with the SearchView
        mSearchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        mSearchView.setOnQueryTextListener(this);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    /**
     * Show error coming from server
     * @param error
     */
    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    /**
     * Add the data to the adapter
     * @param photoResponse
     */
    @Override
    public void showPhotosByPage(PhotoResponse photoResponse) {
        photosAdapter.setPhotos(photoResponse.getPhotos());
    }

    /** set the current page
     *
     * @param page
     */
    @Override
    public void setPage(int page) {
        mPage = page;
    }

    @Override
    public void hideProgressBar() {
        mPbPhoto.setVisibility(View.GONE);
    }

    @Override
    public void showProgressBar() {
        mPbPhoto.setVisibility(View.VISIBLE);
    }

    /**
     * Hide progress bar
     */
    @Override
    public void showProgressBarByPage() {
        mPbPhotoPagination.setVisibility(View.VISIBLE);
    }

    /**
     * Show progress bar when the last item is showing
     */
    @Override
    public void hideProgressBarByPage() {
        mPbPhotoPagination.setVisibility(View.GONE);
    }

    /**
     * search photo by description
     * @param query
     * @return
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
        photosAdapter.clear();
        mIMainPresenter.search(query, 1);
        mQuery = query;
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void goToPhotoDetail(Photo photo, ImageView imageView) {
        PhotoDetailActivity.show(this, photo, imageView);
    }

    /**
     * Init adapter settings
     */
    private void init() {
        final int span = 3;

        mIMainPresenter = new MainPresenter(this);

        GridLayoutManager mLayoutManager = new GridLayoutManager(this, span);
        mRvPhoto.setLayoutManager(mLayoutManager);
        mRvPhoto.setItemAnimator(new DefaultItemAnimator());
        mRvPhoto.setHasFixedSize(true);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x / span;

        photosAdapter = new PhotosAdapter(this, width);
        mRvPhoto.setAdapter(photosAdapter);

        mRvPhoto.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //validate if the last item is showing in order to get the next data
                if(dy > 0){
                    int visibleItemCount = mLayoutManager.getChildCount();
                    int totalItemCount = mLayoutManager.getItemCount();
                    int pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount){
                        if(mQuery == null || mQuery.trim().isEmpty())
                            mIMainPresenter.getPhotosByPage(++mPage);
                        else
                            mIMainPresenter.search(mQuery, ++mPage);
                    }
                }
            }
        });
    }
}
