package com.richardradics.cleanrx.app;

import android.content.Context;

import com.richardradics.cleanrx.BuildConfig;
import com.richardradics.core.error.ErrorHandler;
import com.richardradics.core.navigator.Navigator;

import javax.inject.Inject;

/**
 * Created by radicsrichard on 15. 06. 01..
 */
public class CleanErrorHandler extends ErrorHandler {

    @Inject
    public CleanErrorHandler(Context context, Navigator navigator) {
        super(context, navigator);
    }

    @Override
    public void handlerError(Exception e) {
        super.handlerError(e);
        showError("Error occured!");
    }

    public void logException(Exception e) {
        if (BuildConfig.DEBUG) {
            logError(e);
        }
    }

    public void showSnackbar(String s) {
        showError(s);
    }

}
