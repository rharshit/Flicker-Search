package com.rharshit.flickrsearch.flickr;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import com.rharshit.flickrsearch.SharedPreferences;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

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
        new cacheBitmap().execute(url, id);
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
        new cacheBitmap().execute(url, id);
        return url;
    }

    private class cacheBitmap extends AsyncTask<String, Void, Boolean>{

        Bitmap image = null;
        String url;
        String id;
        @Override
        protected Boolean doInBackground(String... urls) {
            try {
                url = urls[0];
                id = urls[1];
                image = BitmapFactory.decodeStream((InputStream)new URL(url).getContent());
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(aBoolean){
                SharedPreferences.setBitmap(id, image);
                Log.d(TAG, "onPostExecute: "+id);
            }
        }
    }
}
