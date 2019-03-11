package com.rharshit.flickrsearch.flickr.api;

import com.rharshit.flickrsearch.flickr.FlickrPhotosParse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FlickrClient {

    @GET("services/rest/?method=flickr.photos.search&format=json")
    Call<List<FlickrPhotosParse>> photosForTag(@Query("api_key") String api_key, @Query("tags") String tag);

    @GET("services/rest/?method=flickr.photos.search&format=json")
    Call<String> photosForTagJson(@Query("api_key") String api_key, @Query("tags") String tag);
}

