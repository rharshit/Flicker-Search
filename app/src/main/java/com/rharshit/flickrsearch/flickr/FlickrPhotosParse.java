package com.rharshit.flickrsearch.flickr;

import android.util.Log;

import java.util.List;

import static android.content.ContentValues.TAG;


public class FlickrPhotosParse {
    FlickrPageResults photos;

    public void debugInfo() {
        Log.d(TAG, "debugInfo: ");
        photos.getPhotos();
    }
}

class FlickrPageResults {
    int page;
    int pages;
    List<FlickrPhoto> photo;

    public void getPhotos(){
        for(FlickrPhoto p:photo){
            p.getInfo();
        }
    }
}