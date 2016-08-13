package com.thoughtworks.lean.gocd.util;

import java.util.List;
import java.util.function.ToIntFunction;

public class WrappedList<T> {
    List<T> list;

    public static <X> WrappedList<X> newInstance(List<X> list) {
        return new WrappedList<>(list);
    }

    public WrappedList(List<T> list) {
        this.list = list;
    }

    public int sumInt(ToIntFunction<T> toIntFunction) {
        return CollectionsUtil.sumInt(list, toIntFunction);
    }

    public List<T> getList() {
        return list;
    }
}
