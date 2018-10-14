package com.codedito.itemservice.hystrix;

import com.codedito.itemservice.util.UserContext;
import com.codedito.itemservice.util.UserContextHolder;

import java.util.concurrent.Callable;

public class DelegatingUserContextCallable<V> implements Callable<V> {
    private final Callable<V> delegate;
    private UserContext originalUserContext;

    public DelegatingUserContextCallable(final Callable<V> delegate, final UserContext userContext) {
        this.delegate = delegate;
        originalUserContext = userContext;
    }

    @Override
    public V call() throws Exception {
        UserContextHolder.setContext(originalUserContext);

        try {
            return delegate.call();
        } finally {
            originalUserContext = null;
        }
    }

    public static <V> Callable<V> create(final Callable<V> delegate, final UserContext userContext) {
        return new DelegatingUserContextCallable<V>(delegate, userContext);
    }
}