package com.github.aceroni75.gwtboot.client.util;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

public class Rest {
    public static <R> Delegate<R> using(ResourceDelegate<R> resourceDelegate) {
        return new Delegate<>(resourceDelegate);
    }

    public static class Delegate<R> {

        private final ResourceDelegate<R> resourceDelegate;

        private Delegate(ResourceDelegate<R> resourceDelegate) {
            this.resourceDelegate = Objects.requireNonNull(resourceDelegate);
        }

        public <E> Caller<R, E> call(Function<R, E> function) {
            return new Caller<>(resourceDelegate, function);
        }
    }

    public static class Caller<R, E> {
        private final ResourceDelegate<R> resourceDelegate;
        private final Function<R, E> function;
        private Consumer<E> onSuccess = e -> {};
        private Consumer<Throwable> onFailure = t -> {};

        private Caller(ResourceDelegate<R> resourceDelegate, Function<R, E> function) {
            this.resourceDelegate = Objects.requireNonNull(resourceDelegate);
            this.function = Objects.requireNonNull(function);
        }

        public Caller<R, E> onSuccess(Consumer<E> onSuccess) {
            this.onSuccess = Objects.requireNonNull(onSuccess);
            return this;
        }

        public Caller<R, E> onFailure(Consumer<Throwable> onFailure) {
            this.onFailure = Objects.requireNonNull(onFailure);
            return this;
        }

        public void apply() {
            function.apply(resourceDelegate.withCallback(new AsyncCallback<E>() {
                @Override
                public void onFailure(Throwable throwable) {
                    onFailure.accept(throwable);
                }

                @Override
                public void onSuccess(E e) {
                    onSuccess.accept(e);
                }
            }));
        }
    }
}
