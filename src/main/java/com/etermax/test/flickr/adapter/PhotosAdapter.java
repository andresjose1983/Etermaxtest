package com.etermax.test.flickr.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.etermax.test.flickr.R;
import com.etermax.test.flickr.main.IMain;
import com.etermax.test.flickr.model.Photo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mendez Fernandez on 27/12/2016.
 */

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotoViewHolder> {

    private IMain mIMain;
    private List<Photo> mPhotos = new ArrayList<>();
    private int mSpan;

    public PhotosAdapter(IMain iMain, int span) {
        this.mIMain = iMain;
        mSpan = span;
    }

    public void setPhotos(List<Photo> photos) {
        mPhotos.addAll(photos);
        notifyItemRangeInserted((mPhotos.size() - photos.size()) - 1 , photos.size());
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo,
                parent, false);

        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        Photo photo = mPhotos.get(position);
        StringBuilder url = new StringBuilder()
                .append("https://farm")
                .append(photo.getFarm())
                .append(".staticflickr.com/")
                .append(photo.getServer())
                .append("/")
                .append(photo.getId())
                .append("_")
                .append(photo.getSecret())
                .append(".jpg");
        Glide.with(holder.mIvPhoto.getContext()).load(url.toString())
                .override(mSpan, mSpan).centerCrop().crossFade(500).into(holder.mIvPhoto);
    }

    @Override
    public int getItemCount() {
        return mPhotos.size();
    }

    class PhotoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_photo)
        ImageView mIvPhoto;

        public PhotoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void clear(){
        mPhotos.clear();
        notifyDataSetChanged();
    }
}
