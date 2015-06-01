package com.richardradics.cleanrx.di.module;

import com.richardradics.cleanrx.di.PerActivity;
import com.richardradics.cleanrx.interactor.GetCitiesUseCase;
import com.richardradics.cleanrx.repository.CleanRepository;
import com.richardradics.core.executor.PostExecutionThread;
import com.richardradics.core.executor.ThreadExecutor;
import com.richardradics.core.interactor.UseCase;

import dagger.Module;
import dagger.Provides;

/**
 * Created by radicsrichard on 15. 06. 01..
 */
@Module
public class UseCaseModule {

    @Provides @PerActivity
    UseCase provideGetCitiesUseCase(CleanRepository cleanRepository, ThreadExecutor threadExecutor,
                                    PostExecutionThread postExecutionThread) {
        return new GetCitiesUseCase(threadExecutor, postExecutionThread, cleanRepository);
    }

}
