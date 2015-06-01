/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.richardradics.cleanrx.di.module;

import android.content.Context;

import com.richardradics.cleanrx.app.CleanApplication;
import com.richardradics.cleanrx.app.CleanErrorHandler;
import com.richardradics.cleanrx.repository.CleanRepository;
import com.richardradics.cleanrx.repository.CleanWeatherService;
import com.richardradics.cleanrx.repository.api.mapper.WeatherResponseMapperImp;
import com.richardradics.core.executor.JobExecutor;
import com.richardradics.core.executor.PostExecutionThread;
import com.richardradics.core.executor.ThreadExecutor;
import com.richardradics.core.executor.UIThread;
import com.richardradics.core.navigator.Navigator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {
    private final CleanApplication application;

    public ApplicationModule(CleanApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    Navigator provideNavigator() {
        return new Navigator();
    }

    @Provides
    @Singleton
    CleanErrorHandler provideCleanErrorHandler(Context context, Navigator navigator) {
        return new CleanErrorHandler(context, navigator);
    }


    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    CleanRepository provideCleanRepository(CleanWeatherService cleanWeatherService) {
        return cleanWeatherService;
    }

    @Provides
    @Singleton
    CleanWeatherService provideCleanWeatherService(Context context, WeatherResponseMapperImp weatherResponseMapperImp){
        return new CleanWeatherService(context, weatherResponseMapperImp);
    }

    @Provides
    @Singleton
    WeatherResponseMapperImp provideWeatherResponseMapper() {
        return new WeatherResponseMapperImp();
    }

}
