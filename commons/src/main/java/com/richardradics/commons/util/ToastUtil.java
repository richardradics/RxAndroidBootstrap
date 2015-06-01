package com.richardradics.commons.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Richard Radics on 2015.02.12..
 */
public class ToastUtil {

    /**
     * Display a toast with the given message (Length will be Toast.LENGTH_SHORT -- approx 2 sec).
     *
     * @param context The current Context or Activity that this method is called from
     * @param message Message to display
     * @return Toast object that is being displayed. Note,show() has already been called on this object.
     */
    public static Toast quickToast(Context context, String message) {
        return quickToast(context, message, false);
    }

    /**
     * Display a toast with the given message.
     *
     * @param context    The current Context or Activity that this method is called from
     * @param message    Message to display
     * @param longLength if true, will use Toast.LENGTH_LONG (approx 3.5 sec) instead of
     * @return Toast object that is being displayed. Note,show() has already been called on this object.
     */
    public static Toast quickToast(Context context, String message, boolean longLength) {
        final Toast toast;
        if (longLength) {
            toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        } else {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        }
        toast.show();
        return toast;
    }

}
