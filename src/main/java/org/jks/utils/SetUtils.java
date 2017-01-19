package org.jks.utils;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Set;

/**
 * @author liaojian
 * @version 11/23/2016
 */
public class SetUtils {

    public static List<Object> toList(Set<Object> targets){
        return Lists.newArrayList(targets);
    }


}
