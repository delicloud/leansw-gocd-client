package com.thoughtworks.lean.util;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertEquals;

public class FilterTest {
    @Test
    public void should_filter_test() {
        List<String> test = new LinkedList<>();
        test.add("123");
        List<String> ret = test.stream().filter(o -> o != null).collect(Collectors.toList());
        assertEquals(ret.size(),1);

    }
}
