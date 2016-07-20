package com.thoughtworks.lean.util;

import com.google.common.base.Optional;
import com.google.common.primitives.Ints;
import org.apache.commons.lang.math.IntRange;

import java.util.List;

public class NumberUtil {
    public static int intValue(Number number) {
        return Optional.fromNullable(number).or(0).intValue();
    }

    public static List<Integer> intRange(int number1, int number2) {
        return Ints.asList(new IntRange(number1, number2).toArray());
    }
}
