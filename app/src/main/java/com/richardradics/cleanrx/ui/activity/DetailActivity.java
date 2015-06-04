package com.richardradics.cleanrx.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.nineoldandroids.view.ViewHelper;
import com.richardradics.cleanrx.R;
import com.richardradics.cleanrx.app.BaseActivity;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import butterknife.InjectView;

public class DetailActivity extends BaseActivity implements Palette.PaletteAsyncListener {

    public static final String EXTRA_IMAGE = "DetailActivity:image";
    public static final String EXTRA_TITLE = "DetailActivity:title";
    private static boolean TOOLBAR_IS_STICKY = false;
    private static float MAX_TEXT_SCALE_DELTA = 0.3f;


    int mFlexibleSpaceImageHeight;

    @InjectView(R.id.image)
    ImageView image;


    @InjectView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    Integer mActionBarSize;

    private Target bitmapImageViewTarget;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        mActionBarSize = getActionBarSize();


        mFlexibleSpaceImageHeight = getResources().getDimensionPixelSize(R.dimen.flexible_space_image_height);

        image = (ImageView) findViewById(R.id.image);
        ViewCompat.setTransitionName(image, EXTRA_IMAGE);
        collapsingToolbar.setTitle(getIntent().getStringExtra(EXTRA_TITLE));
        bitmapImageViewTarget = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Palette.generateAsync(bitmap, DetailActivity.this);
                image.setImageBitmap(bitmap);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                //place your code here
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                //place your code here
            }
        };
        Picasso.with(this).load(getIntent().getStringExtra(EXTRA_IMAGE)).into(bitmapImageViewTarget);


    }


    public static void launch(BaseActivity activity, View transitionView, String url, String title) {
        ActivityOptionsCompat options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                        activity, transitionView, EXTRA_IMAGE);
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(EXTRA_IMAGE, url);
        intent.putExtra(EXTRA_TITLE, title);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }


    @Override
    public void onGenerated(Palette palette) {
        try {
            if (palette != null) {
                final Palette.Swatch darkVibrantSwatch = palette.getDarkVibrantSwatch();
                final Palette.Swatch darkMutedSwatch = palette.getDarkMutedSwatch();
                final Palette.Swatch lightVibrantSwatch = palette.getLightVibrantSwatch();
                final Palette.Swatch lightMutedSwatch = palette.getLightMutedSwatch();
                final Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();

                final Palette.Swatch backgroundAndContentColors = (darkVibrantSwatch != null)
                        ? darkVibrantSwatch : darkMutedSwatch;

                final Palette.Swatch titleAndFabColors = (darkVibrantSwatch != null)
                        ? lightVibrantSwatch : lightMutedSwatch;

                toolbar.setBackgroundColor(backgroundAndContentColors.getRgb());
                //   bodyTextview.setBackgroundColor(backgroundAndContentColors.getRgb());
                titleTextView.setTextColor(titleAndFabColors.getRgb());
            }
        } catch (Exception e) {

        }
    }
}
