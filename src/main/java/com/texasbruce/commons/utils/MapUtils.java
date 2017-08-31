package com.texasbruce.commons.utils;

import java.util.Map;

public class MapUtils {
    
    /**
     * This method is same as atomic version of HashMap::putIfAbsent 
     * but checks on value == null and returns the new value in Map
     * @param m
     * @param key
     * @param value
     * @return
     */
    public static <K,V> V putIfAbsentRetNewVal (Map<K,V> m, K key, V value) {
        synchronized (m) {
            if (m.get(key) == null) {
                m.put(key, value);
            }
            return m.get(key);
        }
    }

    public static <K,V> V putIfAbsentRetOldVal (Map<K,V> m, K key, V value) {
        synchronized (m) {
            if (m.get(key) == null) {
                return m.put(key, value);
            }
            else {
                return m.get(key);
            }
        }
    }

}
