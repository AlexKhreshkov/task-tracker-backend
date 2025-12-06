package com.example.task_tracker_backend.utils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Function;

public class ExceptionUtils {

    @NotNull
    public static <T> T requireNonNull(@Nullable T value) throws NullPointerException {

        return requireNonNull(value, null);
    }

    @NotNull
    public static <T> T requireNonNull(@Nullable T value, @Nullable String message) throws NullPointerException {

        return requireNonNull(value, message, NullPointerException::new);
    }

    @NotNull
    public static <T, E extends Exception> T requireNonNull(@Nullable T value, @Nullable String message, Function<String, E> exceptionSupplier) throws E {
        if (value == null) {
            throw exceptionSupplier.apply(message);
        }
        return value;
    }

    @NotNull
    public static <T> T requireNonNull(Optional<T> value) {
        return requireNonNull(value, null);
    }

    @NotNull
    public static <T> T requireNonNull(Optional<T> value, String message) {
        return requireNonNull(value, message, IllegalArgumentException::new);
    }

    @NotNull
    public static <T, E extends Exception> T requireNonNull(@Nullable Optional<T> value, @Nullable String message, Function<String, E> exceptionSupplier) throws E {
        if (value.isEmpty()) {
            throw exceptionSupplier.apply(message);
        }
        return value.get();
    }

    @NotNull
    @Contract("null,_,_->fail")
    public static <T, X extends Throwable> T requireNonNull(
            @Nullable
            final T value,
            @NotNull
            final Function<String, X> exceptionSupplier,
            @Nullable
            final String msg) throws X {

        requireNonNull(exceptionSupplier, "exceptionSupplier is null");

        if (value == null) {
            throw exceptionSupplier.apply(msg);
        }

        return value;
    }

    public static <E extends Exception> boolean require(boolean condition, @Nullable String message) {
        return require(condition, message, IllegalStateException::new);
    }

    public static <E extends Exception> boolean require(boolean condition, @Nullable String message, Function<String, E> exceptionSupplier) throws E {
        if (!condition) {
            throw exceptionSupplier.apply(message);
        }
        return true;
    }
}
