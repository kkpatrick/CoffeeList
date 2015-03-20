package com.xiaoqian.coffee.adater;

/**
 * Created by abc on 3/20/15.
 */
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.xiaoqian.coffee.app.AppController;
import com.xiaoqian.coffee.model.Coffee;

import java.util.List;

import com.xiaoqian.coffee.R;
public class CoffeeListViewAdapter extends BaseAdapter{
    private Activity activity;
    private LayoutInflater inflater;
    //private List<Movie> movieItems;
    private List<Coffee> coffeeItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CoffeeListViewAdapter(Activity activity, List<Coffee> coffeeItems) {
        this.activity = activity;
        this.coffeeItems = coffeeItems;
    }

    @Override
    public int getCount() {
        return coffeeItems.size();
    }

    @Override
    public Object getItem(int location) {
        return coffeeItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView desc = (TextView) convertView.findViewById(R.id.desc);

        // getting movie data for the row
        Coffee m = coffeeItems.get(position);

        // thumbnail image
        if(m.getImageUrl().length() != 0) {
            thumbNail.setImageUrl(m.getImageUrl(), imageLoader);
            thumbNail.setVisibility(View.VISIBLE);
        }
        else{
            thumbNail.setVisibility(View.GONE);
        }
        // name
        name.setText(m.getCoffeeName());

        // description
        desc.setText(m.getCoffeeDesc());

        return convertView;
    }

}
