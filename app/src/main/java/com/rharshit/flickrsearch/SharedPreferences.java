package com.rharshit.flickrsearch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class SharedPreferences {
    public static android.content.SharedPreferences shre;
    public static android.content.SharedPreferences.Editor edit;
    static Context context;

    SharedPreferences(Context context){
        this.context = context;

        shre = PreferenceManager.getDefaultSharedPreferences(context);
        edit=shre.edit();
    }

    public static void setBitmap(String id, Bitmap image){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);

        edit.putString(id, encodedImage);
        edit.commit();
    }

    public static Bitmap getBitmap(String id){
        Bitmap bitmap = null;
        String previouslyEncodedImage = shre.getString(id, "");
        if( !previouslyEncodedImage.equalsIgnoreCase("") ){
            byte[] b = Base64.decode(previouslyEncodedImage, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
        }
        return bitmap;
    }
}
