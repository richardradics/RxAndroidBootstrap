package com.richardradics.core.error;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.Log;

import com.richardradics.commons.util.ErrorUtil;
import com.richardradics.core.navigator.Navigator;


/**
 * Created by radicsrichard on 15. 04. 28..
 */

public class ErrorHandler {

    protected Context context;

    protected Navigator navigator;

    protected ErrorHandler(Context context, Navigator navigator) {
        this.context = context;
        this.navigator = navigator;
    }

    public void handlerError(Exception e) {
        logError(e);
    }


    protected void showError(String s) {
        if (navigator.isInForeground()) {
            navigator.getCurrentActivityOnScreen().runOnUiThread(() -> {
                Snackbar.make(navigator.getActivityRootView(), s, Snackbar.LENGTH_SHORT).show();
            });
        }
    }

    protected void logError(Throwable e) {
        String report = ErrorUtil.getErrorReport(e);
        Log.e("ErrorHandler Report:", report);
    }


}
