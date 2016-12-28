package com.etermax.test.flickr.service;

import com.etermax.test.flickr.model.PhotosHeader;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Mendez Fernandez on 27/12/2016.
 */

public interface FlickrService {

    @GET("rest/")
    Call<PhotosHeader> getPhotos(@Query("method") String method,
                                 @Query("api_key") String api_key,
                                 @Query("per_page") int per_page,
                                 @Query("page") int page,
                                 @Query("format") String format,
                                 @Query("nojsoncallback") int nojsoncallback);
}
