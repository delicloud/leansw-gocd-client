package com.thoughtworks.lean.util;

import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DateUtil {
    private DateUtil() {
    }

    public static Date maxOrNull(Stream<Date> dateStream) {
        long max = dateStream.filter(o -> o != null).mapToLong(Date::getTime).max().orElse(0);
        return max == 0 ? null : new DateTime(max).toDate();
    }

    public static Date minOrNull(Stream<Date> dateStream) {
        long min = dateStream.filter(o -> o != null).mapToLong(Date::getTime).min().orElse(Long.MAX_VALUE);
        return min == Long.MAX_VALUE ? null : new DateTime(min).toDate();
    }


    public static Date max(Collection<Date> dates) {
        return Collections.max(dates, Date::compareTo);
    }

    public static Date min(Collection<Date> dates) {
        if (CollectionsUtil.empty(dates)) {
            return null;
        }

        return Collections.min(dates, Date::compareTo);
    }

    public static List<Date> dayRangeTo(Date date, int count) {
        LocalDate fromDate = LocalDate.fromDateFields(date);
        return NumberUtil.intRange(0, count - 1).stream()
                .map(i -> fromDate.minusDays(i).toDate()).collect(Collectors.toList());
    }

    public static List<Date> dayRangeFrom(Date date, int count) {
        LocalDate fromDate = LocalDate.fromDateFields(date);
        return NumberUtil.intRange(0, count - 1).stream()
                .map(i -> fromDate.plusDays(i).toDate()).collect(Collectors.toList());
    }


    public static List<Date> dayRange(Collection<Date> dates) {
        if (dates == null || dates.size() == 0) {
            return Collections.emptyList();
        }
        final List<Date> filtered = dates.stream().filter(date -> date != null).collect(Collectors.toList());
        if (filtered.size() == 0) {
            return Collections.emptyList();
        }
        return dayRange(min(filtered), max(filtered));
    }


    public static List<Date> dayRange(Date... dates) {
        if (dates == null) {
            return Collections.emptyList();
        }
        List<Date> filteredDates = Arrays.asList(dates).stream().filter(date -> date != null).collect(Collectors.toList());
        if (CollectionsUtil.empty(filteredDates)) {
            return Collections.emptyList();
        }
        LocalDate startDate = LocalDate.fromDateFields(min(filteredDates));
        LocalDate endDate = LocalDate.fromDateFields(max(filteredDates));
        int i = 0;
        List<Date> retDates = Lists.newArrayList();
        while (!startDate.plusDays(i).isAfter(endDate)) {
            retDates.add(startDate.plusDays(i).toDate());
            i++;
        }
        return retDates;
    }
}
