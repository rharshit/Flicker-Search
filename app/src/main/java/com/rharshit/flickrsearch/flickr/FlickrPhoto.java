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
//        s	small square 75x75
//        q	large square 150x150
//        t	thumbnail, 100 on longest side
//        m	small, 240 on longest side
//        n	small, 320 on longest side
//                -	medium, 500 on longest side
//        z	medium 640, 640 on longest side
//        c	medium 800, 800 on longest side†
//        b	large, 1024 on longest side*
//                h	large 1600, 1600 on longest side†
//        k	large 2048, 2048 on longest side†
//        o	original image, either a jpg, gif or png, depending on source format
        String url = "https://farm"+farm+".staticflickr.com/"+server+"/"+id+"_"+secret+"_"+ext+".jpg";
        Log.d(TAG, "getURL: "+url);
        return url;
    }
}
