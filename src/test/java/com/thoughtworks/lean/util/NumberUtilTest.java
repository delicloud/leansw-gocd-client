package com.thoughtworks.lean.util;

import com.google.common.primitives.Ints;
import org.apache.commons.lang.math.IntRange;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class NumberUtilTest {
    @Test
    public void test_int_array_to_list(){
        List<Integer> integerList=Ints.asList(new IntRange(0,100).toArray());
        Assert.assertEquals(101,integerList.size());
        Assert.assertEquals(0,integerList.get(0).intValue());
        Assert.assertEquals(100,integerList.get(100).intValue());

    }
}
