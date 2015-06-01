package com.richardradics.core.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import android.view.WindowManager;

import com.richardradics.commons.helper.TelephonyHelper;
import com.richardradics.commons.util.ConnectivityUtil;
import com.richardradics.commons.util.EmailUtil;
import com.richardradics.core.navigator.Navigator;

import javax.inject.Inject;


/**
 * Created by radicsrichard on 15. 04. 28..
 */

public class CommonUseCases {


    private Context context;
    private Navigator navigator;

    @Inject
    public CommonUseCases(Context context, Navigator navigator) {
        this.context = context;
        this.navigator = navigator;
    }

    public void sendEmail(String to, String subject, String body, String pickerTitle) {
        EmailUtil.startEmailActivity(context, to, subject, body, pickerTitle);
    }


    public void callPhone(String phoneNumber) {
        TelephonyHelper.startDialActivity(context, phoneNumber);
    }


    public void callPhoneWithSimCheck(String phoneNumber) {
        TelephonyHelper.startDialActivityWithSimCheck(context, phoneNumber);
    }

    public boolean isNetworkConnected() {
        return ConnectivityUtil.isConnected(context);
    }

    public Point getScreenSizeinActivity() {
        Point size = new Point();

        if (navigator.isInForeground()) {
            Display display = navigator.getCurrentActivityOnScreen().getWindowManager().getDefaultDisplay(); //Activity#getWindowManager()
            display.getSize(size);
        }
        return size;

    }

    public Point getScreenSize() {
        Point size = new Point();

        WindowManager wm = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        display.getSize(size);
        return size;
    }

    public boolean hasNavBar() {
        boolean hasMenuKey = ViewConfiguration.get(context).hasPermanentMenuKey();
        boolean hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);

        if (!hasMenuKey && !hasBackKey)
            return true;
        else
            return false;
    }

    public int getNavigationBarHeight() {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");

        if (hasNavBar() && resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }
}
