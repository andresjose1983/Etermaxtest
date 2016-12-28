package com.etermax.test.flickr.service;

import com.etermax.test.flickr.BuildConfig;
import com.etermax.test.flickr.model.PhotoResponse;
import com.etermax.test.flickr.model.PhotosHeader;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mendez Fernandez on 27/12/2016.
 */

public final class RestClient {

    private static FlickrService flickrService;
    private final static String GET_PHOTOS_RECENT = "flickr.photos.getRecent";
    private final static int PER_PAGE = 10;
    private final static int NO_JSON_CALLBACK = 1;

    static{
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        // add logging as last interceptor
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        flickrService = retrofit.create(FlickrService.class);
    }

    public static Call<PhotosHeader> getPhotos(final int page) throws IOException {
        return flickrService.getPhotos(GET_PHOTOS_RECENT, BuildConfig.API_KEY, PER_PAGE, page,
                BuildConfig.API_FORMAT, NO_JSON_CALLBACK);
    }
}
