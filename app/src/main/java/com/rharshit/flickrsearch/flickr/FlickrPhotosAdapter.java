package com.rharshit.flickrsearch.flickr;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.rharshit.flickrsearch.R;

import java.util.ArrayList;
import java.util.List;

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
        UrlImageViewHelper.setUrlDrawable(iv, photo.getURL("q"), R.drawable.ic_insert_photo);
        return iv;
    }
}
