package com.richardradics.cleanrx.interactor;

import com.richardradics.cleanrx.repository.CleanRepository;
import com.richardradics.core.executor.PostExecutionThread;
import com.richardradics.core.executor.ThreadExecutor;
import com.richardradics.core.interactor.UseCase;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by radicsrichard on 15. 05. 29..
 */
public class GetCitiesUseCase extends UseCase {

    private final CleanRepository cleanRepository;

    public static final Double BP_LATITUDE = 47.498405;
    public static final Double BP_LONGITUDE = 19.040757;
    public static final Integer DEFAULT_COUNT = 25;

    @Inject
    public GetCitiesUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, CleanRepository cleanRepository) {
        super(threadExecutor, postExecutionThread);
        this.cleanRepository = cleanRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.cleanRepository.getCities(BP_LATITUDE, BP_LONGITUDE, DEFAULT_COUNT);
    }
}
