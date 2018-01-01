package com.github.aceroni75.gwtboot.client.util;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Possible<T> {

    private final T value;

    private Possible(T value) {
        this.value = value;
    }

    public static <T> Possible<T> of(T value) {
        return new Possible<>(value);
    }

    public static <T> Possible<T> empty() {
        return of(null);
    }

    public Possible<T> filter(Predicate<T> validator) {
        return isEmpty() || validator.test(value) ? this : empty();
    }

    public <R> Possible<R> transform(Function<T, R> transformation) {
        return isEmpty() ? empty() : of(transformation.apply(value));
    }

    public Possible<T> ifPresent(Consumer<T> consumer) {
        if (isNotEmpty()) {
            consumer.accept(value);
        }
        return this;
    }

    public Possible<T> orElse(Runnable runnable) {
        if (isEmpty()) {
            runnable.run();
        }
        return this;
    }

    private boolean isNotEmpty() {
        return !isEmpty();
    }

    public boolean isEmpty() {
        return value == null;
    }
}
