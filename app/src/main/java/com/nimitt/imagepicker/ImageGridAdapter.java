package com.nimitt.imagepicker;


import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ImageGridAdapter extends BaseAdapter {

    private Context mContext;
    ArrayList<Uri> mArray;
    public ImageGridAdapter(Context c, ArrayList<Uri> mArrayUri) {
        this.mContext = c;
        this.mArray = mArrayUri;
    }


    @Override
    public int getCount() {
        if(mArray == null){
            return 0;
        }
        return mArray.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public View getView(final int position, View convertView, ViewGroup parent) {

        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        }
        else
        {
            imageView = (ImageView) convertView;
        }
        Glide.with(mContext).load(mArray.get(position)).into(imageView);
        return imageView;
    }
}