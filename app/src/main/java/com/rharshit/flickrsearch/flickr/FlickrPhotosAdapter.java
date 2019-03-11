package com.rharshit.flickrsearch.flickr;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class FlickrPhotosAdapter extends ArrayAdapter<FlickrPhotosParse> {

    private Context mContext;
    private List<FlickrPhotosParse> values;

    public FlickrPhotosAdapter(Context context, int resource, List<FlickrPhotosParse> objects) {
        super(context, resource, objects);

        this.values = objects;
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tv = new TextView(mContext);
        FlickrPhotosParse photo = values.get(position);
        String id = "Test";
        tv.setText(id);
        return tv;
    }
}
