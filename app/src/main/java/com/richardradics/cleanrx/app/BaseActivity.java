package com.richardradics.cleanrx.app;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.richardradics.cleanrx.R;
import com.richardradics.cleanrx.di.component.ApplicationComponent;
import com.richardradics.cleanrx.di.module.ActivityModule;
import com.richardradics.core.navigator.Navigator;
import com.richardradics.core.util.CommonUseCases;
import com.richardradics.core.util.LoadAndToast;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by radicsrichard on 15. 05. 29..
 */
public abstract class BaseActivity extends AppCompatActivity {

    @InjectView(R.id.toolbar)
    protected Toolbar toolbar;


    @InjectView(R.id.toolbar_title)
    protected TextView titleTextView;

    @Inject
    protected Navigator navigator;

    @Inject
    protected CleanErrorHandler cleanErrorHandler;

    @Inject
    protected LoadAndToast loadAndToast;

    @Inject
    protected CommonUseCases commonUseCases;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.inject(this);
        initToolBar();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.inject(this);
        initToolBar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigator.setCurrentActivityOnScreen(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        navigator.setCurrentActivityOnScreen(null);
    }

    protected void initToolBar() {
        if (toolbar != null) {
            toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
            titleTextView = (TextView) findViewById(R.id.toolbar_title);
            setSupportActionBar(toolbar);
            titleTextView.setText(getTitle());
            toolbar.setNavigationIcon(R.drawable.ic_backarrow);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    protected ApplicationComponent getApplicationComponent() {
        return ((CleanApplication) getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    protected int getActionBarSize() {
        TypedValue typedValue = new TypedValue();
        int[] textSizeAttr = new int[]{R.attr.actionBarSize};
        int indexOfAttrTextSize = 0;
        TypedArray a = obtainStyledAttributes(typedValue.data, textSizeAttr);
        int actionBarSize = a.getDimensionPixelSize(indexOfAttrTextSize, -1);
        a.recycle();
        return actionBarSize;
    }
}
