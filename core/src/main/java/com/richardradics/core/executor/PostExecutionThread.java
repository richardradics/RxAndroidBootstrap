package com.richardradics.core.executor;

import rx.Scheduler;

/**
 * Created by radicsrichard on 15. 05. 29..
 */
public interface PostExecutionThread {
    Scheduler getScheduler();
}
