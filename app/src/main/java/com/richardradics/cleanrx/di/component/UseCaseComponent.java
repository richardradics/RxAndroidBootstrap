package com.richardradics.cleanrx.di.component;

import com.richardradics.cleanrx.di.PerActivity;
import com.richardradics.cleanrx.di.module.ActivityModule;
import com.richardradics.cleanrx.di.module.UseCaseModule;
import com.richardradics.cleanrx.ui.activity.MainActivity;

import dagger.Component;

/**
 * Created by radicsrichard on 15. 06. 01..
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, UseCaseModule.class})
public interface UseCaseComponent extends ActivityComponent {
    void inject(MainActivity mainActivity);

}
