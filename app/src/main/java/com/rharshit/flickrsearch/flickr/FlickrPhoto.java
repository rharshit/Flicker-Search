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

    public String getURL(){
        String url = "https://farm"+farm+".staticflickr.com/"+server+"/"+id+"_"+secret+".jpg";
        Log.d(TAG, "getURL: "+url);
        return url;
    }

    public String getURL(String ext){
        String url = "https://farm"+farm+".staticflickr.com/"+server+"/"+id+"_"+secret+"_"+ext+".jpg";
        Log.d(TAG, "getURL: "+url);
        return url;
    }
}
