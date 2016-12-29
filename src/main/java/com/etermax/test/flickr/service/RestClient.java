package com.etermax.test.flickr.service;

import com.etermax.test.flickr.App;
import com.etermax.test.flickr.BuildConfig;
import com.etermax.test.flickr.model.PhotoDetailReponse;
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
    private static final String METHOD_GET_RECENT_PHOTOS = "flickr.photos.getRecent";
    private static final String METHOD_SEARCH_PHOTOS = "flickr.photos.search";
    private static final String METHOD_GET_PHOTO_INFO = "flickr.photos.getInfo";
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

    /**
     * Get photo by page
     *
     * @param page
     * @return
     * @throws IOException
     */
    public static Call<PhotosHeader> getPhotos(final int page) throws IOException {
        return flickrService.getPhotos(METHOD_GET_RECENT_PHOTOS, BuildConfig.API_KEY, PER_PAGE, page,
                BuildConfig.API_FORMAT, NO_JSON_CALLBACK);
    }

    /**
     * Get photo by text
     *
     * @param text
     * @param page
     * @return
     * @throws IOException
     */
    public static Call<PhotosHeader> search(final String text, final int page) throws IOException {
        return flickrService.searchPhotos(METHOD_SEARCH_PHOTOS, BuildConfig.API_KEY, PER_PAGE, page,
                text, BuildConfig.API_FORMAT, NO_JSON_CALLBACK);
    }

    /**
     * Get photo detail by id
     *
     * @param photoId
     * @return
     * @throws IOException
     */
    public static Call<PhotoDetailReponse> getPhotoDetail(final long photoId) throws IOException {
        return flickrService.getPhotoDetail(METHOD_GET_PHOTO_INFO, BuildConfig.API_KEY, photoId,
                BuildConfig.API_FORMAT, NO_JSON_CALLBACK);
    }
}
