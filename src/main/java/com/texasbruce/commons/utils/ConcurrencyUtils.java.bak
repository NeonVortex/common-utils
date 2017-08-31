package com.texasbruce.commons.utils;

public class ConcurrencyUtils {
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <T> T getObjectFromNestedFuture(Object obj) throws InterruptedException, java.util.concurrent.ExecutionException {
        T result = null;
        
        if (obj instanceof java.util.concurrent.Future) {
            result = ConcurrencyUtils.<T>getObjectFromNestedFuture(((java.util.concurrent.Future) obj).get());
        }
        else {
            try {
                result = (T) obj;
            }
            catch (Exception e) {
                throw new ClassCastException("Unknown type of result caught: " + obj);
            }
        }
        
        return result;
    }
}
