package com.rharshit.flickrsearch.flickr;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.rharshit.flickrsearch.R;
import com.rharshit.flickrsearch.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class FlickrPhotosAdapter extends ArrayAdapter<FlickrPhoto> {

    private Context mContext;
    private ArrayList<FlickrPhoto> values = new ArrayList<FlickrPhoto>();

    public FlickrPhotosAdapter(Context context, int resource, ArrayList<FlickrPhoto> objects) {
        super(context, resource, objects);

        this.values = objects;
        mContext = context;
    }

    public void addPhotos(ArrayList<FlickrPhoto> objects){
        values.addAll(objects);
    }

    @Override
    public int getCount() {
        return values!=null?values.size():0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FlickrPhoto photo = values.get(position);
        ImageView iv = new ImageView(mContext);
        iv.setLayoutParams(new LinearLayoutCompat.LayoutParams(200, 200));
        iv.setForegroundGravity(Gravity.CENTER);
        iv.setPadding(8, 8, 8,8);
        Bitmap image = SharedPreferences.getBitmap(photo.id);
        if(image != null){
            iv.setImageBitmap(image);
            Log.d(TAG, "getView: Loaded from cache");
        } else {
            try{
                UrlImageViewHelper.setUrlDrawable(iv, photo.getURL("q"), R.drawable.ic_insert_photo);
                Log.d(TAG, "getView: Loaded from URL");
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return iv;
    }
}
