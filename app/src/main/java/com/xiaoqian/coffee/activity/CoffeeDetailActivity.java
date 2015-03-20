package com.xiaoqian.coffee.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.xiaoqian.coffee.R;
import com.xiaoqian.coffee.adater.CoffeeListViewAdapter;
import com.xiaoqian.coffee.app.AppController;

public class CoffeeDetailActivity extends Activity {

    private TextView detailNameTextView;
    private TextView detailDescTextView;
    private NetworkImageView detailImage;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_detail);

        ActionBar actionBar = getActionBar();
        actionBar.setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#F16421")));
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        LayoutInflater inflater = getLayoutInflater();
        View layoutView = inflater.inflate(R.layout.custom_action_bar, null);
        ImageView iconInLayout = (ImageView)layoutView.findViewById(R.id.imageView);
        iconInLayout.setColorFilter(Color.parseColor("#FFFFFF"));
        iconInLayout.setImageResource(R.drawable.drip);
        actionBar.setCustomView(layoutView);

        detailNameTextView = (TextView)findViewById(R.id.detailName);
        detailDescTextView = (TextView)findViewById(R.id.detailDesc);
        detailImage = (NetworkImageView)findViewById(R.id.coffeeImage);

        detailNameTextView.setEllipsize(null);
        detailDescTextView.setEllipsize(null);
        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        if(getIntent().getStringExtra(CoffeeListViewAdapter.COFFEE_IMAGE_URL).length() != 0) {
            detailImage.setImageUrl(getIntent().getStringExtra(CoffeeListViewAdapter.COFFEE_IMAGE_URL), imageLoader);
            detailImage.setVisibility(View.VISIBLE);
        }
        else{
            detailImage.setVisibility(View.GONE);
        }

        detailNameTextView.setText(getIntent().getStringExtra(CoffeeListViewAdapter.COFFEE_NAME));
        detailDescTextView.setText(getIntent().getStringExtra(CoffeeListViewAdapter.COFFEE_DESC));
    }

}
