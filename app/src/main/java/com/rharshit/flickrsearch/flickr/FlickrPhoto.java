package com.rharshit.flickrsearch.flickr;

import android.util.Log;

import static android.content.ContentValues.TAG;

public class FlickrPhoto {
    String id;
    String secret;
    String server;
    String farm;
    String title;

    public void getInfo(){
        Log.d(TAG, "getInfo: "+
                "id: "+id +" secret "+ secret + "server" + server+
                " farm "+farm+" title "+title);
    }
}
