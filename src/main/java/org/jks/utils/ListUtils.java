package org.jks.utils;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author liaojian
 * @version 11/23/2016
 */
public class ListUtils {
    public static Set<Object> toSet(List<Object> sources){
        Set<Object> targets = Collections.emptySet();
        targets.addAll(sources);
        return targets;
    }
}
