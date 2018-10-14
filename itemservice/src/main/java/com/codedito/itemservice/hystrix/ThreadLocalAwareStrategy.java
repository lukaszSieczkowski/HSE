package com.codedito.itemservice.hystrix;

import com.codedito.itemservice.util.UserContextHolder;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariable;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariableLifecycle;
import com.netflix.hystrix.strategy.properties.HystrixProperty;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadLocalAwareStrategy extends HystrixConcurrencyStrategy {
    private HystrixConcurrencyStrategy existingConcurrencyStrategy;

    public ThreadLocalAwareStrategy(
            final HystrixConcurrencyStrategy existingConcurrencyStategy
    ) {
        existingConcurrencyStrategy = existingConcurrencyStrategy;
    }

    @Override
    public BlockingQueue<Runnable> getBlockingQueue(final int maxQueueSize) {
        return existingConcurrencyStrategy != null ? existingConcurrencyStrategy.getBlockingQueue(maxQueueSize) : super.getBlockingQueue(maxQueueSize);
    }

    @Override
    public <T> HystrixRequestVariable<T> getRequestVariable(
            final HystrixRequestVariableLifecycle<T> rv
    ) {
        return existingConcurrencyStrategy != null ? existingConcurrencyStrategy.getRequestVariable(rv) : super.getRequestVariable(rv);
    }

    @Override
    public ThreadPoolExecutor getThreadPool(
            final HystrixThreadPoolKey threadPoolKey,
            final HystrixProperty<Integer> corePoolSize,
            final HystrixProperty<Integer> maximumPoolSize,
            final HystrixProperty<Integer> keepAliveTime,
            final TimeUnit unit,
            final BlockingQueue<Runnable> workQueue
    ) {
        return existingConcurrencyStrategy != null ? existingConcurrencyStrategy.getThreadPool(threadPoolKey, corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue) : super.getThreadPool(threadPoolKey, corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    public <T> Callable<T> wrapCallable(final Callable<T> callable) {
        return existingConcurrencyStrategy != null ? existingConcurrencyStrategy.wrapCallable(new DelegatingUserContextCallable<T>(callable, UserContextHolder.getContext())) : super.wrapCallable(new DelegatingUserContextCallable<T>(callable, UserContextHolder.getContext()));
    }


}