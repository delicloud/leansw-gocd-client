package com.thoughtworks.lean.gocd.util;

import java.util.Collection;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class CollectionsUtil {
    private CollectionsUtil() {

    }

    public static <T> int sumInt(Collection<T> inputs, ToIntFunction<T> toIntFunction) {
        if (inputs == null) return 0;
        return inputs.stream().mapToInt(toIntFunction).sum();
    }

    public static <T> long sumLong(Collection<T> inputs, ToLongFunction<T> toIntFunction) {
        if (inputs == null) return 0;
        return inputs.stream().mapToLong(toIntFunction).sum();
    }


    public static boolean empty(Collection collection) {
        return collection == null || collection.isEmpty();
    }
}
