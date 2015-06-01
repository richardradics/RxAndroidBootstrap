package com.richardradics.core.navigator;


import android.app.Activity;
import android.view.View;

import javax.inject.Inject;

import hugo.weaving.DebugLog;

/**
 * Created by radicsrichard on 15. 05. 29..
 */
public class Navigator {

    @Inject
    public Navigator() {
    }

    private Activity currentActivityOnScreen;


    public Activity getCurrentActivityOnScreen() {
        return currentActivityOnScreen;
    }

    @DebugLog
    public void setCurrentActivityOnScreen(Activity currentActivityOnScreen) {
        this.currentActivityOnScreen = currentActivityOnScreen;
    }

    public static void navigateUp(Activity activity) {
        activity.finish();
    }

    public boolean isInForeground() {
        if (currentActivityOnScreen != null) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public View getActivityRootView() {
        return currentActivityOnScreen.getWindow().getDecorView().findViewById(android.R.id.content);
    }

}
