package com.etermax.test.flickr.service;

import com.etermax.test.flickr.App;
import com.etermax.test.flickr.BuildConfig;
import com.etermax.test.flickr.model.PhotosHeader;
import java.io.IOException;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
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
    private final static int PER_PAGE = 20;
    private final static int NO_JSON_CALLBACK = 1;

    static {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.cache(new Cache(App.getInstance().getCacheDir(), 10 * 1024 * 1024)) // 10 MB
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    if (App.checkInternetConnection()) {
                        request = request.newBuilder().header("Cache-Control", "public, max-age=" + 60).build();
                    } else {
                        request = request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build();
                    }
                    return chain.proceed(request);
                });

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
