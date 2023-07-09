package com.tourguide.place.util;

import java.util.HashSet;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapperImpl;

public class MappingUtil {
    public static void copyNotNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    private static String[] getNullPropertyNames(Object source) {
        final var src = new BeanWrapperImpl(source);
        var pds = src.getPropertyDescriptors();
        var emptyNames = new HashSet<String>();
        for (var pd : pds) {
            var srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        var result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
