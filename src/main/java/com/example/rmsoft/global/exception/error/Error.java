package com.example.rmsoft.global.exception.error;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface Error {

    static <V, T> List<T> from(Collection<V> collection, Function<? super V, ? extends T> mapper) {

        return collection.stream()
                .map(mapper)
                .collect(Collectors.toList());
    }
}
