package com.richardradics.cleanrx.app.test;

import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import com.richardradics.cleanrx.ui.activity.MainActivity;

import org.junit.runner.RunWith;

/**
 * Created by evan on 3/29/15.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentationTest extends ActivityInstrumentationTestCase2<MainActivity> {
    public MainActivityInstrumentationTest() {
        super(MainActivity.class);
    }
    

}
