package com.richardradics.cleanrx.app;

import android.app.Application;

import com.richardradics.cleanrx.di.component.ApplicationComponent;
import com.richardradics.cleanrx.di.component.DaggerApplicationComponent;
import com.richardradics.cleanrx.di.module.ApplicationModule;

/**
 * Created by radicsrichard on 15. 06. 01..
 */
public class CleanApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

}
