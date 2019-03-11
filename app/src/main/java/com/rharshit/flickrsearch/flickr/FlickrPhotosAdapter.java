package com.rharshit.flickrsearch.flickr;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.rharshit.flickrsearch.R;

import java.util.List;

public class FlickrPhotosAdapter extends ArrayAdapter<FlickrPhoto> {

    private Context mContext;
    private List<FlickrPhoto> values;

    public FlickrPhotosAdapter(Context context, int resource, List<FlickrPhoto> objects) {
        super(context, resource, objects);

        this.values = objects;
        mContext = context;
    }

    @Override
    public int getCount() {
        return values!=null?values.size():0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FlickrPhoto photo = values.get(position);
        ImageView iv = new ImageView(mContext);
        UrlImageViewHelper.setUrlDrawable(iv, photo.getURL("q"), R.drawable.ic_insert_photo);
        return iv;
    }
}
