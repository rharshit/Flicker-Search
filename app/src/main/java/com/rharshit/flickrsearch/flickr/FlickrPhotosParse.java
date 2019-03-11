package com.rharshit.flickrsearch.flickr;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


public class FlickrPhotosParse {
    FlickrPageResults photos;

    public void debugInfo() {
        Log.d(TAG, "debugInfo: ");
        if(photos!=null){
            photos.getPhotos();
        }
    }

    public ArrayList<FlickrPhoto> getPageResult() {
        return photos!=null?photos.getPhoto():null;
    }
}

class FlickrPageResults {
    int page;
    int pages;
    ArrayList<FlickrPhoto> photo;

    public void getPhotos(){
        for(FlickrPhoto p:photo){
            p.getInfo();
        }
    }

    public ArrayList<FlickrPhoto> getPhoto() {
        return photo;
    }
}