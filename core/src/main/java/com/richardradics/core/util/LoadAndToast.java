package com.richardradics.core.util;

import android.app.Activity;
import android.content.Context;

import com.richardradics.commons.widget.loadtoast.LoadToast;
import com.richardradics.core.navigator.Navigator;

import javax.inject.Inject;

/**
 * Created by mark on 2015. 04. 29..
 */

public class LoadAndToast {

    private Context context;


    private Navigator navigator;
    private CommonUseCases commonUseCases;
    private LoadToast loadToast;

    @Inject
    public LoadAndToast(){
    }

    public LoadAndToast(Context context, CommonUseCases commonUseCases, Navigator navigator) {
        this.navigator = navigator;
        this.context = context;
        this.commonUseCases = commonUseCases;
        init(this.context);
    }

    protected void init(Context context) {
        if (context != null) {
            loadToast = new LoadToast(context);
            setupLoadToast();
        }
    }

    protected void setupLoadToast() {
        if (loadToast != null) {
            loadToast.setTranslationY((commonUseCases.getScreenSize().y - commonUseCases.getNavigationBarHeight() - commonUseCases.getScreenSize().y / 5) - 5);
        }
    }


    public void showLoading(String message) {
            loadToast.setText(message);
            loadToast.show();
    }

    public void endLoading(boolean success) {
            if (success)
                loadToast.success();
            else
                loadToast.error();
    }
}
