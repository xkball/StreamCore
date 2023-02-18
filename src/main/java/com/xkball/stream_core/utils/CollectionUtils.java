package com.xkball.stream_core.utils;

import java.util.Map;


public class CollectionUtils {
    public static <K,V> Pair<K,V> formEntryToPair(Map.Entry<K,V> entry){
        return new Pair<>(entry.getKey(),entry.getValue());
    }
    
    public static <K,V> void putIntoMap(Map<K,V> map,Pair<K,V> pair){
        map.put(pair.key,pair.value);
    }
}
